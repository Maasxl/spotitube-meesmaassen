package nl.han.oose.track;

import nl.han.oose.AccountToken;
import nl.han.oose.persistance.AccountTokenDAO;
import nl.han.oose.persistance.TrackDAO;

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
