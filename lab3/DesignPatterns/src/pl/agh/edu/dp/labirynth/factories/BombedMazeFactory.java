package pl.agh.edu.dp.labirynth.factories;

import pl.agh.edu.dp.labirynth.elements.BombedRoom;
import pl.agh.edu.dp.labirynth.elements.BombedWall;
import pl.agh.edu.dp.labirynth.elements.Room;
import pl.agh.edu.dp.labirynth.elements.Wall;

public class BombedMazeFactory extends MazeFactory {
    private static BombedMazeFactory instance;

    public static BombedMazeFactory getInstance(){
        if( instance == null){
            instance = new BombedMazeFactory();
        }
        return instance;
    }
    @Override
    public Room createRoom(int number) {
        return new BombedRoom(number);
    }

    @Override
    public Wall createWall() {
        return new BombedWall();
    }
}
