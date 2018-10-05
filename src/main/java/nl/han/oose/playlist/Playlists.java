package nl.han.oose.playlist;

import java.util.List;

public class Playlists {

    private List<Playlist> playlistArray;
    private int length;

    public Playlists(List<Playlist> playlist, int length) {
        this.playlistArray = playlist;
        this.length = length;
    }

    public Playlists() {

    }

    public List<Playlist> getPlaylistArray() {
        return playlistArray;
    }

    public void setPlaylistArray(List<Playlist> playlistArray) {
        this.playlistArray = playlistArray;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
