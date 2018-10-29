package nl.han.oose.service.playlist;

import nl.han.oose.entity.login.AccountToken;
import nl.han.oose.entity.playlist.Playlist;
import nl.han.oose.entity.playlist.Playlists;
import nl.han.oose.entity.track.Track;
import nl.han.oose.entity.track.Tracks;
import nl.han.oose.persistance.login.AccountTokenDAO;
import nl.han.oose.persistance.playlist.PlaylistDAO;
import nl.han.oose.persistance.track.TrackDAO;

import javax.inject.Inject;
import javax.naming.AuthenticationException;

public class PlaylistService {

    @Inject
    private AccountTokenDAO accountTokenDAO;

    @Inject
    private PlaylistDAO playlistDAO;

    @Inject
    private TrackDAO trackDAO;


    public Playlists playlistSearch(String token) throws AuthenticationException {
        AccountToken accountToken = accountTokenDAO.getAccountToken(token);
        if (token.equals(accountToken.getToken())) {
            return playlistDAO.getAllPlaylists(accountToken);
        } else {
            throw new AuthenticationException("Account token incorrect");
        }
    }

    public Tracks getTracksFromPlaylist(int id, String token) throws AuthenticationException {
        AccountToken accountToken = accountTokenDAO.getAccountToken(token);
        if (token.equals(accountToken.getToken())) {
            return playlistDAO.getTracksFromPlaylist(id);
        } else {
            throw new AuthenticationException("Account token incorrect");
        }
    }

    public Playlists deletePlaylist(String token, int playlistId) throws AuthenticationException {
        AccountToken accountToken = accountTokenDAO.getAccountToken(token);
        if (token.equals(accountToken.getToken())) {
            playlistDAO.deletePlaylist(playlistId);
            return playlistDAO.getAllPlaylists(accountToken);
        } else {
            throw new AuthenticationException("Account token incorrect");
        }
    }

    public Playlists addPlaylist(String token, Playlist playlist) throws AuthenticationException {
        AccountToken accountToken = accountTokenDAO.getAccountToken(token);
        if (token.equals(accountToken.getToken())) {
            playlistDAO.addPlaylist(accountToken, playlist);
            return playlistDAO.getAllPlaylists(accountToken);
        } else {
            throw new AuthenticationException("Account token incorrect");
        }
    }

    public Playlists editPlaylist(String token, int playlistId, Playlist playlist) throws AuthenticationException {
        AccountToken accountToken = accountTokenDAO.getAccountToken(token);
        if (token.equals(accountToken.getToken())) {
            playlistDAO.editPlaylist(playlistId, playlist);
            return playlistDAO.getAllPlaylists(accountToken);
        } else {
            throw new AuthenticationException("Account token incorrect");
        }
    }

    public Tracks deleteTrackFromPlaylist(String token, int playlistId, int trackId) throws AuthenticationException {
        AccountToken accountToken = accountTokenDAO.getAccountToken(token);
        if (token.equals(accountToken.getToken())) {
            playlistDAO.deleteTrackFromPlaylist(playlistId, trackId);
            return trackDAO.getTrackList(playlistId);
        } else {
            throw new AuthenticationException("Account token incorrect");
        }
    }

    public Tracks addTrackToPlaylist(String token, int playlistId, Track track) throws AuthenticationException {
        AccountToken accountToken = accountTokenDAO.getAccountToken(token);
        if (token.equals(accountToken.getToken())) {
            playlistDAO.addTrackToPlaylist(playlistId, track);
            return playlistDAO.getTracksFromPlaylist(playlistId);
        } else {
            throw new AuthenticationException("Account token incorrect");
        }
    }
}
