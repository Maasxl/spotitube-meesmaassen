package nl.han.oose.track;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class Trackcontroller {

    @Inject
    private TrackService trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackList(@QueryParam("token") String token, @QueryParam("forPlaylist") String forPlaylist) {
        try {
            return Response.ok().entity(trackService.getTrackList(token, forPlaylist)).build();
        } catch (BadRequestException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
