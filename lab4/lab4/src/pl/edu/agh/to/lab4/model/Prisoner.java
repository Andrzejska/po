package pl.edu.agh.to.lab4.model;

import java.util.Calendar;

public class Prisoner extends Suspect {
    private final int judgementYear;
    private final int sentenceDuration;
    private final String pesel;

    public Prisoner(String firstName, String lastName, String pesel, int judgementYear, int sentenceDuration) {
        this.judgementYear = judgementYear;
        this.sentenceDuration = sentenceDuration;
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getPesel() {
        return this.pesel;
    }

    public boolean canBeSuspected() {
        return judgementYear + sentenceDuration < getCurrentYear();
    }

    private int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
}