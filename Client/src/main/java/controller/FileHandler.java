package controller;

import beans.FileObject;
import model.ServerConnection;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class FileHandler {

    public synchronized void splitFile(File f  ,String sender , String receiver , File locationToSave) throws IOException {

        int partCounter = 1;//I like to name parts from 001, 002, 003, ...
        //you can change it to 0 if you want 000, 001, ...
        int sizeOfFiles = 1024*1024;// 1MB
        byte[] buffer = new byte[sizeOfFiles];

        String fileName = f.getName();

        //try-with-resources to ensure closing stream
        try (FileInputStream fis = new FileInputStream(f); BufferedInputStream bis = new BufferedInputStream(fis)) {

            int bytesAmount = 0;
            while ((bytesAmount = bis.read(buffer)) > 0) {

                //write each chunk of data into separate file with different number in name
                String filePartName = String.format("%s.%03d", fileName, partCounter++);
                FileObject fileObject = new FileObject(buffer , filePartName , bytesAmount , locationToSave);
                ServerConnection.getInstance().getRegisteryObject().getServerFileTransfer().sendFileParts(sender , receiver , fileObject);

            }
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

        try (FileOutputStream fos = new FileOutputStream(into);
             BufferedOutputStream mergingStream = new BufferedOutputStream(fos)) {
            for (File f : files) {
                Files.copy(f.toPath(), mergingStream);
            }
        }
    }

    public List<File> listOfFilesToMerge(File oneOfFiles) {
        String tmpName = oneOfFiles.getName();//{name}.{number}
        String destFileName = tmpName.substring(0, tmpName.lastIndexOf('.'));//remove .{number}
        File[] files = oneOfFiles.getParentFile().listFiles(
                (File dir, String name) -> name.matches(destFileName + "[.]\\d+"));
        Arrays.sort(files);//ensuring order 001, 002, ..., 010, ...
        return Arrays.asList(files);
    }
}
