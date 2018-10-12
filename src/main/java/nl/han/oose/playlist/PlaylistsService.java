package nl.han.oose.playlist;

import nl.han.oose.AccountToken;
import nl.han.oose.track.Track;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlaylistsService {

    private List<Playlist> playlistArray = new ArrayList<>();
    private List<Track> trackArray = new ArrayList<>();
    private Playlists myPlaylist;
    private AccountToken accountToken = new AccountToken("1234-1234-1234", "Mees Maassen");

    public PlaylistsService() {
        trackArray.add(new Track(1, "Song1", "Singer", 350, "het album", 3, new Date(), "a description", false));
        trackArray.add(new Track(2, "Song2", "Singer2", 300, "het album2", 0, new Date(), "a description lol", true));
        playlistArray.add(new Playlist(1, "Rock", true, trackArray));
        playlistArray.add(new Playlist(2, "Pop", false, trackArray));
        myPlaylist = new Playlists(playlistArray, 1346);
    }

    public Playlists playlistSearch(String token) throws NotFoundException {
        if (token.equals(accountToken.getToken())) {
            return myPlaylist;
        } else {
            throw new NotFoundException("No playlists found");
        }
    }
}
