package pl.edu.agh.dronka.shop.model;

public enum MusicStyle {
    POP("Pop"), ROCK("Rock"), RAP("Rap"), CLASSIC("Classic"), TECHNO("Techno");
    private String displayStyle;
    MusicStyle(String musicStyleName){
        this.displayStyle=musicStyleName;
    }
    public static MusicStyle parseStyle(String styleS){
        switch(styleS){
            case "POP":
                return POP;
            case "ROCK":
                return ROCK;
            case "RAP":
                return RAP;
            case "CLASSIC":
                return CLASSIC;
            case "TECHNO":
                return TECHNO;
            default:
                throw new IllegalStateException("Unexpected value: " + styleS);
        }
    }
}

