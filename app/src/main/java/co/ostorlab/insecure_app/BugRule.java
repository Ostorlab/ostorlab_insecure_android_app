package co.ostorlab.insecure_app;

import android.content.Context;

abstract public class BugRule {

    private Context context;

    public void setContext(Context context){ this.context = context;}
    public Context getContext(){ return context;}
    abstract public void run() throws Exception;
    abstract public String getDescription();
    public String toString()
    {
        return this.getClass().getSimpleName();
    }
}
