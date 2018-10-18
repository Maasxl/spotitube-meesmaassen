package nl.han.oose.persistance;

import nl.han.oose.AccountToken;
import nl.han.oose.playlist.Playlist;
import nl.han.oose.playlist.Playlists;
import nl.han.oose.track.Tracks;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaylistDAO {

    @Inject
    private ConnectionFactory connectionFactory;

    public Playlists getAllPlaylists(AccountToken accountToken) {
        Playlists playlists = new Playlists();
        int playlistsLength = 0;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM playlist;");
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String user = resultSet.getString("user");
                Boolean owner;

                if (!user.equals(accountToken.getUser())) {
                    owner = false;
                } else {
                    owner = true;
                }

                playlists.getPlaylists().add(new Playlist(id, name, owner, new ArrayList<>()));
                playlistsLength += getPlaylistLength(id);
            }
            playlists.setLength(playlistsLength);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private int getPlaylistLength(int id) {
        int playlistLength = 0;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("");
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                playlistLength += resultSet.getInt("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlistLength;
    }

    public Tracks getTracksFromPlaylist() {
        return null;
    }
}
