package nl.han.oose.persistance.login;

import nl.han.oose.entity.login.Account;
import nl.han.oose.persistance.ConnectionFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    @Inject
    private ConnectionFactory connectionFactory;

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM Account;");
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String user = resultSet.getString("user");
                String password = resultSet.getString("password");
                accounts.add(new Account(user, password));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    public void persistAccount(Account account) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO Account (user,password) VALUES (?,?);");
        ) {
            statement.setString(1, account.getUser());
            statement.setString(2, account.getPassword());

            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Account getAccount(String user) {
        Account account = null;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM account WHERE user = ?");
        ) {
            statement.setString(1, user);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("user");
                String password = resultSet.getString("password");
                account = new Account(username, password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }
}
