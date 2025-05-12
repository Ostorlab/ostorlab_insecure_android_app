package co.ostorlab.insecure_app;


import android.app.Activity;
import android.content.Context;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.FileOutputStream;

import co.ostorlab.insecure_app.bugs.calls.AESCipher;
import co.ostorlab.insecure_app.bugs.calls.BiometricFingerprintPromptVulnerability;
import co.ostorlab.insecure_app.bugs.calls.ClearTextTraffic;
import co.ostorlab.insecure_app.bugs.calls.ImplicitPendingIntent;
import co.ostorlab.insecure_app.bugs.calls.InsecureFilePermissions;
import co.ostorlab.insecure_app.bugs.calls.DexClassLoaderCallVulnerability;
import co.ostorlab.insecure_app.bugs.calls.ECBModeCipher;
import co.ostorlab.insecure_app.bugs.calls.InsecureRandom;
import co.ostorlab.insecure_app.bugs.calls.IntentCall;
import co.ostorlab.insecure_app.bugs.calls.MobileOnlyDownloadManager;
import co.ostorlab.insecure_app.bugs.calls.ParcelableMemoryCorruption;
import co.ostorlab.insecure_app.bugs.calls.PathClassLoaderCall;
import co.ostorlab.insecure_app.bugs.calls.PathTraversalVulnerability;
import co.ostorlab.insecure_app.bugs.calls.SecurePathTraversal;
import co.ostorlab.insecure_app.bugs.calls.SerializableMemoryCorruption;
import co.ostorlab.insecure_app.bugs.calls.StaticIV;
import co.ostorlab.insecure_app.bugs.calls.HardcodedUrlInUrl;
import co.ostorlab.insecure_app.bugs.calls.TLSTraffic;
import co.ostorlab.insecure_app.bugs.calls.WebviewInsecureSettings;
import co.ostorlab.insecure_app.bugs.calls.ArrayCall;
import co.ostorlab.insecure_app.bugs.calls.SQLiteDatabaseCall;
import co.ostorlab.insecure_app.bugs.calls.BiometricFingerprintManagerVulnerability;
import co.ostorlab.insecure_app.bugs.calls.PackageContextCall;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import androidx.fragment.app.FragmentActivity;

@RunWith(MockitoJUnitRunner.class)
public class BugRuleCallerTest {

    private static final String TEMP_DIR = "/tmp/";
    FragmentActivity mockActivity = mock(FragmentActivity.class);

    private BugRuleCaller caller;
    @Mock private Context context;
    @Mock private FileOutputStream fileOutputStream;

    @Before
    public void before() throws Exception{
        caller = new BugRuleCaller(context);
        when(context.openFileOutput(anyString(), anyInt())).thenReturn(fileOutputStream);
        when(context.getFilesDir()).thenReturn(new File(TEMP_DIR));
    }

//    @Test
//    public void ruleCaller_whenCalled_rulesCalled() throws Exception{
//        BugRule bugRule = Mockito.mock(BugRule.class);
//        caller.addRule(bugRule);
//        caller.callRules("");
//
//        verify(bugRule, Mockito.times(1)).run();
//    }

    @Test
    public void ruleCaller_callECBModeCipher_NoExceptionThrown() throws Exception{
        caller.addRule(new ECBModeCipher());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_callClearTextTraffic_NoExceptionThrown() throws Exception{
        caller.addRule(new ClearTextTraffic());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }
    @Test
    public void ruleCaller_callTLSTraffic_NoExceptionThrown() throws Exception{
        caller.addRule(new TLSTraffic());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }
    @Test
    public void ruleCaller_callAESCipher_NoExceptionThrown() throws Exception{
        caller.addRule(new AESCipher());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }
    @Test
    public void ruleCaller_callStaticIV_NoExceptionThrown() throws Exception{
        caller.addRule(new StaticIV());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }
    @Test
    public void ruleCaller_callHardcodedKeyInUrl_NoExceptionThrown() throws Exception{
        caller.addRule(new HardcodedUrlInUrl());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }
    @Test
    public void ruleCaller_callPathClassLoader_NoExceptionThrown() throws Exception{
        caller.addRule(new PathClassLoaderCall());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }
    @Test
    public void ruleCaller_callDexClassLoader_NoExceptionThrown() throws Exception{
        caller.addRule(new DexClassLoaderCallVulnerability());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }
    @Test
    public void ruleCaller_callInsecureFilePermissions_NoExceptionThrown() throws Exception{
        caller.addRule(new InsecureFilePermissions());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_callWebviewInsecureSettings_NoExceptionThrown() throws Exception{
        caller.addRule(new WebviewInsecureSettings());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_callMobileOnlyDownloadManager_NoExceptionThrown() throws Exception{
        caller.addRule(new MobileOnlyDownloadManager());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_callInsecureRandom_NoExceptionThrown() throws Exception{
        caller.addRule(new InsecureRandom());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_ArrayCall_NoExceptionThrown() throws Exception{
        caller.addRule(new ArrayCall());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_SQLiteDatabaseCall_NoExceptionThrown() throws Exception{
        caller.addRule(new SQLiteDatabaseCall());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_IntentCall_NoExceptionThrown() throws Exception{
        caller.addRule(new IntentCall());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_SerializableMemoryCorruption_NoExceptionThrown() throws Exception{
        caller.addRule(new SerializableMemoryCorruption());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_PathTraversalVulnerability_NoExceptionThrown() throws Exception{
        caller.addRule(new PathTraversalVulnerability());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_ParcelableMemoryCorruption_NoExceptionThrown() throws Exception{
        caller.addRule(new ParcelableMemoryCorruption());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_ImplicitPendingIntentVulnerability_NoExceptionThrown() throws Exception{
        caller.addRule(new ImplicitPendingIntent());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_BiometricFingerprintManagerVulnerability_NoExceptionThrown() throws Exception{
        caller.addRule(new BiometricFingerprintManagerVulnerability());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_BiometricFingerprintPromptVulnerability_NoExceptionThrown() throws Exception{
        caller.addRule(new BiometricFingerprintPromptVulnerability(mockActivity));
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_PackageContext_NoExceptionThrown() throws Exception{
        caller.addRule(new PackageContextCall());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }

    @Test
    public void ruleCaller_SecurePathTraversal_NoExceptionThrown() throws Exception{
        caller.addRule(new SecurePathTraversal());
        caller.callRules("");

        Assert.assertEquals(caller.getRules().size(), 1);
    }
}
