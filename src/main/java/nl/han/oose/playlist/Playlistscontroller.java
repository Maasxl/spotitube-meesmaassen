package nl.han.oose.playlist;


import javax.security.auth.login.LoginException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class Playlistscontroller {

    private PlaylistsService playlistsService = new PlaylistsService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response playlistSearch(@QueryParam("token") String accountToken) {
        try {
            return Response.ok().entity(playlistsService.playlistSearch(accountToken)).build();
        } catch (LoginException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
