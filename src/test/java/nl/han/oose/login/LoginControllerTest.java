package nl.han.oose.login;

import nl.han.oose.Account;
import nl.han.oose.AccountToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.security.auth.login.LoginException;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Mock
    private LoginService loginService;

    @InjectMocks
    private Logincontroller sut;


    @Test
    public void testStatusOkOnSuccesfulLogin() throws LoginException {
        AccountToken accountToken = new AccountToken("", "");
        Mockito.when(loginService.login(Mockito.any())).thenReturn(accountToken);
        Account account = new Account("", "");
        Response loginResponce = sut.login(account);

        assertEquals(Response.Status.OK.getStatusCode(), loginResponce.getStatus());
        assertEquals(accountToken, loginResponce.getEntity());
    }
}
