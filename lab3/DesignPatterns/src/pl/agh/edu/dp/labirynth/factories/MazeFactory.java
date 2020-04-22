package pl.agh.edu.dp.labirynth.factories;

import pl.agh.edu.dp.labirynth.elements.Door;
import pl.agh.edu.dp.labirynth.elements.Room;
import pl.agh.edu.dp.labirynth.elements.Wall;

public class MazeFactory {
    private static MazeFactory instance;

    public static MazeFactory getInstance(){
        if( instance == null){
            instance = new MazeFactory();
        }
        return instance;
    }
    public Door createDoor(Room r1, Room r2) {
        return new Door(r1, r2);
    }

    public Room createRoom(int number) {
        return new Room(number);
    }

    public Wall createWall() {
        return new Wall();
    }
}
