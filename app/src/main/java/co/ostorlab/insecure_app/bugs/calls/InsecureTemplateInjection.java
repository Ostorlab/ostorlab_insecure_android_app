package co.ostorlab.insecure_app.bugs.calls;
import co.ostorlab.insecure_app.BugRule;

import android.content.Context;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;


public final class InsecureTemplateInjection extends BugRule {

    private static final String TAG = "RULE";
    private Context appContext;

    public InsecureTemplateInjection(Context context){
        appContext = context.getApplicationContext();
    }

    @Override
    public String getDescription() {
        return "Insecure template injection.";
    }

    @Override
    public void run(String user_input) throws Exception {
        TemplateRenderer templateRenderer = new TemplateRenderer();

        String templateFileName = "template.ftl";
        String renderedTemplate = templateRenderer.renderTemplate(appContext, templateFileName, user_input);
    }
}

class TemplateRenderer {

    public String renderTemplate(Context context, String templateFileName, String user_input) {
        try {
            // Create FreeMarker configuration
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            cfg.setTemplateLoader(new ClassTemplateLoader(getClass(), "/"));

            // Get the template
            Template template = cfg.getTemplate(templateFileName);

            // Create the data model
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("message", user_input);

            // Render the template
            StringWriter writer = new StringWriter();
            template.process(dataModel, writer);

            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
