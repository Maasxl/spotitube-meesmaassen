package nl.han.oose.login;

import nl.han.oose.entity.login.Account;
import nl.han.oose.entity.login.AccountToken;
import nl.han.oose.persistance.login.AccountDAO;
import nl.han.oose.service.login.LoginService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.security.auth.login.LoginException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    private LoginService sut;

    @Mock
    private AccountDAO accountDAOMock;

    @Test
    public void testSuccessfulLogin() throws LoginException {
        Account account = new Account("mees", "wachtwoord");
        AccountToken login = sut.login(account);
        assertEquals("mees", login.getUser());
        assertEquals("1234-1234-1234", login.getToken());
    }

    @Test
    public void testFailedLogin() throws LoginException {
        thrown.expect(LoginException.class);
        thrown.expectMessage("Invalid login credentials");
        Account account = new Account("mees", "wachtwoord10");
        sut.login(account);
    }

}
