package nl.han.oose.service.login;

import nl.han.oose.entity.login.Account;
import nl.han.oose.entity.login.AccountToken;
import nl.han.oose.persistance.login.AccountDAO;
import nl.han.oose.persistance.login.AccountTokenDAO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.security.auth.login.LoginException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private AccountDAO accountDAOMock;

    @Mock
    private AccountTokenDAO accountTokenDAOMock;

    @InjectMocks
    private LoginServiceImpl sut;

    @Test
    public void testSuccessfulLogin() throws LoginException {
        Account account = new Account("mees", "meespass");
        AccountToken accountToken = new AccountToken("1234-1234-1234", "mees");
        Mockito.when(accountDAOMock.getAccount(Mockito.any())).thenReturn(account);
        Mockito.when(accountTokenDAOMock.createNewTokenForAccount(Mockito.any())).thenReturn(accountToken);
        sut.login(account);
        assertEquals("mees", accountToken.getUser());
        assertEquals("1234-1234-1234", accountToken.getToken());
    }

}
