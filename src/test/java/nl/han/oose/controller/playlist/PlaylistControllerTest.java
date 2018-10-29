package nl.han.oose.controller.playlist;

import nl.han.oose.entity.playlist.Playlists;
import nl.han.oose.service.playlist.PlaylistService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.naming.AuthenticationException;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistControllerTest {

    @Mock
    private PlaylistService playlistService;

    @InjectMocks
    private PlaylistController sut;


    @Test
    public void testStatusOkOnSuccessfulPlaylistSearch() throws AuthenticationException {
        Playlists playlists = new Playlists();
        String token = "1234-1234-1234";
        Mockito.when(playlistService.playlistSearch(Mockito.any())).thenReturn(playlists);

        Response searchResponse = sut.playlistSearch(token);

        assertEquals(Response.Status.OK.getStatusCode(), searchResponse.getStatus());
        assertEquals(playlists, searchResponse.getEntity());
    }

}