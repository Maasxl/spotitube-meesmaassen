package nl.han.oose.controller.track;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TrackControllerTest {

    @InjectMocks
    private TrackController sut;

    @Test
    public void testStatusBadRequestWhenTokenIsEmpty() {
        Response response = sut.trackList("", 1);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

}