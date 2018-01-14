package co.ostorlab.insecure_app;


abstract public class BugRule {

    private static String DESCRIPTION;
    abstract public void run() throws Exception;

    /** Return rule DESCRIPTION. */
    String getDescription(){
        return DESCRIPTION;
    }


}
