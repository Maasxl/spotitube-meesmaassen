package nl.han.oose.service.track;

import nl.han.oose.entity.login.AccountToken;
import nl.han.oose.entity.track.Tracks;
import nl.han.oose.persistance.login.AccountTokenDAO;
import nl.han.oose.persistance.track.TrackDAO;
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
public class TrackServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    private AccountTokenDAO accountTokenDAOMock;

    @Mock
    private TrackDAO trackDAOMock;

    @InjectMocks
    private TrackService sut;

    @Test
    public void testThatTracksAreReturned() throws AuthenticationException {
        AccountToken accountToken = new AccountToken("1234-1234-1234", "mees");
        Tracks tracks = new Tracks();

        Mockito.when(accountTokenDAOMock.getAccountToken(Mockito.any())).thenReturn(accountToken);
        Mockito.when(trackDAOMock.getTrackList(Mockito.anyInt())).thenReturn(tracks);

        assertEquals(tracks, sut.getTrackList("1234-1234-1234", 1));
    }
}
