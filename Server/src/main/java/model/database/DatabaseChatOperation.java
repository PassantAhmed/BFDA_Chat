package model.database;

import beans.Group;
import beans.Message;
import utilities.SqlParser;


import java.sql.*;
import java.util.Vector;

public class DatabaseChatOperation {

    private PreparedStatement preparedStatement;
    private Connection connection;
    private ResultSet resultSet;

    public DatabaseChatOperation() throws SQLException, ClassNotFoundException {

        Database database = Database.getInstance();
        connection = database.getConnection();

    }

    public synchronized String getChatRoomOfClient(String myName, String clientName) throws SQLException {
        System.out.println(clientName);
        System.out.println(myName);
        String query = "SELECT * FROM Chatdb.FullChatRoomData where userName = ? and ChatRoom_id in " +
                "(select ChatRoom_id from Chatdb.FullChatRoomData where userName = ? ) and type = 0 ;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, clientName);
        preparedStatement.setString(2, myName);

        resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return resultSet.getString(1);
        else
            return createChatRoomWithUser("No Name", clientName, myName);


    }

    public synchronized String createChatRoomWithUser(String chatRoomName, String clientName, String myName) throws SQLException {

        String chatRoomID = createChatRoom(chatRoomName, "0");
        addClientToChatRoom(chatRoomID, myName);
        addClientToChatRoom(chatRoomID, clientName);

        return chatRoomID;

    }

    public synchronized String createChatRoomWithUsers(String chatRoomName, Vector<String> clients) throws SQLException {

        if (isAvailableChatRoomName(chatRoomName)) {
            String chatRoomID = createChatRoom(chatRoomName, "1");
            for (String client : clients)
                addClientToChatRoom(chatRoomID, client);
            return chatRoomID;
        }
        return null;

    }

    public synchronized String sendMsgtoDatabase(String chatMemberID, Message message) throws SQLException {
        String query = "INSERT INTO ChatMsg " +
                "(ChatMember_id, MsgBody, MsgFont, MsgSize, MsgIsBold, MsgIsItalic, MsgColor, DateStamp ) " +
                "VALUES ( ? , ? , ? , ? , ? , ? , ? , ? );";
        preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, chatMemberID);
        preparedStatement.setString(2, message.getMessageContent());
        preparedStatement.setString(3, message.getMessageFontFamily());
        preparedStatement.setString(4, message.getMessageFontSize());
        preparedStatement.setBoolean(5, message.isBold());
        preparedStatement.setBoolean(6, message.isItalic());
        preparedStatement.setString(7, message.getMessageFontColor());
        preparedStatement.setTimestamp(8, SqlParser.fromLocalDateTimeToSql(message.getMessageDate()));
        preparedStatement.executeUpdate();
        //connection.commit();

        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next())
            return rs.getString(1);
        return null;
    }

    private synchronized boolean isAvailableChatRoomName(String chatRoomName) throws SQLException {
        String query = "SELECT * FROM Chatdb.ChatRoom where ChatName = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, chatRoomName);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return false;
        else
            return true;
    }

    private synchronized String createChatRoom(String chatRoomName, String type) throws SQLException {

        String query = "INSERT INTO `Chatdb`.`ChatRoom` (`ChatName`, `Type`, `Active`) VALUES (?, ?, '1');";
        preparedStatement = connection.prepareStatement(query, 1);
        preparedStatement.setString(1, chatRoomName);
        preparedStatement.setString(2, type);
        preparedStatement.executeUpdate();
//        connection.commit();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next())
            return rs.getString(1);
        return null;

    }

    private synchronized void addClientToChatRoom(String chatRoomID, String users) throws SQLException {
        String query = "INSERT INTO `Chatdb`.`ChatMember` (`ChatRoom_id`, `User_id`, `isAdmin`) VALUES (?, (select User.id from User where username = ?), '0');";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, chatRoomID);
        preparedStatement.setString(2, users);
        preparedStatement.executeUpdate();
        //connection.commit();

    }

    public synchronized String getChatMemberID(String userName, String chatRoomID) throws SQLException {
        String query = "select ChatMember.id from ChatMember , User where User.username = ? " +
                "and ChatMember.User_id = User.id " +
                "and ChatMember.ChatRoom_id = ? ";
        preparedStatement = connection.prepareStatement(query);
        System.out.println("IngetChatMemberID");
        System.out.println(userName);
        System.out.println(chatRoomID);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, chatRoomID);

        resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return resultSet.getString(1);
        else
            return null;
    }

    public synchronized Vector<Message> getAllRoomMessages(String chatRoomID) throws SQLException {
        String query = "SELECT " +
                "    User.username,\n" +
                "    ChatMsg.MsgBody,\n" +
                "    ChatMsg.MsgFont,\n" +
                "    ChatMsg.MsgSize,\n" +
                "    ChatMsg.MsgIsBold,\n" +
                "    ChatMsg.MsgIsItalic,\n" +
                "    ChatMsg.MsgColor,\n" +
                "    ChatMsg.DateStamp\n" +
                "FROM ChatMsg , User , ChatMember , ChatRoom \n" +
                "where ChatRoom.id = ? \n" +
                "and ChatMember.ChatRoom_id = ChatRoom.id\n" +
                "and ChatMsg.ChatMember_id = ChatMember.id \n" +
                "and User.id = ChatMember.User_id\n" +
                "order by ChatMsg.DateStamp";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, chatRoomID);
        resultSet = preparedStatement.executeQuery();
        Vector<Message> messages = new Vector<>();
        while (resultSet.next()) {
            Message message = new Message();
            message.setFromUser(resultSet.getString(1));
            message.setMessageContent(resultSet.getString(2));
            message.setMessageFontFamily(resultSet.getString(3));
            message.setMessageFontSize(resultSet.getString(4));
            message.setBold(resultSet.getBoolean(5));
            message.setItalic(resultSet.getBoolean(6));
            message.setMessageFontColor(resultSet.getString(7));
            message.setMessageDate(SqlParser.fromSqlTimeStampToLocalDateTime(resultSet.getTimestamp(8)));
            messages.add(message);
        }
        return messages;

    }

    public Message getMessage(String chatMsgID) {
        return null;
    }

    public synchronized Vector<String> chatMembers(String chatMsgID) throws SQLException {
        String query = "select User.username from User , ChatMember , ChatRoom where " +
                "ChatRoom_id = (select ChatRoom.id from ChatRoom , ChatMsg , ChatMember " +
                "where ChatMsg.id = ? " +
                "and ChatMember.id = ChatMsg.ChatMember_id " +
                "and ChatRoom.id = ChatMember.ChatRoom_id ) " +
                "and ChatMember.ChatRoom_id = ChatRoom.id " +
                "and User.id = ChatMember.User_id";

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, Integer.parseInt(chatMsgID));
        resultSet = preparedStatement.executeQuery();
        Vector<String> members = new Vector<>();
        while (resultSet.next()) {
            String res = resultSet.getString(1);
            members.add(res);
            System.out.println(res);
        }
        return members;

    }

    public synchronized String getChatRoomForChatMember(String chatMemberID) throws SQLException {
        String query = "select ChatMember.ChatRoom_id from ChatMember where ChatMember.id = ? ";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, chatMemberID);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return resultSet.getString(1);
        else
            return null;
    }

    public synchronized Vector<Group> getAllGroups(int myID) throws SQLException {
        String query = "SELECT * FROM ChatRoom , ChatMember " +
                "where Type = 1 " +
                "and ChatMember.User_id = ? " +
                "and ChatRoom.id = ChatMember.ChatRoom_id;";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, myID);
        resultSet = preparedStatement.executeQuery();
        Vector<Group> groups = new Vector<>();
        while(resultSet.next())
        {
            groups.add(new Group(resultSet.getString(2) , resultSet.getString(1)));
        }
        return groups;
    }




}
