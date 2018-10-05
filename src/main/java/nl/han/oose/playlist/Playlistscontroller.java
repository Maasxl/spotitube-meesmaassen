package nl.han.oose.playlist;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/playlists")
public class Playlistscontroller {

    private List<Playlist> playlistArray = new ArrayList<>();

    public Playlistscontroller() {
        playlistArray.add(new Playlist(1, "Rock", true, new Tracks()));
        playlistArray.add(new Playlist(2, "Pop", false, new Tracks()));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response playlistSearch() {
        return Response.ok().entity(new Playlists(playlistArray, 1346)).build();
    }
}
