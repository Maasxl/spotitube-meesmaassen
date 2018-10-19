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

    @Override
    public AccountToken login(Account user) throws LoginException {
        Account account = accountDAO.getAccount(user.getUser());
        if (account != null && user.getPassword().equals(account.getPassword())) {
            accountTokenDAO.deleteExpiredTokens();
                return accountTokenDAO.createNewTokenForAccount(user.getUser());
            } else {
                throw new LoginException("Invalid login credentials");
            }
        }
}
