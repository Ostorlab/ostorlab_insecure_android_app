package co.ostorlab.insecure_app;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import co.ostorlab.insecure_app.bugs.calls.AESCipher;
import co.ostorlab.insecure_app.bugs.calls.ClearTextTraffic;
import co.ostorlab.insecure_app.bugs.calls.DexClassLoaderCall;
import co.ostorlab.insecure_app.bugs.calls.ECBModeCipher;
import co.ostorlab.insecure_app.bugs.calls.PathClassLoaderCall;
import co.ostorlab.insecure_app.bugs.calls.StaticIV;
import co.ostorlab.insecure_app.bugs.calls.TLSTraffic;

import static org.mockito.Mockito.verify;

public class BugRuleCallerTest {

    private BugRuleCaller caller;

    @Before
    public void before(){
        caller = new BugRuleCaller();
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
        caller.callRules();

        Assert.assertEquals(caller.getRules().size(), 7);

    }


}
