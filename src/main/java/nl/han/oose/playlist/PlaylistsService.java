package nl.han.oose.playlist;

import nl.han.oose.AccountToken;
import nl.han.oose.persistance.AccountTokenDAO;
import nl.han.oose.persistance.PlaylistDAO;
import nl.han.oose.track.Track;
import nl.han.oose.track.Tracks;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistsService {

    private List<Playlist> playlistArray = new ArrayList<>();
    private List<Track> trackArray = new ArrayList<>();
    private Playlists myPlaylist;

    @Inject
    private AccountTokenDAO accountTokenDAO;

    @Inject
    private PlaylistDAO playlistDAO;

    public PlaylistsService() {
        trackArray.add(new Track(1, "Song1", "Singer", 350, "het album", 3, "16-10-2018", "a description", false));
        trackArray.add(new Track(2, "Song2", "Singer2", 300, "het album2", 1, "16-10-2018", "a description lol", true));
        playlistArray.add(new Playlist(1, "Rock", true, trackArray));
        playlistArray.add(new Playlist(2, "Pop", false, trackArray));
        myPlaylist = new Playlists(playlistArray, 1346);
    }

    public Playlists playlistSearch(String token) throws NotFoundException {
        AccountToken accountToken = accountTokenDAO.getAccountToken(token);
        if (token.equals(accountToken.getToken())) {
            return playlistDAO.getAllPlaylists(accountToken);
        } else {
            throw new NotFoundException("No playlists found");
        }
    }

    public Tracks getTracksFromPlaylist(int id, String token) throws NotFoundException {
        AccountToken accountToken = accountTokenDAO.getAccountToken(token);
        if (token.equals(accountToken.getToken())) {
            return playlistDAO.getTracksFromPlaylist();
        } else {
            throw new NotFoundException();
        }
    }
}
