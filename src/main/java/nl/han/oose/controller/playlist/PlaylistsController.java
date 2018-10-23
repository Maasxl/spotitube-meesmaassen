package nl.han.oose.controller.playlist;


import nl.han.oose.entity.playlist.Playlist;
import nl.han.oose.entity.track.Track;
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

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String accountToken, @PathParam("id") int playlistId) {
        try {
            return Response.ok().entity(playlistsService.deletePlaylist(accountToken, playlistId)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String accountToken, Playlist playlist) {
        try {
            return Response.ok().entity(playlistsService.addPlaylist(accountToken, playlist)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(@QueryParam("token") String accountToken, @PathParam("id") int playlistId, Playlist playlist) {
        try {
            return Response.ok().entity(playlistsService.editPlaylist(accountToken, playlistId, playlist)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{playlistid}/tracks/{trackid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@QueryParam("token") String token, @PathParam("playlistid") int playlistId, @PathParam("trackid") int trackId) {
        try {
            return Response.ok().entity(playlistsService.deleteTrackFromPlaylist(token, playlistId, trackId)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String accountToken, @PathParam("id") int playlistId, Track track) {
        try {
            return Response.ok().entity(playlistsService.addTrackToPlaylist(accountToken, playlistId, track)).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
