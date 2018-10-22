package nl.han.oose.persistance;

import nl.han.oose.entity.track.Song;
import nl.han.oose.entity.track.Tracks;
import nl.han.oose.entity.track.Video;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultSets {


    public Tracks resultSetSong(ResultSet resultSet) throws SQLException {
        Tracks tracks = new Tracks();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String performer = resultSet.getString("performer");
            int duration = resultSet.getInt("duration");
            String url = resultSet.getString("url");
            int playcount = resultSet.getInt("playcount");
            String album = resultSet.getString("album");
            boolean offlineAvailable = resultSet.getBoolean("offline_available");

            tracks.getTracks().add(new Song(id, title, performer, duration, url, playcount, offlineAvailable, album));
        }
        return tracks;
    }

    public Tracks resultSetVideo(ResultSet resultSet) throws SQLException, ParseException {
        Tracks tracks = new Tracks();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String performer = resultSet.getString("performer");
            int duration = resultSet.getInt("duration");
            String url = resultSet.getString("url");
            int playcount = resultSet.getInt("playcount");
            String publicationDate = null;

            if (resultSet.getString("publication_date") != null) {
                SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = oldFormat.parse(resultSet.getString("publication_date"));
                SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");

                publicationDate = newFormat.format(date);
            }

            String description = resultSet.getString("description");
            boolean offlineAvailable = resultSet.getBoolean("offline_available");

            tracks.getTracks().add(new Video(id, title, performer, duration, url, playcount, offlineAvailable, publicationDate, description));
        }
        return tracks;
    }
}
