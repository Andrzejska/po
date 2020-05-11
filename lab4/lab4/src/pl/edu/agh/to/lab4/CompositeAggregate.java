package pl.edu.agh.to.lab4;

import pl.edu.agh.to.lab4.database.SuspectAggregate;
import pl.edu.agh.to.lab4.filter.SearchStrategy;
import pl.edu.agh.to.lab4.model.Suspect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CompositeAggregate implements SuspectAggregate {

    private final List<SuspectAggregate> databases;

    public CompositeAggregate(List<SuspectAggregate> databases) {
        this.databases = databases;
    }

    @Override
    public Iterator<Suspect> iterator(SearchStrategy searchStrategy) {
        Collection<Suspect> suspects = new ArrayList<>();
        databases.forEach(data -> {
            Iterator<Suspect> iterator = data.iterator(searchStrategy);
            while (iterator.hasNext()) {
                suspects.add(iterator.next());
            }
        });
        return suspects.iterator();
    }
}
