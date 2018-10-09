package nl.han.oose.track;

import javax.security.auth.login.LoginException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class Trackcontroller {

    private TrackService trackService = new TrackService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackList(@QueryParam("token") String accountToken, @QueryParam("forPlaylist") String forPlaylist) {
        try {
            return Response.ok().entity(trackService.getTrackList(accountToken, forPlaylist)).build();
        } catch (LoginException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
