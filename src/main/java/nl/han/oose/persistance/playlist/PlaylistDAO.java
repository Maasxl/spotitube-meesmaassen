package nl.han.oose.persistance.playlist;

import nl.han.oose.entity.login.AccountToken;
import nl.han.oose.entity.playlist.Playlist;
import nl.han.oose.entity.playlist.Playlists;
import nl.han.oose.entity.track.Track;
import nl.han.oose.entity.track.Tracks;
import nl.han.oose.persistance.ConnectionFactory;
import nl.han.oose.persistance.ResultSets;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class PlaylistDAO {

    @Inject
    private ConnectionFactory connectionFactory;

    @Inject
    private ResultSets resultSets;

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
                boolean owner = true;

                if (!user.equals(accountToken.getUser())) {
                    owner = false;
                }

                playlists.getPlaylists().add(new Playlist(id, name, owner, new ArrayList<>()));
                playlistsLength += getPlaylistLength(id);
            }
            playlists.setLength(playlistsLength);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlists;
    }

    private int getPlaylistLength(int id) {
        int playlistLength = 0;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT SUM(duration) AS playlist_length FROM track WHERE id IN(SELECT track_id FROM trackInPlaylist WHERE playlist_id = ?);");
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                playlistLength += resultSet.getInt("playlist_length");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return playlistLength;
    }

    public Tracks getTracksFromPlaylist(int playlistId) {
        Tracks tracks = new Tracks();
        Tracks songs = getSongsFromPlaylist(playlistId);
        Tracks videos = getVideosFromPlaylist(playlistId);
        tracks.getTracks().addAll(songs.getTracks());
        tracks.getTracks().addAll(videos.getTracks());

        return tracks;
    }

    private Tracks getVideosFromPlaylist(int playlistId) {
        Tracks tracks;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT video_view.*, trackInPlaylist.offline_available\n" +
                                "FROM video_view LEFT JOIN trackInPlaylist\n" +
                                "ON trackInPlaylist.track_id = video_view.id\n" +
                                "AND trackInPlaylist.playlist_id = ?\n" +
                                "WHERE video_view.id IN(SELECT track_id FROM trackInPlaylist WHERE playlist_id = ?);");
        ) {
            statement.setInt(1, playlistId);
            statement.setInt(2, playlistId);
            ResultSet resultSet = statement.executeQuery();

            tracks = resultSets.resultSetVideo(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return tracks;
    }

    private Tracks getSongsFromPlaylist(int playlistId) {
        Tracks tracks;

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT song_view.*, trackInPlaylist.offline_available\n" +
                                "FROM song_view LEFT JOIN trackInPlaylist\n" +
                                "ON trackInPlaylist.track_id = song_view.id\n" +
                                "AND trackInPlaylist.playlist_id = ?\n" +
                                "WHERE song_view.id IN(SELECT track_id FROM trackInPlaylist WHERE playlist_id = ?);");
        ) {
            statement.setInt(1, playlistId);
            statement.setInt(2, playlistId);
            ResultSet resultSet = statement.executeQuery();

            tracks = resultSets.resultSetSong(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tracks;
    }

    public void deletePlaylist(int playlistId) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM playlist WHERE id = ?;");
        ) {
            statement.setInt(1, playlistId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPlaylist(AccountToken accountToken, Playlist playlist) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO playlist (user, name) VALUES (?,?);");
        ) {
            statement.setString(1, accountToken.getUser());
            statement.setString(2, playlist.getName());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editPlaylist(int playlistId, Playlist playlist) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE playlist SET name = ? WHERE id = ?;");
        ) {
            statement.setString(1, playlist.getName());
            statement.setInt(2, playlistId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTrackFromPlaylist(int playlistId, int trackId) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM trackInPlaylist WHERE playlist_id = ? AND track_id = ?;");
        ) {
            statement.setInt(1, playlistId);
            statement.setInt(2, trackId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTrackToPlaylist(int playlistId, Track track) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO trackInPlaylist (playlist_id, track_id, offline_available) VALUES (?,?,?);");
        ) {
            statement.setInt(1, playlistId);
            statement.setInt(2, track.getId());
            statement.setBoolean(3, track.isOfflineAvailable());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
