package pl.agh.edu.dp.labirynth.elements;

public class BombedWall extends Wall{
    public BombedWall(){
        super();
    }
    @Override
    public void Enter() {
        System.out.println("Entered bombed wall");
    }
}
