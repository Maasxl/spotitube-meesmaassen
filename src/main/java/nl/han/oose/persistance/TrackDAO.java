package nl.han.oose.persistance;

import nl.han.oose.track.Tracks;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class TrackDAO {

    @Inject
    private ConnectionFactory connectionFactory;

    @Inject
    private ResultSets resultSets;

    public Tracks getTrackList(int forPlaylist) {
        Tracks tracks = new Tracks();
        Tracks songs = getSongsForPlaylist(forPlaylist);
        Tracks videos = getVideosForPlaylist(forPlaylist);
        tracks.getTracks().addAll(songs.getTracks());
        tracks.getTracks().addAll(videos.getTracks());

        return tracks;
    }

    private Tracks getSongsForPlaylist(int forPlaylist) {
        Tracks tracks = new Tracks();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT song_view.*, trackInPlaylist.offline_available " +
                                "FROM song_view LEFT JOIN trackInPlaylist " +
                                "ON trackInPlaylist.track_id = song_view.id " +
                                "AND trackInPlaylist.playlist_id = ?" +
                                "WHERE song_view.id NOT IN(SELECT track_id FROM trackInPlaylist WHERE playlist_id = ?);");
        ) {
            statement.setInt(1, forPlaylist);
            statement.setInt(2, forPlaylist);
            ResultSet resultSet = statement.executeQuery();

            tracks = resultSets.resultSetSong(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tracks;
    }

    private Tracks getVideosForPlaylist(int forPlaylist) {
        Tracks tracks = new Tracks();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT video_view.*, trackInPlaylist.offline_available " +
                                "FROM video_view LEFT JOIN trackInPlaylist " +
                                "ON trackInPlaylist.track_id = video_view.id " +
                                "AND trackInPlaylist.playlist_id = ?" +
                                "WHERE video_view.id NOT IN(SELECT track_id FROM trackInPlaylist WHERE playlist_id = ?);");
        ) {
            statement.setInt(1, forPlaylist);
            statement.setInt(2, forPlaylist);
            ResultSet resultSet = statement.executeQuery();

            tracks = resultSets.resultSetVideo(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return tracks;
    }
}
