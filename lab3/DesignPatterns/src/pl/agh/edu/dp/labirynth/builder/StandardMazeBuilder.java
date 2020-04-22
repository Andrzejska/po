package pl.agh.edu.dp.labirynth.builder;

import pl.agh.edu.dp.labirynth.Direction;
import pl.agh.edu.dp.labirynth.MapSite;
import pl.agh.edu.dp.labirynth.Maze;
import pl.agh.edu.dp.labirynth.builder.MazeBuilder;
import pl.agh.edu.dp.labirynth.elements.Door;
import pl.agh.edu.dp.labirynth.elements.Room;
import pl.agh.edu.dp.labirynth.factories.MazeFactory;

import static pl.agh.edu.dp.labirynth.Direction.*;

public class StandardMazeBuilder implements MazeBuilder {
    private Maze currentMaze;
    private MazeFactory factory;

    public StandardMazeBuilder(MazeFactory factory) {
        this.currentMaze = new Maze();
        this.factory = factory;
    }

    @Override
    public void addRoom(Room room) {
        room.setSide(South, factory.createWall());
        room.setSide(North, factory.createWall());
        room.setSide(East, factory.createWall());
        room.setSide(West, factory.createWall());
        currentMaze.addRoom(room);
    }

    @Override
    public void addDoor(Room r1, Room r2) throws Exception {
        Direction r1Door = null;
        for (Direction dir : values()) {
            if (r1.getSide(dir).equals(r2.getSide(dir.getOppositeSide()))) {
                r1Door = dir;
                break;
            }
        }
        if (r1Door == null) throw new Exception("Seems like rooms don't have common door");
        else {
            Door newDoor = factory.createDoor(r1, r2);
            r1.setSide(r1Door, newDoor);
            r2.setSide(r1Door.getOppositeSide(), newDoor);
        }
    }

    @Override
    public void addCommonWall(Direction r1Direction, Room r1, Room r2) throws Exception {
        MapSite side = r1.getSide(r1Direction);
        if (side == null) throw new Exception("Seems like such a room doesn't exist");
        r2.setSide(r1Direction.getOppositeSide(), side);
    }

    public Maze getCurrentMaze() {
        return this.currentMaze;
    }
}