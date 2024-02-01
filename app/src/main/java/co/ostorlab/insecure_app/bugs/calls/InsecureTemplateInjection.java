package co.ostorlab.insecure_app.bugs.calls;
import co.ostorlab.insecure_app.BugRule;

import android.content.Context;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.TemplateExceptionHandler;
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
            StringTemplateLoader templateLoader = new StringTemplateLoader();

            String template_content = "<!DOCTYPE html><html><body><h1>Hello, " + user_input + "!</h1></body></html>";
            templateLoader.putTemplate("hardcodedTemplate.ftl", template_content);
            cfg.setTemplateLoader(templateLoader);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);


            // Get the template
            Template template = cfg.getTemplate("hardcodedTemplate.ftl");

            // Create the data model
            StringWriter writer = new StringWriter();
            template.process(null, writer);


            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
