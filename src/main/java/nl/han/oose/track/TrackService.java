package nl.han.oose.track;

import nl.han.oose.AccountToken;
import nl.han.oose.playlist.Playlist;

import javax.ws.rs.BadRequestException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrackService {

    private List<Track> tracks = new ArrayList<>();
    private Tracks trackList;
    private List<Track> trackArray = new ArrayList<>();
    private Playlist playlist;
    private AccountToken accountToken = new AccountToken("1234-1234-1234", "Mees Maassen");

    public Tracks getTrackList(String token, String forPlaylist) throws BadRequestException {
        trackArray.add(new Track(1, "Song1", "Singer", 350, "het album", 3, new Date(), "a description", false));
        playlist = new Playlist(1, "Rock", true, trackArray);
        if (token.equals(accountToken.getToken()) && forPlaylist.equals(playlist.getName())) {
            tracks.add(new Track(2, "Song2", "Singer2", 300, "het album2", 0, new Date(), "a description lol", true));
            tracks.add(new Track(3, "Song3", "Singer", 280, "het album", 2, new Date(), "a description for my song", false));
            trackList = new Tracks(tracks);
            return trackList;
        } else {
            throw new BadRequestException("The playlist is not found");
        }
    }
}
