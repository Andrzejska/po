package pl.agh.edu.dp.player;

import pl.agh.edu.dp.labirynth.Direction;
import pl.agh.edu.dp.labirynth.MapSite;
import pl.agh.edu.dp.labirynth.elements.Door;
import pl.agh.edu.dp.labirynth.elements.Room;
import pl.agh.edu.dp.labirynth.elements.Wall;

public class Player {

    private Room currentRoom;

    public Player(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void move(Direction dir) {
        MapSite side = this.currentRoom.getSide(dir);
        side.Enter();
        if (side instanceof Door) {
            this.currentRoom = ((Door) side).getRoomAtOthersSide(currentRoom);
        }
    }

    public void status() {
        System.out.println("Room number: " + this.currentRoom.getRoomNumber());
        for (Direction dir : Direction.values()) {
            if (this.currentRoom.getSide(dir) instanceof Door) {
                System.out.println(dir + " door");
            } if (this.currentRoom.getSide(dir) instanceof Wall) {
                System.out.println(dir + " wall");
            } if (this.currentRoom.getSide(dir) instanceof Room) {
                System.out.println(dir + " Room");
            }
        }
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }
}
