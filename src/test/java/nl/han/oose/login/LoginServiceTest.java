package nl.han.oose.login;

import nl.han.oose.Account;
import nl.han.oose.AccountToken;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.security.auth.login.LoginException;

import static org.junit.Assert.assertEquals;

public class LoginServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private LoginService sut;

    @Before
    public void setUp() throws Exception {
        sut = new LoginServiceImpl();
    }

    @Test
    public void testSuccesfulLogin() throws LoginException {
        Account account = new Account("mees", "wachtwoord");
        AccountToken login = sut.login(account);
        assertEquals("Mees Maassen", login.getUser());
        assertEquals("1234-1234-1234", login.getToken());
    }

    @Test
    public void testFailedLogin() throws LoginException {
        thrown.expect(LoginException.class);
        thrown.expectMessage("Invalid login credentials");
        Account account = new Account("kees", "wachtwoord");
        sut.login(account);
    }

}
