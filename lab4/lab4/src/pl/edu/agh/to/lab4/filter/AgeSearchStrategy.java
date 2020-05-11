package pl.edu.agh.to.lab4.filter;

import pl.edu.agh.to.lab4.model.Person;
import pl.edu.agh.to.lab4.model.Suspect;

public class AgeSearchStrategy implements SearchStrategy {

    private int age;

    public AgeSearchStrategy(int age) {
        this.age = age;
    }


    @Override
    public boolean filter(Suspect suspect) {
        if (suspect instanceof Person) {
            return this.age == ((Person) suspect).getAge();
        }
        return false;
    }
}
