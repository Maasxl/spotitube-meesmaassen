package nl.han.oose.login;

import nl.han.oose.Account;
import nl.han.oose.AccountToken;
import nl.han.oose.persistance.AccountDAO;
import nl.han.oose.persistance.AccountTokenDAO;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;

public class LoginServiceImpl implements LoginService {

    @Inject
    private AccountDAO accountDAO;

    @Inject
    private AccountTokenDAO accountTokenDAO;

//    private AccountToken token = new AccountToken("1234-1234-1234", "Mees Maassen");

    @Override
    public AccountToken login(Account user) throws LoginException {
        for (Account account : accountDAO.getAllAccounts()) {
            if (user.getUser().equals(account.getUser()) && user.getPassword().equals(account.getPassword())) {
                return accountTokenDAO.getAccountToken(user);
            } else {
                throw new LoginException("Invalid login credentials");
            }
        }
        return null;
    }
}
