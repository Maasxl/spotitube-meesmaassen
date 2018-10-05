package nl.han.oose.login;

import nl.han.oose.Account;
import nl.han.oose.AccountToken;

import javax.security.auth.login.LoginException;

public class LoginService {

    private AccountToken token = new AccountToken("1234-1234-1234", "Mees Maassen");
    private Account account = new Account("mees", "wachtwoord");

    public AccountToken login(Account user) throws LoginException {
        if (user.getUser().equals(account.getUser()) && user.getPassword().equals(account.getPassword())) {
            return token;
        } else {
            throw new LoginException("Invalid login credentials");
        }
    }
}
