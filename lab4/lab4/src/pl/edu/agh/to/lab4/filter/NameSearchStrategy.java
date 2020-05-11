package pl.edu.agh.to.lab4.filter;

import pl.edu.agh.to.lab4.model.Suspect;

public class NameSearchStrategy implements SearchStrategy {

    private String name;

    public NameSearchStrategy(String name) {
        this.name = name;
    }

    @Override
    public boolean filter(Suspect suspect) {
        return suspect.getFirstName().equals(this.name);
    }
}
