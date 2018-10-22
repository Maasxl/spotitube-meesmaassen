package nl.han.oose.playlist;

import nl.han.oose.controller.playlist.PlaylistsController;
import nl.han.oose.entity.playlist.Playlists;
import nl.han.oose.service.playlist.PlaylistsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistscontrollerTest {

    @Mock
    private PlaylistsService playlistsService;
    private Playlists playlists;

    @InjectMocks
    private PlaylistsController sut;


    @Test
    public void testStatusOkOnSuccesfulPlaylistSearch() throws NotFoundException {
        String token = "";
        Mockito.when(playlistsService.playlistSearch(Mockito.any())).thenReturn(playlists);

        Response searchResponce = sut.playlistSearch(token);

        assertEquals(Response.Status.OK.getStatusCode(), searchResponce.getStatus());
        assertEquals(playlists, searchResponce.getEntity());
    }

}