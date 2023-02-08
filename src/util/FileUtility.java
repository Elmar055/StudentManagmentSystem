package util;

import java.io.*;

public class FileUtility {

    public static boolean writeObjectToFile(Serializable object, String filename) {
        try(FileOutputStream fout = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fout)) {
            oos.writeObject(object);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static Object readFileDeserialize(String name) throws RuntimeException{
        Object obj = null;
        try{
            ObjectInputStream obin = new ObjectInputStream(new FileInputStream(name));
            obj = obin.readObject();
        }finally {
            return obj;
        }
    }

}
