package pl.edu.agh.to.lab4.database;

import pl.edu.agh.to.lab4.CompositeAggregate;
import pl.edu.agh.to.lab4.SuspectIterator;
import pl.edu.agh.to.lab4.filter.SearchStrategy;
import pl.edu.agh.to.lab4.model.Prisoner;
import pl.edu.agh.to.lab4.model.Suspect;

import java.util.*;
import java.util.stream.Collectors;

public class PrisonersDatabase implements SuspectAggregate {

    private final Map<String, Collection<Prisoner>> prisoners = new HashMap<String, Collection<Prisoner>>();

    public PrisonersDatabase() {
    }

    @Override
    public Iterator<Suspect> iterator(SearchStrategy searchStrategy) {
        return new SuspectIterator(prisoners
                .values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList())
                .iterator(),
                searchStrategy
        );
    }

    public void generateInitialData() {
        addPrisoner("Wiezienie krakowskie", new Prisoner("Jan", "Kowalski", "87080452357", 2005, 7));
        addPrisoner("Wiezienie krakowskie", new Prisoner("Anita", "Wiercipieta", "84080452357", 2009, 3));
        addPrisoner("Wiezienie krakowskie", new Prisoner("Janusz", "Zlowieszczy", "92080445657", 2001, 10));
        addPrisoner("Wiezienie przedmiejskie", new Prisoner("Janusz", "Zamkniety", "802104543357", 2010, 5));
        addPrisoner("Wiezienie przedmiejskie", new Prisoner("Adam", "Future", "880216043357", 2020, 5));
        addPrisoner("Wiezienie przedmiejskie", new Prisoner("Zbigniew", "Nienajedzony", "90051452335", 2011, 1));
        addPrisoner("Wiezienie centralne", new Prisoner("Jan", "Przedziwny", "91103145223", 2009, 4));
        addPrisoner("Wiezienie centralne", new Prisoner("Janusz", "Podejrzany", "85121212456", 2012, 1));
    }

    public Map<String, Collection<Prisoner>> getPrisoners() {
        return this.prisoners;
    }

    public Collection<String> getPrisons() {
        return prisoners.keySet();
    }

    public void addPrisoner(String category, Prisoner prisoner) {
        if (!prisoners.containsKey(category))
            prisoners.put(category, new ArrayList<Prisoner>());
        prisoners.get(category).add(prisoner);
    }
}
