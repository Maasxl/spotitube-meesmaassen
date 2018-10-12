package nl.han.oose.track;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TrackcontrollerTest {

    @Mock
    private TrackService trackService;
    private Tracks tracks;

    @InjectMocks
    private Trackcontroller sut;


}