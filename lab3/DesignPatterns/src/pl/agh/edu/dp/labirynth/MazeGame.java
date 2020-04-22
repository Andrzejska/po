package pl.agh.edu.dp.labirynth;

import pl.agh.edu.dp.labirynth.builder.StandardMazeBuilder;
import pl.agh.edu.dp.labirynth.elements.Room;
import pl.agh.edu.dp.labirynth.factories.MazeFactory;
import pl.agh.edu.dp.player.Player;

import java.util.Scanner;

import static pl.agh.edu.dp.labirynth.Direction.*;

public class MazeGame {

    private Player player;

    private static MazeGame instance;

    public static MazeGame getInstance() {
        if (instance == null) {
            instance = new MazeGame();
        }
        return instance;
    }

    public void start() {
        System.out.println("Enter q to exit the game");
        System.out.println("Enter w/s/a/d to move player");
        loop();
    }

    private void loop() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            player.status();
            char c = scan.next().charAt(0);
            switch (c) {
                case 'w':
                    this.player.move(North);
                    break;
                case 's':
                    this.player.move(South);
                    break;
                case 'a':
                    this.player.move(West);
                    break;
                case 'd':
                    this.player.move(East);
                    break;
                case 'q':
                    stop();
                    return;
                default:
                    System.out.println("Unknown command: " + c);
                    break;
            }
        }
    }

    public void stop() {
        System.out.println("Exiting game...");
    }

    public void createMaze(StandardMazeBuilder builder, MazeFactory factory) throws Exception {
        this.player = new Player(buildExampleMaze(builder, factory));
        Maze maze = builder.getCurrentMaze();
    }

    private Room buildExampleMaze(StandardMazeBuilder builder, MazeFactory factory) throws Exception {
        Room[] rooms = new Room[9];
        for (int i = 0; i < 9; i++) {
            rooms[i] = factory.createRoom(i);
            builder.addRoom(rooms[i]);
        }

        builder.addCommonWall(Direction.East, rooms[0], rooms[1]);
        builder.addCommonWall(Direction.South, rooms[0], rooms[3]);
        builder.addCommonWall(Direction.East, rooms[1], rooms[2]);
        builder.addCommonWall(Direction.South, rooms[1], rooms[4]);
        builder.addCommonWall(Direction.South, rooms[2], rooms[5]);
        builder.addCommonWall(Direction.East, rooms[3], rooms[4]);
        builder.addCommonWall(Direction.South, rooms[3], rooms[6]);
        builder.addCommonWall(Direction.East, rooms[4], rooms[5]);
        builder.addCommonWall(Direction.South, rooms[4], rooms[7]);
        builder.addCommonWall(Direction.South, rooms[5], rooms[8]);
        builder.addCommonWall(Direction.East, rooms[6], rooms[7]);
        builder.addCommonWall(Direction.East, rooms[7], rooms[8]);

        builder.addDoor(rooms[0], rooms[1]);
        builder.addDoor(rooms[1], rooms[2]);
        builder.addDoor(rooms[2], rooms[5]);
        builder.addDoor(rooms[4], rooms[5]);
        builder.addDoor(rooms[4], rooms[7]);
        builder.addDoor(rooms[7], rooms[8]);
        builder.addDoor(rooms[6], rooms[7]);
        builder.addDoor(rooms[3], rooms[6]);

        return rooms[0];
    }
}
