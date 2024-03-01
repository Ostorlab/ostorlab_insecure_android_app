package co.ostorlab.insecure_app.bugs.calls;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;

import co.ostorlab.insecure_app.BugRule;

public class SerializableMemoryCorruption extends BugRule {
    public class SerializableObject implements Serializable{

        {
            System.loadLibrary("native-lib");
        }

        private static final long serialVersionUID = 0L;
        private long ptr;

        private native void freePtr(long ptr);

        private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
            ois.defaultReadObject();
            try {
                freePtr(ptr);
            } catch (Exception e) {
                // handle error...
                e.printStackTrace();
            }
        }

        private void writeObject(ObjectOutputStream oos) throws IOException {
            oos.defaultWriteObject();
            // Custom attribute getting
        }
    }

    @Override
    public void run(String user_input) throws Exception {
        if (user_input.isEmpty() == false){
            FileInputStream fileInputStream = new FileInputStream(user_input);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            SerializableObject serializableObject = new SerializableObject();
            serializableObject.readObject(objectInputStream);
        }

        String fileName = "";
        FileInputStream fileInputStream = new FileInputStream(fileName);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        SerializableObject serializableObject = new SerializableObject();
        serializableObject.readObject(objectInputStream);
    }

    @Override
    public String getDescription() {
        return "Deserialization Vulnerabilities";
    }
}
