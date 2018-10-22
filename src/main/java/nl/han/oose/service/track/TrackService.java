package nl.han.oose.service.track;

import nl.han.oose.entity.login.AccountToken;
import nl.han.oose.entity.track.Tracks;
import nl.han.oose.persistance.login.AccountTokenDAO;
import nl.han.oose.persistance.track.TrackDAO;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

public class TrackService {

    @Inject
    private AccountTokenDAO accountTokenDAO;

    @Inject
    private TrackDAO trackDAO;


    public Tracks getTrackList(String token, int forPlaylist) throws BadRequestException {
        AccountToken accountToken = accountTokenDAO.getAccountToken(token);
        if (token.equals(accountToken.getToken())) {
            return trackDAO.getTrackList(forPlaylist);
        } else {
            throw new BadRequestException("The playlist is not found");
        }
    }
}
