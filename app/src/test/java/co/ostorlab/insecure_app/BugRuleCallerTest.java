package co.ostorlab.insecure_app;


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
import co.ostorlab.insecure_app.bugs.calls.ClearTextTraffic;
import co.ostorlab.insecure_app.bugs.calls.InsecureFilePermissions;
import co.ostorlab.insecure_app.bugs.calls.DexClassLoaderCall;
import co.ostorlab.insecure_app.bugs.calls.ECBModeCipher;
import co.ostorlab.insecure_app.bugs.calls.PathClassLoaderCall;
import co.ostorlab.insecure_app.bugs.calls.StaticIV;
import co.ostorlab.insecure_app.bugs.calls.TLSTraffic;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BugRuleCallerTest {

    private static final String TEMP_DIR = "/tmp/";

    private BugRuleCaller caller;
    @Mock private Context context;
    @Mock private FileOutputStream fileOutputStream;

    @Before
    public void before() throws Exception{
        caller = new BugRuleCaller(context);
        when(context.openFileOutput(anyString(), anyInt())).thenReturn(fileOutputStream);
        when(context.getFilesDir()).thenReturn(new File(TEMP_DIR));
    }

    @Test
    public void ruleCaller_whenCalled_allRulesAreCalled() throws Exception{
        BugRule bugRule = Mockito.mock(BugRule.class);
        caller.addRule(bugRule);
        caller.callRules();

        verify(bugRule, Mockito.times(1)).run();
    }

    @Test
    public void ruleCaller_callAllRules_success() throws Exception{
        caller.addRule(new ECBModeCipher());
        caller.addRule(new ClearTextTraffic());
        caller.addRule(new TLSTraffic());
        caller.addRule(new AESCipher());
        caller.addRule(new StaticIV());
        caller.addRule(new PathClassLoaderCall());
        caller.addRule(new DexClassLoaderCall());
        caller.addRule(new InsecureFilePermissions());
        caller.callRules();

        Assert.assertEquals(caller.getRules().size(), 8);

    }


}
