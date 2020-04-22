package pl.agh.edu.dp.labirynth.builder;

import pl.agh.edu.dp.labirynth.Direction;
import pl.agh.edu.dp.labirynth.elements.Room;

public interface MazeBuilder {
    void addRoom(Room room);
    void addDoor(Room r1, Room r2) throws Exception;
    void addCommonWall(Direction roomDirection, Room r1, Room r2) throws Exception;
}
