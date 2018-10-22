package nl.han.oose.service.playlist;

import nl.han.oose.entity.login.AccountToken;
import nl.han.oose.entity.playlist.Playlists;
import nl.han.oose.entity.track.Tracks;
import nl.han.oose.persistance.login.AccountTokenDAO;
import nl.han.oose.persistance.playlist.PlaylistDAO;

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
