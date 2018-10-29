package nl.han.oose.controller.playlist;


import nl.han.oose.entity.playlist.Playlist;
import nl.han.oose.entity.track.Track;
import nl.han.oose.service.playlist.PlaylistService;

import javax.inject.Inject;
import javax.naming.AuthenticationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.apache.cxf.common.util.StringUtils.isEmpty;

@Path("/playlists")
public class PlaylistController {

    @Inject
    private PlaylistService playlistService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response playlistSearch(@QueryParam("token") String accountToken) {
        if (isEmpty(accountToken)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.ok().entity(playlistService.playlistSearch(accountToken)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksFromPlaylist(@PathParam("id") int id, @QueryParam("token") String accountToken) {
        if (isEmpty(accountToken)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.ok().entity(playlistService.getTracksFromPlaylist(id, accountToken)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String accountToken, @PathParam("id") int playlistId) {
        if (isEmpty(accountToken)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.ok().entity(playlistService.deletePlaylist(accountToken, playlistId)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String accountToken, Playlist playlist) {
        if (isEmpty(accountToken)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.ok().entity(playlistService.addPlaylist(accountToken, playlist)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(@QueryParam("token") String accountToken, @PathParam("id") int playlistId, Playlist playlist) {
        if (isEmpty(accountToken)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.ok().entity(playlistService.editPlaylist(accountToken, playlistId, playlist)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Path("/{playlistid}/tracks/{trackid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@QueryParam("token") String accountToken, @PathParam("playlistid") int playlistId, @PathParam("trackid") int trackId) {
        if (isEmpty(accountToken)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.ok().entity(playlistService.deleteTrackFromPlaylist(accountToken, playlistId, trackId)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String accountToken, @PathParam("id") int playlistId, Track track) {
        if (isEmpty(accountToken)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            return Response.ok().entity(playlistService.addTrackToPlaylist(accountToken, playlistId, track)).build();
        } catch (AuthenticationException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
