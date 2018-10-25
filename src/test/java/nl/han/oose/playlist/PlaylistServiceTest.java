package nl.han.oose.playlist;

import nl.han.oose.service.playlist.PlaylistService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class PlaylistServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private PlaylistService sut;

    @Before
    public void setUp() throws Exception {
        sut = new PlaylistService();
    }

}