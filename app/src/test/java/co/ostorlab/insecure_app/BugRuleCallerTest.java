package co.ostorlab.insecure_app;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import co.ostorlab.insecure_app.bugs.calls.ClearTextTraffic;
import co.ostorlab.insecure_app.bugs.calls.ECBModeCipher;
import co.ostorlab.insecure_app.bugs.calls.TLSTraffic;

import static org.mockito.Mockito.verify;

public class BugRuleCallerTest {

    private BugRuleCaller bugRuleCaller;

    @Before
    public void before(){
        bugRuleCaller = new BugRuleCaller();
    }

    @Test
    public void ruleCaller_whenCalled_allRulesAreCalled() throws Exception{
        BugRule bugRule = Mockito.mock(BugRule.class);
        bugRuleCaller.addRule(bugRule);
        bugRuleCaller.callRules();

        verify(bugRule, Mockito.times(1)).run();
    }

    @Test
    public void ruleCaller_callAllRules_success() throws Exception{
        bugRuleCaller.addRule(new ECBModeCipher());
        bugRuleCaller.addRule(new ClearTextTraffic());
        bugRuleCaller.addRule(new TLSTraffic());
        bugRuleCaller.callRules();
    }


}
