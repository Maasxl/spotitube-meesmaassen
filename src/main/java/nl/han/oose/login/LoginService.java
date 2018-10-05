package nl.han.oose.login;

import nl.han.oose.Account;
import nl.han.oose.AccountToken;

import javax.security.auth.login.LoginException;

public interface LoginService {
    AccountToken login(Account user) throws LoginException;
}
