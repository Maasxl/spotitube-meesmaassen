package nl.han.oose.playlist;

import java.util.List;

public class Playlists {

    private List<Playlist> playlists;
    private int length;

    public Playlists(List<Playlist> playlist, int length) {
        this.playlists = playlist;
        this.length = length;
    }

    public Playlists() {

    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlistArray) {
        this.playlists = playlistArray;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
