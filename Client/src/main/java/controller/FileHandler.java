package controller;

import beans.FileObject;
import model.ServerConnection;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileHandler {

    public synchronized void splitFile(File f  ,String sender , String receiver , File locationToSave) throws IOException {

        int partCounter = 1;//I like to name parts from 001, 002, 003, ...

        //you can change it to 0 if you want 000, 001, ...
        int sizeOfFiles = 5120*1024;// 1MB

        byte[] buffer = new byte[sizeOfFiles];

        String fileName = f.getName();

        //try-with-resources to ensure closing stream
        try (FileInputStream fis = new FileInputStream(f); BufferedInputStream bis = new BufferedInputStream(fis)) {

            int bytesAmount = 0;
            while ((bytesAmount = bis.read(buffer)) > 0) {

                //write each chunk of data into separate file with different number in name
                String filePartName = String.format("%s.%03d", fileName, partCounter++);
                FileObject fileObject = new FileObject(buffer , filePartName , bytesAmount , locationToSave ,
                        new File(locationToSave.toString() +f.getName()));
                ServerConnection.getInstance().getRegisteryObject().getServerFileTransfer().sendFileParts(sender , receiver , fileObject , false);

            }
            String filePartName = String.format("%s.%03d", fileName, 1);
            System.out.println(locationToSave.toString());
            ServerConnection.getInstance().getRegisteryObject().getServerFileTransfer().sendFileParts(sender , receiver ,
                    new FileObject(new File(locationToSave.toString()+"\\"+ filePartName) ,   new File(locationToSave.toString() +"\\"+f.getName())) , true);

        }
    }


    public void retrieveFile(FileObject fileObject) throws IOException {

        File newFile = new File(fileObject.getLocationToSave(), fileObject.getPartName());
        try (FileOutputStream out = new FileOutputStream(newFile)) {
            System.out.println("Sending");
            out.write(fileObject.getFileByte(), 0, fileObject.getByteAmount());
        }
    }
    public void mergeFiles(List<File> files, File into) throws IOException {

        System.out.println(into.toString());

        try (FileOutputStream fos = new FileOutputStream(into);
             BufferedOutputStream mergingStream = new BufferedOutputStream(fos)) {
            for (File f : files) {
                System.out.println(f.toString());
                Files.copy(f.toPath(), mergingStream);
                f.delete();
            }
        }
    }

    public List<File> listOfFilesToMerge(File oneOfFiles) {

        System.out.println("oneOfFiles :"+oneOfFiles.toString());
        String tmpName = oneOfFiles.getName();//{name}.{number}
        String destFileName = tmpName.substring(0, tmpName.lastIndexOf('.'));//remove .{number}
        ArrayList<File> files = new ArrayList<>() ;
        for(File file : oneOfFiles.getParentFile().listFiles())
        {
            System.out.println(file.toString());
            String fileName = file.getName();
            if(fileName.substring(fileName.length()-4).trim().matches( "[.]\\d+")
                    && fileName.substring(0, fileName.lastIndexOf('.')).equals(destFileName))
            {
                System.out.println("Inside File : "+file.toString());
                files.add(file);
            }
        }

        System.out.println("destFileName :"+destFileName);
        System.out.println("Parent :"+oneOfFiles.getParentFile());
        Collections.sort(files);//ensuring order 001, 002, ..., 010, ...

        return files;
    }
}
