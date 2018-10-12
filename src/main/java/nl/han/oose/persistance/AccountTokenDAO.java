package nl.han.oose.persistance;

import nl.han.oose.Account;
import nl.han.oose.AccountToken;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountTokenDAO {

    private ConnectionFactory connectionFactory = new ConnectionFactory();

    public AccountToken getAccountToken(Account account) {
        AccountToken returnToken;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM AccountToken WHERE user = ?;");
        ) {
            statement.setString(1, account.getUser());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                returnToken = new AccountToken(resultSet.getString("token"), resultSet.getString("user"));
                return returnToken;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
