package com.next.io;

import java.io.InputStream;

public class FileReader {

    public static InputStream getFile(String path) {
        return FileReader.class.getResourceAsStream(path);
    }
}
