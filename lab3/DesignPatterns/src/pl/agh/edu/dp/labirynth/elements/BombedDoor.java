package pl.agh.edu.dp.labirynth.elements;

public class BombedDoor extends Door {
    public BombedDoor(Room r1, Room r2) {
        super(r1, r2);
    }

    @Override
    public void Enter() {
        System.out.println("You opened bombed door");
    }
}
