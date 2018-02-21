/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlfiles;

import beans.Message;
import javafx.stage.DirectoryChooser;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author Passant
 */
public class XmlMessage {

    private static ArrayList<Message> userMessages;
    private static final String NAME_SPACE = "http://www.w3.org/2001/XMLSchema-instance";
    private static String fileName;

    public boolean writeXmlFile(String userName, String chatName, Vector<Message> messages) {
        Date date = new Date();
        boolean writeFlag = false;
        File file = getSaveLocation();
        if(file != null)
        try {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream(file.toString()+"\\"+ userName + "_" +chatName+ "_" +date.getTime() + ".xml"));

            xmlStreamWriter.writeStartDocument("1.0");
            xmlStreamWriter.writeCharacters(System.lineSeparator());

            xmlStreamWriter.writeProcessingInstruction("xml-stylesheet type=" + "\"text/xsl\"", "href=" + "\"xmlStyle.xsl\"");

            xmlStreamWriter.writeCharacters(System.lineSeparator());

            // Root(Parent) Tag
            xmlStreamWriter.setDefaultNamespace(NAME_SPACE);
            xmlStreamWriter.writeStartElement(NAME_SPACE, "messages");
            xmlStreamWriter.writeNamespace("xsi", NAME_SPACE);

            xmlStreamWriter.writeAttribute("xsi:noNamespaceSchemaLocation", "schema.xsd");
            xmlStreamWriter.writeAttribute("owner", userName);
            xmlStreamWriter.writeAttribute("chatName", chatName);
            xmlStreamWriter.writeCharacters(System.lineSeparator());

            for (int counter=0; counter<messages.size(); counter++) {
                // Writing All Messages 
                xmlStreamWriter.writeStartElement("message");
                xmlStreamWriter.writeCharacters(System.lineSeparator());
                xmlStreamWriter.writeStartElement("from");
                xmlStreamWriter.writeCharacters(messages.get(counter).getFromUser());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters(System.lineSeparator());

               /* for (int secondCounter = 0; secondCounter < messages.get(counter).getToUsers().size(); secondCounter++) {
                    xmlStreamWriter.writeStartElement("to");
                    xmlStreamWriter.writeCharacters(messages.get(counter).getToUsers().get(secondCounter));
                    xmlStreamWriter.writeEndElement();
                    xmlStreamWriter.writeCharacters(System.lineSeparator());
                }*/

                xmlStreamWriter.writeStartElement("date");
                xmlStreamWriter.writeCharacters(String.valueOf(messages.get(counter).getMessageDate()));
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters(System.lineSeparator());

                xmlStreamWriter.writeStartElement("content");
                xmlStreamWriter.writeAttribute("color", messages.get(counter).getMessageFontColor());
                xmlStreamWriter.writeAttribute("font-size", messages.get(counter).getMessageFontSize());
                xmlStreamWriter.writeAttribute("font-family", messages.get(counter).getMessageFontFamily());
                xmlStreamWriter.writeCharacters(messages.get(counter).getMessageContent());
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



            URL xmlStyle = getClass().getResource("/xml/xmlStyle.xsl");
            URL schema = getClass().getResource("/xml/schema.xsd");
            URL cssFile = getClass().getResource("/xml/messageStyle.css");
            URL pic = getClass().getResource("/xml/Male.png");


            Files.copy(xmlStyle.openStream() , new File(file.toString()+"\\"+"xmlStyle.xsl").toPath() , StandardCopyOption.REPLACE_EXISTING);
            Files.copy(schema.openStream() ,  new File(file.toString()+"\\"+"schema.xsd").toPath() , StandardCopyOption.REPLACE_EXISTING);
            Files.copy(cssFile.openStream() , new File(file.toString()+"\\"+"messageStyle.css").toPath() , StandardCopyOption.REPLACE_EXISTING);
            Files.copy(pic.openStream() , new File(file.toString()+"\\"+"Male.png").toPath() , StandardCopyOption.REPLACE_EXISTING);



        } catch (XMLStreamException | FileNotFoundException  ex) {
            System.out.println(ex.toString());
            writeFlag = false;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return writeFlag;
    }
    private File getSaveLocation() {
        DirectoryChooser chooser = new DirectoryChooser();
        File file = chooser.showDialog(null);

        if (file != null) {
            return file;
        }
        return null;
    }
}
