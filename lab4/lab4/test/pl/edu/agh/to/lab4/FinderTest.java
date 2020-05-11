package pl.edu.agh.to.lab4;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to.lab4.database.PersonDatabase;
import pl.edu.agh.to.lab4.database.PrisonersDatabase;
import pl.edu.agh.to.lab4.database.SuspectAggregate;
import pl.edu.agh.to.lab4.filter.NameSearchStrategy;
import pl.edu.agh.to.lab4.model.Prisoner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FinderTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private PrintStream originalOut;
    PersonDatabase personDatabase = new PersonDatabase();
    PrisonersDatabase prisonersDatabase = new PrisonersDatabase();
    private Finder suspectFinder;

    public FinderTest() {
        List<SuspectAggregate> databases = new ArrayList<>();
        databases.add(personDatabase);
        databases.add(prisonersDatabase);
        suspectFinder = new Finder(new CompositeAggregate(databases));
    }

    @Test
    public void testDisplayingNotJailedPrisoner() {
        addPrisoner("Wiezeienie stanowe", new Prisoner("Jan", "Kowalski", "802104543357", 2000, 1));
        suspectFinder.display(new NameSearchStrategy("Jan"));
        assertContentIsDisplayed("Jan Kowalski");
    }

    @Test
    public void testDisplayingSuspectedPerson() {
        personDatabase.addCracovPerson("Jan", "Kowalski", 20);
        suspectFinder.display(new NameSearchStrategy("Jan"));
        assertContentIsDisplayed("Jan Kowalski");
    }

    @Test
    public void testNotDisplayingTooYoungPerson() {
        personDatabase.addCracovPerson("Janusz", "Kowalski", 15);
        suspectFinder.display(new NameSearchStrategy("Jan"));
        assertContentIsNotDisplayed("Janusz Kowalski");
    }

    @Test
    public void testNotDisplayingJailedPrisoner() {
        personDatabase.addCracovPerson("Jan", "Kowalski", 20);
        addPrisoner("Wiezeienie stanowe", new Prisoner("Jan", "Kowalski2", "802104543357", 2000, 20));
        suspectFinder.display(new NameSearchStrategy("Jan"));
        assertContentIsNotDisplayed("Jan Kowalski2");
    }

    private void assertContentIsDisplayed(String expectedContent) {
        assertTrue("Application did not contain expected content: " + outContent.toString(), outContent.toString()
                .contains(expectedContent));
    }

    private void assertContentIsNotDisplayed(String expectedContent) {
        assertFalse("Application did contain expected content although it should not: " + outContent.toString(), outContent.toString()
                .contains(expectedContent));
    }

    @Before
    public void redirectSystemOut() {
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void resetSystemOut() {
        System.setOut(originalOut);
    }

    private void addPrisoner(String category, Prisoner news) {
        if (!prisonersDatabase.getPrisoners().containsKey(category))
            prisonersDatabase.getPrisoners().put(category, new ArrayList<Prisoner>());
        prisonersDatabase.addPrisoner(category, news);
    }
}
