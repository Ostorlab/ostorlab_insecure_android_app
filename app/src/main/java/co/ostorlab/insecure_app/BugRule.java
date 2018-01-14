package co.ostorlab.insecure_app;


abstract public class BugRule {

    abstract public void run() throws Exception;
    abstract public String getDescription();
}
