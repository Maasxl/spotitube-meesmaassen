package nl.han.oose.track;

import java.util.List;

public class Tracks {

    private List<Track> tracks;

    public Tracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
