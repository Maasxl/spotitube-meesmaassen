package nl.han.oose.persistance.login;

import nl.han.oose.entity.login.AccountToken;
import nl.han.oose.persistance.ConnectionFactory;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class AccountTokenDAO {

    @Inject
    private ConnectionFactory connectionFactory;

    public AccountToken createNewTokenForAccount(String user) {
        AccountToken accountToken;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO accounttoken (user, token, expiry_date) VALUES (?,?,?)"
                );
        ) {
            String token = UUID.randomUUID().toString();
            LocalDateTime expiryDate = LocalDateTime.now().plusHours(24);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, token);
            preparedStatement.setString(3, expiryDate.toString());
            preparedStatement.execute();

            accountToken = new AccountToken(token, user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountToken;
    }

    public AccountToken getAccountToken(String token) {
        AccountToken returnToken;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounttoken WHERE token = ?;");
        ) {
            statement.setString(1, token);
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

    public void deleteExpiredTokens() {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM accounttoken WHERE expiry_date < NOW()")
        ) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
