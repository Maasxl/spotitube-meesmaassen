package nl.han.oose.track;

import nl.han.oose.controller.track.TrackController;
import nl.han.oose.entity.track.Tracks;
import nl.han.oose.service.track.TrackService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TrackControllerTest {

    @Mock
    private TrackService trackService;
    private Tracks tracks;

    @InjectMocks
    private TrackController sut;


}