package pl.edu.agh.to.lab4;

import pl.edu.agh.to.lab4.filter.SearchStrategy;
import pl.edu.agh.to.lab4.model.Suspect;

import java.util.ArrayList;
import java.util.Iterator;

public class Finder {
   private final CompositeAggregate compositeAggregate;

    public Finder(CompositeAggregate compositeAggregate) {
        this.compositeAggregate = compositeAggregate;
    }

    public void display(SearchStrategy searchStrategy) {
        ArrayList<Suspect> suspectPeople = new ArrayList<Suspect>();
        Iterator<Suspect> suspectIterator = compositeAggregate.iterator(searchStrategy);

        while (suspectIterator.hasNext()) {
           suspectPeople.add(suspectIterator.next());
        }

        System.out.println("Znalazlem " + suspectPeople.size() + " pasujacych podejrzanych!");

        for (Suspect suspect: suspectPeople) {
            System.out.println(suspect.toString());
        }
    }
}
