package pl.edu.agh.to.lab4.database;

import pl.edu.agh.to.lab4.CompositeAggregate;
import pl.edu.agh.to.lab4.filter.SearchStrategy;
import pl.edu.agh.to.lab4.model.Suspect;

import java.util.Iterator;

public interface SuspectAggregate {
    Iterator<Suspect> iterator(SearchStrategy searchStrategy);
}
