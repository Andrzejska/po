package pl.agh.edu.dp.labirynth.elements;

public class EnchantedWall extends Wall {
    public EnchantedWall(){
        super();
    }


    @Override

    public void Enter() {
        System.out.println("Entered enchanted room");
    }
}
