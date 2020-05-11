package pl.edu.agh.to.lab4.database;

import pl.edu.agh.to.lab4.SuspectIterator;
import pl.edu.agh.to.lab4.filter.SearchStrategy;
import pl.edu.agh.to.lab4.model.Person;
import pl.edu.agh.to.lab4.model.Suspect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class PersonDatabase implements SuspectAggregate {

    private final Collection<Person> cracovPersons = new ArrayList<Person>();

    public PersonDatabase() {
    }

    @Override
    public Iterator<Suspect> iterator(SearchStrategy searchStrategy) {
        return new SuspectIterator(cracovPersons.iterator(), searchStrategy);
    }

    public void generateInitialData() {
        addCracovPerson("Jan", "Kowalski", 30);
        addCracovPerson("Janusz", "Krakowski", 30);
        addCracovPerson("Janusz", "Mlodociany", 10);
        addCracovPerson("Kasia", "Kosinska", 19);
        addCracovPerson("Piotr", "Zgredek", 29);
        addCracovPerson("Tomek", "Gimbus", 14);
        addCracovPerson("Janusz", "Gimbus", 15);
        addCracovPerson("Alicja", "Zaczarowana", 22);
        addCracovPerson("Janusz", "Programista", 77);
        addCracovPerson("Pawel", "Pawlowicz", 32);
        addCracovPerson("Krzysztof", "Mendel", 30);
    }

    public Collection<Person> getCracovPersons() {
        return this.cracovPersons;
    }

    public void addCracovPerson(String firstName, String lastName, int age) {
        cracovPersons.add(new Person(firstName, lastName, age));
    }
}
