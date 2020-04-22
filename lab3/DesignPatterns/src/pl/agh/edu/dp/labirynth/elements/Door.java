package pl.agh.edu.dp.labirynth.elements;

import pl.agh.edu.dp.labirynth.MapSite;
import pl.agh.edu.dp.labirynth.MazeGame;

public class Door extends MapSite {
    private Room room1;
    private Room room2;

    public Door(Room r1, Room r2){
        this.room1 = r1;
        this.room2 = r2;
    }

    public Room getRoomAtOthersSide(Room firstR) {
        return room1 == firstR ? room2 : room1;
    }

    @Override
    public void Enter() {
        System.out.println("You opened normal door");
    }

    public Room getRoom1() {
        return room1;
    }

    public void setRoom1(Room room1) {
        this.room1 = room1;
    }

    public Room getRoom2() {
        return room2;
    }

    public void setRoom2(Room room2) {
        this.room1 = room2;
    }
}
