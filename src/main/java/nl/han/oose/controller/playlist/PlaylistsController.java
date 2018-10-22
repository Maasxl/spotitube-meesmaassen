package nl.han.oose.controller.playlist;


import nl.han.oose.service.playlist.PlaylistsService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistsController {

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

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@PathParam("id") int id, @QueryParam("token") String accountToken) {
        try {
            return Response.ok().entity(playlistsService.getTracksFromPlaylist(id, accountToken)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

//    @DELETE
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response deletePlaylist(@QueryParam("token") String accountToken, @PathParam("id") int playlistId) {
//        try {
//            return Response.ok().entity(playlistsService.deletePlaylist(accountToken, playlistId)).build();
//        } catch (NotFoundException e) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//    }
}
