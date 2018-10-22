package nl.han.oose.playlist;

import nl.han.oose.AccountToken;
import nl.han.oose.persistance.AccountTokenDAO;
import nl.han.oose.persistance.PlaylistDAO;
import nl.han.oose.track.Tracks;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

public class PlaylistsService {

    @Inject
    private AccountTokenDAO accountTokenDAO;

    @Inject
    private PlaylistDAO playlistDAO;


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
            return playlistDAO.getTracksFromPlaylist(id);
        } else {
            throw new NotFoundException();
        }
    }
}
