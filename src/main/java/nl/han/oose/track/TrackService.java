package nl.han.oose.track;

import nl.han.oose.AccountToken;
import nl.han.oose.persistance.AccountTokenDAO;
import nl.han.oose.playlist.Playlist;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import java.util.ArrayList;
import java.util.List;

public class TrackService {

    @Inject
    private AccountTokenDAO accountTokenDAO;

    private List<Track> tracks = new ArrayList<>();
    private Tracks trackList;
    private List<Track> trackArray = new ArrayList<>();
    private Playlist playlist;

    public Tracks getTrackList(String token, String forPlaylist) throws BadRequestException {
        AccountToken accountToken = accountTokenDAO.getAccountToken(token);
        trackArray.add(new Track(1, "Song1", "Singer", 350, "het album", 3, "16-10-2018", "a description", false));
        playlist = new Playlist(1, "Rock", true, trackArray);
        if (token.equals(accountToken.getToken()) && forPlaylist.equals(playlist.getName())) {
            tracks.add(new Track(2, "Song2", "Singer2", 300, "het album2", 0, "16-10-2018", "a description lol", true));
            tracks.add(new Track(3, "Song3", "Singer", 280, "het album", 2, "16-10-2018", "a description for my song", false));
            trackList = new Tracks(tracks);
            return trackList;
        } else {
            throw new BadRequestException("The playlist is not found");
        }
    }
}
