package org.itstep.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerserviceTest {

    private static String fromPath_jpg;
    private static String toPath_jpg;

    private static String fromPath_txt;
    private static String toPath_txt;

    @BeforeAll
    static  void setPreData(){
        String MAIN_DIR = System.getProperty("user.dir");
        String SEPARATOR = System.getProperty("file.separator");

        fromPath_jpg = MAIN_DIR+SEPARATOR+"files"+SEPARATOR+"img"+SEPARATOR+"cat.jpg";
        toPath_jpg = MAIN_DIR+SEPARATOR+"files"+SEPARATOR+"img"+SEPARATOR+"cat_test_copy.jpg";

        fromPath_txt = MAIN_DIR+SEPARATOR+"files"+SEPARATOR+"doc"+SEPARATOR+"text.txt";
        toPath_txt = MAIN_DIR+SEPARATOR+"files"+SEPARATOR+"doc"+SEPARATOR+"text_test.txt";

    }

    @Test
    void testGetFileAsByteArray_JPG() {
        byte[] bytes = FileManagerservice.getFileAsByteArray(fromPath_jpg);
        assertNotNull(bytes);
        assertTrue(bytes.length>0);

    }

    @Test
    void testwriteByteArrayToFile() {
        byte[] bytes = FileManagerservice.getFileAsByteArray(fromPath_jpg);
        FileManagerservice.writeByteArrayToFile(bytes, toPath_jpg);
        byte[] testBytes = FileManagerservice.getFileAsByteArray(toPath_jpg);

        assertNotNull(testBytes);
        assertTrue(testBytes.length>0);

    }

    @Test
    void testgetTextFromFile(){
        String text = FileManagerservice.getTextFromFile(fromPath_txt);

        assertNotNull(text);
        assertTrue(text.contains("#line 1\n"));
        assertTrue(text.contains("#line 2\n"));
        assertTrue(text.contains("#line 3\n"));
    }

    @Test
    void testWriteTextToFileWithoutAppend(){
        String text = "some test text\n" +
                      "need to be writen to file.";

        FileManagerservice.writeTextToFile(toPath_txt, text,false);

        String testText = FileManagerservice.getTextFromFile(toPath_txt);

        assertNotNull(testText);
        assertEquals(testText, text + "\n");

        FileManagerservice.writeTextToFile(toPath_txt, text,false);

        testText = FileManagerservice.getTextFromFile(toPath_txt);

        assertNotNull(testText);
        assertEquals(testText, text + "\n");
    }

    @Test
    void testWriteTextToFileWithAppend(){
        String text = "some test text\n" +
                "need to be writen to file.";

        FileManagerservice.writeTextToFile(toPath_txt, text,false);

        String testText = FileManagerservice.getTextFromFile(toPath_txt);

        assertNotNull(testText);
        assertEquals(testText, text + "\n");

        FileManagerservice.writeTextToFile(toPath_txt, text,true);

        testText = FileManagerservice.getTextFromFile(toPath_txt);

        assertNotNull(testText);
//Bug_report in WorkWithFiles/files/doc/bug_report
        assertEquals(testText, text + "\n" + text + "\n");
    }

    @AfterAll
    static void removeTestFiles(){
        File file_jpg = new File(toPath_jpg);
        if (file_jpg.isFile()) {
            try {
                Files.delete(file_jpg.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File file_txt = new File(toPath_txt);
        if (file_txt.isFile()) {
            try {
                Files.delete(file_txt.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}