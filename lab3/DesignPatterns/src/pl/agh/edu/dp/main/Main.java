package pl.agh.edu.dp.main;

import pl.agh.edu.dp.labirynth.*;
import pl.agh.edu.dp.labirynth.builder.StandardMazeBuilder;
import pl.agh.edu.dp.labirynth.factories.MazeFactory;

public class Main {
    public static void main(String[] args) throws Exception {
        MazeGame mazeGame = new MazeGame();
        MazeFactory mazeFactory = MazeFactory.getInstance();
        StandardMazeBuilder builder = new StandardMazeBuilder(mazeFactory);
        mazeGame.createMaze(builder, mazeFactory);
        mazeGame.start();
    }
}



