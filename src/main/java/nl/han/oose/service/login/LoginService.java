package nl.han.oose.service.login;

import nl.han.oose.entity.login.Account;
import nl.han.oose.entity.login.AccountToken;

import javax.security.auth.login.LoginException;

public interface LoginService {
    AccountToken login(Account user) throws LoginException;
}
