package org.itstep;

import org.itstep.service.FileManagerservice;

public class AppRunner {

    private static final String MAIN_DIR = System.getProperty("user.dir");
    private static final String SEPARATOR = System.getProperty("file.separator");

    public static void main(String[] args) {
//-------
        String filePath = MAIN_DIR+SEPARATOR+"files"+SEPARATOR+"img"+SEPARATOR+"cat.jpg";
        String fileCopyPath = MAIN_DIR+SEPARATOR+"files"+SEPARATOR+"img"+SEPARATOR+"cat_copy.jpg";

        FileManagerservice.copyFile(filePath, fileCopyPath);
//-------

        String filePath_txt = MAIN_DIR+SEPARATOR+"files"+SEPARATOR+"doc"+SEPARATOR+"text.txt";
        String fileCopyPath_txt = MAIN_DIR+SEPARATOR+"files"+SEPARATOR+"doc"+SEPARATOR+"text_copy.txt";

        String text = FileManagerservice.getTextFromFile(filePath_txt);
        FileManagerservice.writeTextToFile(fileCopyPath_txt, text, false);
    }
}
