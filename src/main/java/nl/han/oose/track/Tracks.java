package nl.han.oose.track;

import java.util.ArrayList;

public class Tracks {

    private ArrayList<Track> tracks = new ArrayList<>();

    public Tracks() {
    }

    public Tracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }
}
