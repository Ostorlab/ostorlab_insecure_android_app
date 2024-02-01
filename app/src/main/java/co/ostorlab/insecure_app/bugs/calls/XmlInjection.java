package co.ostorlab.insecure_app.bugs.calls;

import android.content.Context;
import java.io.FileOutputStream;
import android.util.Xml;
import org.xmlpull.v1.XmlSerializer;


import co.ostorlab.insecure_app.BugRule;

public class XmlInjection extends BugRule {

    @Override
    public void run(String user_input) throws Exception {

        createXmlFile(user_input);
    }

    @Override
    public String getDescription() {
        return "Inject an xml resource.";
    }

    private void createXmlFile(String user_input) throws Exception {
        // Create a new XML file in the app's internal storage
        FileOutputStream fos = getContext().openFileOutput("injected_data.xml", Context.MODE_PRIVATE);
        String xmlContent = "<root>" + user_input + "</root>";


        // Create an XmlSerializer
        XmlSerializer serializer = Xml.newSerializer();
        serializer.setOutput(fos, "UTF-8");

        Xml.parse(xmlContent, null);

        // Close the output stream
        fos.close();
    }
}


