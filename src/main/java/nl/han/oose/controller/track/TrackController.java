package nl.han.oose.controller.track;

import nl.han.oose.service.track.TrackService;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.apache.cxf.common.util.StringUtils.isEmpty;

@Path("/tracks")
public class TrackController {

    @Inject
    private TrackService trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response trackList(@QueryParam("token") String token, @QueryParam("forPlaylist") int forPlaylist) {
        if (isEmpty(token) || forPlaylist <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.ok().entity(trackService.getTrackList(token, forPlaylist)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
