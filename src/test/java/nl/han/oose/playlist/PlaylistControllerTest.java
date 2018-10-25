package nl.han.oose.playlist;

import nl.han.oose.controller.playlist.PlaylistController;
import nl.han.oose.entity.playlist.Playlists;
import nl.han.oose.service.playlist.PlaylistService;
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
public class PlaylistControllerTest {

    @Mock
    private PlaylistService playlistService;
    private Playlists playlists;

    @InjectMocks
    private PlaylistController sut;


    @Test
    public void testStatusOkOnSuccesfulPlaylistSearch() throws NotFoundException {
        String token = "";
        Mockito.when(playlistService.playlistSearch(Mockito.any())).thenReturn(playlists);

        Response searchResponce = sut.playlistSearch(token);

        assertEquals(Response.Status.OK.getStatusCode(), searchResponce.getStatus());
        assertEquals(playlists, searchResponce.getEntity());
    }

}