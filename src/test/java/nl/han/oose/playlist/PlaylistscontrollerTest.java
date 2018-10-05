package nl.han.oose.playlist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.security.auth.login.LoginException;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistscontrollerTest {

    @Mock
    private PlaylistsService playlistsService;
    private Playlists playlists;

    @InjectMocks
    private Playlistscontroller sut;


    @Test
    public void testStatusOkOnSuccesfulPlaylistSearch() throws LoginException {
        String token = "";
        Mockito.when(playlistsService.playlistSearch(Mockito.any())).thenReturn(playlists);

        Response searchResponce = sut.playlistSearch(token);

        assertEquals(Response.Status.OK.getStatusCode(), searchResponce.getStatus());
        assertEquals(playlists, searchResponce.getEntity());
    }

}