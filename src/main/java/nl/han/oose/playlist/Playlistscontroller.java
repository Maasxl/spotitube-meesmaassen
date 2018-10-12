package nl.han.oose.playlist;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class Playlistscontroller {

    @Inject
    private PlaylistsService playlistsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response playlistSearch(@QueryParam("token") String accountToken) {
        try {
            return Response.ok().entity(playlistsService.playlistSearch(accountToken)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
