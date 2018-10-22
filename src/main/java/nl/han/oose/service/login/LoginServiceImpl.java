package nl.han.oose.service.login;

import nl.han.oose.entity.login.Account;
import nl.han.oose.entity.login.AccountToken;
import nl.han.oose.persistance.login.AccountDAO;
import nl.han.oose.persistance.login.AccountTokenDAO;

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
