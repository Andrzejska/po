package pl.edu.agh.to.lab4.model;

public class Person extends Suspect {
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public boolean canBeSuspected() {
        return age > 18;
    }
}
