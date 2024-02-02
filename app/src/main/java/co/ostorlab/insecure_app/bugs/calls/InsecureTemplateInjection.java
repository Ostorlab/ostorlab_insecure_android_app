package co.ostorlab.insecure_app.bugs.calls;
import co.ostorlab.insecure_app.BugRule;

import android.content.Context;
import freemarker.template.Configuration;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import java.io.StringWriter;


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

        String renderedTemplate = templateRenderer.renderTemplate(appContext, user_input);
    }
}

class TemplateRenderer {

    public String renderTemplate(Context context, String user_input) {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
            StringTemplateLoader templateLoader = new StringTemplateLoader();
            String template_content = "<!DOCTYPE html><html><body><h1>Hello, " + user_input + "!</h1></body></html>";

            templateLoader.putTemplate("hardcodedTemplate.ftl", template_content);
            cfg.setTemplateLoader(templateLoader);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            Template template = cfg.getTemplate("hardcodedTemplate.ftl");
            StringWriter writer = new StringWriter();

            template.process(null, writer);
            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
