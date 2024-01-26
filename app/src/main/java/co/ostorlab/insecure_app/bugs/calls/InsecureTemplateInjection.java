package co.ostorlab.insecure_app.bugs.calls;
import co.ostorlab.insecure_app.BugRule;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import android.content.res.Resources;
import java.io.StringWriter;


public final class InsecureTemplateInjection extends BugRule {

    private static final String TAG = "RULE";
    private Context appContext;

    public InsecureTemplateInjection(Context context){
        appContext = context.getApplicationContext();
    }

    @Override
    public String getDescription() {
        return "insecure template injection.";
    }

    @Override
    public void run(String user_input) throws Exception {

        TemplateRenderer templateRenderer = new TemplateRenderer();

        String templateFileName = "template.mustache";
        String renderedTemplate = templateRenderer.renderTemplate(appContext.getApplicationContext(), templateFileName, user_input);
    }

}

class TemplateRenderer {

    public String renderTemplate(Context context, String templateFileName, String user_input) {
        try {
            // Load the template from assets
            InputStream inputStream = context.getAssets().open(templateFileName);
            InputStreamReader reader = new InputStreamReader(inputStream);

            // Compile the Mustache template
            MustacheFactory mf = new DefaultMustacheFactory();
            Mustache mustache = mf.compile(reader, templateFileName);

            // Render the template with the provided data model
            return executeTemplate(mustache, user_input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String executeTemplate(Mustache mustache, String user_input) {
        java.util.Map<String, Object> context = new java.util.HashMap<>();
        StringWriter writer = new StringWriter();

        context.put("message", user_input);
        return mustache.execute(writer, context).toString();
    }
}