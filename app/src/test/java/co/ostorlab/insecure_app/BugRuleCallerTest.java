package co.ostorlab.insecure_app;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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
}
