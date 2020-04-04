package pl.edu.agh.dronka.shop.model;

public class MusicItem extends Item{
    MusicStyle musicStyle;

    public MusicStyle getMusicStyle() {
        return musicStyle;
    }

    public boolean isVideoConnected() {
        return videoConnected;
    }

    boolean videoConnected;

    public void setMusicStyle(MusicStyle musicStyle) {
        this.musicStyle = musicStyle;
    }

    public void setVideoConnected(boolean videoConnected) {
        this.videoConnected = videoConnected;
    }

    public MusicItem(String name, Category category, int price, int quantity, MusicStyle musicStyle, boolean videoConnected){
        super(name,category,price,quantity);
        this.musicStyle=musicStyle;
        this.videoConnected=videoConnected;
    }
    public MusicItem(){}
}
