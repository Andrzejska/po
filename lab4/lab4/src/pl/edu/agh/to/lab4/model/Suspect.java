package pl.edu.agh.to.lab4.model;

public abstract class Suspect {

    protected String firstName;
    protected String lastName;

    public String getFirstName() { return this.firstName; }

    public String getLastName() { return this.lastName; }

    public String toString() {
        return this.firstName + " " + this.lastName;
    }

    public abstract boolean canBeSuspected();
}
