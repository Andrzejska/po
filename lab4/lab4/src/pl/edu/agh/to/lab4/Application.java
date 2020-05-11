package pl.edu.agh.to.lab4;

import pl.edu.agh.to.lab4.database.PersonDatabase;
import pl.edu.agh.to.lab4.database.PrisonersDatabase;
import pl.edu.agh.to.lab4.database.StudentDatabase;
import pl.edu.agh.to.lab4.database.SuspectAggregate;
import pl.edu.agh.to.lab4.filter.AgeSearchStrategy;
import pl.edu.agh.to.lab4.filter.CompositeSearchStrategy;
import pl.edu.agh.to.lab4.filter.NameSearchStrategy;
import pl.edu.agh.to.lab4.filter.SearchStrategy;
import pl.edu.agh.to.lab4.model.Student;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        PersonDatabase personDatabase = new PersonDatabase();
        personDatabase.generateInitialData();
        PrisonersDatabase prisonerDatabase = new PrisonersDatabase();
        prisonerDatabase.generateInitialData();
        StudentDatabase studentDatabase = new StudentDatabase();
        studentDatabase.generateInitialData();

        List<SuspectAggregate> databases = new ArrayList<>();
        databases.add(personDatabase);
        databases.add(prisonerDatabase);
        databases.add(studentDatabase);

        Finder suspects = new Finder(new CompositeAggregate(databases));

        List<SearchStrategy> strategies = new ArrayList<>();
        strategies.add(new NameSearchStrategy("Janusz"));
        strategies.add(new AgeSearchStrategy(77));

        suspects.display(new CompositeSearchStrategy(strategies));
    }
}
