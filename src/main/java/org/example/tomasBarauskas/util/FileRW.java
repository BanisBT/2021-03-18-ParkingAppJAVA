package org.example.tomasBarauskas.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public interface FileRW<E extends Object> {

    default void writeObjectDetailsToFile(String path, List<E> list) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for (int i = 0; i < list.size(); i++) {
            oos.writeObject(list.get(i));
        }
        oos.flush();
        oos.close();
    }

    List<E> getDetailsFromFile1(String path) throws IOException;
}
