package org.itstep.service;

import java.io.*;
import java.nio.file.Files;

public class FileManagerservice {

    public static byte[] getFileAsByteArray( String filePath){

        byte[] bytes = null;

        try {
            File file = new File(filePath);
            bytes = Files.readAllBytes(file.toPath());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    public static void writeByteArrayToFile( byte[] bytes, String filePath){

        try (FileOutputStream fos = new FileOutputStream(filePath)){
            fos.write(bytes);
            fos.flush();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyFile( String pathFrom, String pathTo){

        byte[] bytes = getFileAsByteArray(pathFrom);
        writeByteArrayToFile(bytes,pathTo);
    }

    public static String getTextFromFile(String filePath_txt){
        String text = "";

        try (
        FileReader fileReader = new FileReader(filePath_txt);
        BufferedReader bufferedreader = new BufferedReader(fileReader);
        )
        {
            String line ;
            while ((line = bufferedreader.readLine()) != null){
                text += line + "\n";
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return text;
    }

    public static void writeTextToFile (String toPath_txt, String text, boolean append){

        try (FileWriter fileWriter = new FileWriter(toPath_txt, append)){
            fileWriter.write(text);
            fileWriter.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
