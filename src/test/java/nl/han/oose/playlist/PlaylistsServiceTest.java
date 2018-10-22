package nl.han.oose.playlist;

import nl.han.oose.service.playlist.PlaylistsService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class PlaylistsServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    private PlaylistsService sut;

    @Before
    public void setUp() throws Exception {
        sut = new PlaylistsService();
    }

}