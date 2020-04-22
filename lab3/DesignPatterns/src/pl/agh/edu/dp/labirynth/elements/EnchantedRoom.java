package pl.agh.edu.dp.labirynth.elements;

public class EnchantedRoom extends Room {
    public EnchantedRoom(int number) {
        super(number);
    }

    @Override
    public void Enter() {
        System.out.println("Entered enchanted room");
    }
}
