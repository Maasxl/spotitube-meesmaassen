package nl.han.oose.service.playlist;

import nl.han.oose.entity.login.AccountToken;
import nl.han.oose.entity.playlist.Playlists;
import nl.han.oose.persistance.login.AccountTokenDAO;
import nl.han.oose.persistance.playlist.PlaylistDAO;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.naming.AuthenticationException;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private AccountTokenDAO accountTokenDAOMock;

    @Mock
    private PlaylistDAO playlistDAOMock;

    @InjectMocks
    private PlaylistService sut;

    @Test
    public void testThatPlaylistAreReturned() throws AuthenticationException {
        AccountToken accountToken = new AccountToken("1234-1234-1234", "mees");
        Playlists playlists = new Playlists();

        Mockito.when(accountTokenDAOMock.getAccountToken(Mockito.any())).thenReturn(accountToken);
        Mockito.when(playlistDAOMock.getAllPlaylists(Mockito.any())).thenReturn(playlists);

        assertEquals(playlists, sut.playlistSearch("1234-1234-1234"));
    }

}