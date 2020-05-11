package pl.edu.agh.to.lab4.database;

import pl.edu.agh.to.lab4.SuspectIterator;
import pl.edu.agh.to.lab4.filter.SearchStrategy;
import pl.edu.agh.to.lab4.model.Student;
import pl.edu.agh.to.lab4.model.Suspect;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class StudentDatabase implements SuspectAggregate {

    private final Collection<Student> students = new ArrayList<Student>();

    public StudentDatabase() {
    }

    @Override
    public Iterator<Suspect> iterator(SearchStrategy searchStrategy) {
        return new SuspectIterator(students.iterator(), searchStrategy);
    }

    public void generateInitialData() {
        addStudent("Bronislaw", "Filan", "403432");
        addStudent("Wandelin", "Kosman", "347634");
        addStudent("Remigiusz", "Pelka", "675476");
        addStudent("Konstantyn", "Kaszuba", "236523");
        addStudent("Denis", "Kukulski", "867522");
        addStudent("Konstanty", "Krasny", "563465");
        addStudent("Janusz", "Rojewski", "234543");
        addStudent("Albert", "Mikel", "763465");
        addStudent("Janusz", "Rogowski", "346434");
        addStudent("Denis", "Pawlowicz", "764534");
        addStudent("Bronislaw", "Mendel", "565465");
    }

    public Collection<Student> getStudents() {
        return students;
    }

    public void addStudent(String firstName, String lastName, String index) {
        students.add(new Student(firstName, lastName, index));
    }
}
