/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlfiles;

import beans.Message;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author Passant
 */
public class XmlMessage {

    private static ArrayList<Message> userMessages;
    private static final String NAME_SPACE = "http://www.w3.org/2001/XMLSchema-instance";
    private static String fileName;

    public XmlMessage(String chatName) {
        userMessages = new ArrayList<>();
        fileName = chatName;
    }

    private static boolean readXmlFile(String fileName) {
        boolean readFlag = false;
        Message message = new Message();
        ArrayList<String> toUsers = new ArrayList<>();
        XMLInputFactory inputFactory = XMLInputFactory.newFactory();
        boolean fromFlag, toFlag, dateFlag, contentFlag;
        fromFlag = toFlag = dateFlag = contentFlag = false;
        try {
            XMLStreamReader streamReader = inputFactory.createXMLStreamReader(new FileInputStream("src\\xmlfiles\\"+fileName+".xml"));
            while (streamReader.hasNext()) {
                int eventType = streamReader.getEventType();

                switch (eventType) {

                    case XMLStreamReader.START_ELEMENT:

                        if (streamReader.getLocalName().equals("from")) {
                            fromFlag = true;
                            toFlag = dateFlag = contentFlag = false; 
                        } else if (streamReader.getLocalName().equals("to")) {
                            toFlag = true;
                            fromFlag = dateFlag = contentFlag = false; 
                        } else if (streamReader.getLocalName().equals("date")) {
                            dateFlag = true;
                            fromFlag = toFlag = contentFlag = false; 
                        } else if (streamReader.getLocalName().equals("content")) {
                            contentFlag = true;
                            fromFlag = toFlag = dateFlag = false; 
                        }  
                        
                        if (streamReader.getAttributeCount() > 0) {
                            for (int counter = 0; counter < streamReader.getAttributeCount(); counter++) {

                                if (streamReader.getAttributeLocalName(counter).equals("color")) {
                                    message.setMessageFontColor(streamReader.getAttributeValue(counter));
                                } 
                                
                                else if (streamReader.getAttributeLocalName(counter).equals("font-size")) {
                                    message.setMessageFontSize(streamReader.getAttributeValue(counter));
                                } 
                                
                                else if (streamReader.getAttributeLocalName(counter).equals("font-family")) {
                                    message.setMessageFontFamily(streamReader.getAttributeValue(counter));
                                }
                            }
                        }
                        break;

                    case XMLStreamReader.CHARACTERS:
                        if (!streamReader.getText().isEmpty()) {
                            if(fromFlag){
                                message.setFromUser(streamReader.getText());
                            }
                            
                            else if(toFlag){
                                toUsers.add(streamReader.getText());
                                message.setToUsers(toUsers);
                            }
                            
                            else if(dateFlag){
                                //message.setMessageDate(streamReader.getText());
                            }
                            
                            else if(contentFlag){
                                message.setMessageContent(streamReader.getText());
                            }
                        }
                        break;
                }

                userMessages.add(message);
                streamReader.next();
            }

            streamReader.close();

        } catch (XMLStreamException ex) {
            readFlag = false;
        } catch (FileNotFoundException ex) {
            readFlag = false;
        }
        return readFlag;
    }

    public static boolean appendXmlFile(String chatName, ArrayList<Message> messages) {
        boolean appendFlag = false;
        appendFlag = readXmlFile(chatName);
        return appendFlag && writeXmlFile(messages);
    }

    public static boolean writeXmlFile(ArrayList<Message> messages) {
        boolean writeFlag = false;
        try {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream("src\\xmlfiles\\" + fileName + ".xml"));

            xmlStreamWriter.writeStartDocument("utf-8", "1.0");
            xmlStreamWriter.writeCharacters(System.lineSeparator());

            xmlStreamWriter.writeProcessingInstruction("xml-stylesheet type=" + "\"text/xsl\"", "href=" + "\"src\\xmlfiles\\xmlStyle.xsl\"");

            xmlStreamWriter.writeCharacters(System.lineSeparator());

            // Root(Parent) Tag
            xmlStreamWriter.setDefaultNamespace(NAME_SPACE);
            xmlStreamWriter.writeStartElement(NAME_SPACE, "messages");
            xmlStreamWriter.writeNamespace("xsi", NAME_SPACE);

            xmlStreamWriter.writeAttribute("xsi:noNamespaceSchemaLocation", "schema.xsd");
            xmlStreamWriter.writeCharacters(System.lineSeparator());

            for (Message message : messages) {
                // Writing All Messages 
                xmlStreamWriter.writeStartElement("message");
                xmlStreamWriter.writeCharacters(System.lineSeparator());
                xmlStreamWriter.writeStartElement("from");
                xmlStreamWriter.writeCharacters(message.getFromUser());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters(System.lineSeparator());

                for (int counter = 0; counter < message.getToUsers().size(); counter++) {
                    xmlStreamWriter.writeStartElement("to");
                    xmlStreamWriter.writeCharacters(message.getToUsers().get(counter));
                    xmlStreamWriter.writeEndElement();
                    xmlStreamWriter.writeCharacters(System.lineSeparator());
                }

                xmlStreamWriter.writeStartElement("date");
                //xmlStreamWriter.writeCharacters(message.getMessageDate());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters(System.lineSeparator());

                xmlStreamWriter.writeStartElement("content");
                xmlStreamWriter.writeAttribute("color", message.getMessageFontColor());
                xmlStreamWriter.writeAttribute("font-size", message.getMessageFontSize());
                xmlStreamWriter.writeAttribute("font-family", message.getMessageFontFamily());
                xmlStreamWriter.writeCharacters(message.getMessageContent());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters(System.lineSeparator());

                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters(System.lineSeparator());
            }

            //End of Parent Tag
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.flush();
            xmlStreamWriter.close();

            writeFlag = true;

        } catch (XMLStreamException ex) {
            writeFlag = false;
        } catch (FileNotFoundException ex) {
            writeFlag = false;
        }
        return writeFlag;
    }

}
