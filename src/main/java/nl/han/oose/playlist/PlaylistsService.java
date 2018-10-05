package nl.han.oose.playlist;

import nl.han.oose.AccountToken;
import nl.han.oose.Tracks;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistsService {

    private List<Playlist> playlistArray = new ArrayList<>();
    private Playlists myPlaylist;
    private AccountToken accountToken = new AccountToken("1234-1234-1234", "Mees Maassen");

    public PlaylistsService() {
        playlistArray.add(new Playlist(1, "Rock", true, new Tracks()));
        playlistArray.add(new Playlist(2, "Pop", false, new Tracks()));
        myPlaylist = new Playlists(playlistArray, 1346);
    }

    public Playlists playlistSearch(String token) throws LoginException {
        if (token.equals(accountToken.getToken())) {
            return myPlaylist;
        } else {
            throw new LoginException("Invalid token credentials");
        }
    }
}
