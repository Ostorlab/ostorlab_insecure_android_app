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
        private static final long serialVersionUID = 0L;

        Object looselyDefinedThing;
        String methodName;

        private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
            ois.defaultReadObject();
            try {
                Method method = looselyDefinedThing.getClass().getMethod(methodName);
                method.invoke(looselyDefinedThing);
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
    public void run() throws Exception {
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
