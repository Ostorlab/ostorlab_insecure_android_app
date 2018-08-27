package co.ostorlab.insecure_app;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.ostorlab.insecure_app.bugs.calls.AESCipher;
import co.ostorlab.insecure_app.bugs.calls.ClearTextTraffic;
import co.ostorlab.insecure_app.bugs.calls.InsecureCommands;
import co.ostorlab.insecure_app.bugs.calls.DexClassLoaderCall;
import co.ostorlab.insecure_app.bugs.calls.ECBModeCipher;
import co.ostorlab.insecure_app.bugs.calls.InsecureFilePermissions;
import co.ostorlab.insecure_app.bugs.calls.InsecureRandom;
import co.ostorlab.insecure_app.bugs.calls.InsecureSharedPreferences;
import co.ostorlab.insecure_app.bugs.calls.IntentCall;
import co.ostorlab.insecure_app.bugs.calls.MobileOnlyDownloadManager;
import co.ostorlab.insecure_app.bugs.calls.PathClassLoaderCall;
import co.ostorlab.insecure_app.bugs.calls.StaticIV;
import co.ostorlab.insecure_app.bugs.calls.TLSTraffic;
import co.ostorlab.insecure_app.bugs.calls.WebviewInsecureSettings;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    private BugRuleCaller caller;

    @Before
    public void before() throws Exception{
        caller = new BugRuleCaller(InstrumentationRegistry.getContext());
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("co.ostorlab.insecure_app", appContext.getPackageName());
    }

    @Test
    public void ruleCaller_callECBModeCipher_NoExceptionThrown() throws Exception{
        caller.addRule(new ECBModeCipher());
        caller.callRules();

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_callClearTextTraffic_NoExceptionThrown() throws Exception{
        caller.addRule(new ClearTextTraffic());
        caller.callRules();

        Assert.assertEquals(caller.getRules().size(), 1);
    }
    @Test
    public void ruleCaller_callTLSTraffic_NoExceptionThrown() throws Exception{
        caller.addRule(new TLSTraffic());
        caller.callRules();

        Assert.assertEquals(caller.getRules().size(), 1);
    }
    @Test
    public void ruleCaller_callAESCipher_NoExceptionThrown() throws Exception{
        caller.addRule(new AESCipher());
        caller.callRules();

        Assert.assertEquals(caller.getRules().size(), 1);
    }
    @Test
    public void ruleCaller_callStaticIV_NoExceptionThrown() throws Exception{
        caller.addRule(new StaticIV());
        caller.callRules();

        Assert.assertEquals(caller.getRules().size(), 1);
    }
    @Test
    public void ruleCaller_callPathClassLoader_NoExceptionThrown() throws Exception{
        caller.addRule(new PathClassLoaderCall());
        caller.callRules();

        Assert.assertEquals(caller.getRules().size(), 1);
    }
    @Test
    public void ruleCaller_callDexClassLoader_NoExceptionThrown() throws Exception{
        caller.addRule(new DexClassLoaderCall());
        caller.callRules();

        Assert.assertEquals(caller.getRules().size(), 1);
    }
    @Test
    public void ruleCaller_callInsecureFilePermissions_NoExceptionThrown() throws Exception{
        caller.addRule(new InsecureFilePermissions());
        caller.callRules();

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_callInsecureSharedPreferences_NoExceptionThrown() throws Exception{
        caller.addRule(new InsecureSharedPreferences());
        caller.callRules();

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_callInsecureCommands_NoExceptionThrown() throws Exception{
        caller.addRule(new InsecureCommands());
        caller.callRules();

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_callWebviewInsecureSettings_NoExceptionThrown() throws Exception{
        caller.addRule(new WebviewInsecureSettings());
        caller.callRules();

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_callMobileOnlyDownloadManager_NoExceptionThrown() throws Exception{
        caller.addRule(new MobileOnlyDownloadManager());
        caller.callRules();

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_callInsecureRandom_NoExceptionThrown() throws Exception{
        caller.addRule(new InsecureRandom());
        caller.callRules();

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_callIntent_NoExceptionThrown() throws Exception{
        caller.addRule(new IntentCall());
        caller.callRules();

        Assert.assertEquals(caller.getRules().size(), 1);
    }
}
