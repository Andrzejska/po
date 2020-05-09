# Laboratorium 4
## Refaktoryzacja
## Autorzy Andrii Trishch, Uladzislau Tumilovich

#### Krok 1. Diagram UML:

##### Diagram podany na cwiczeniu
![UML-CW](uml-cw.png)

##### Nasz diagram:
![UML](uml.png)

W odrużnieniu od diagramu podanego na cwiczeniu nasz diagram wyświetlia zależność między klasami **Finder** i **PrisonerDataProvider**/**PersonDatabase**, a nie  asocjacje, bo podane wyszej klasy nie zawierają objektów klasy **Finder**.

#### Krok 2. Poprawa podstawowych blędów:

**a)** Została zmieniona klasa Prisoners. Do atrybutów **firstname**, **lastname** został zmieniony dostęp z *publicznego* na *przywatny*, z czego wynikła potrzeba stworzenia geterów, nazwa metody **display()** na **toString()**, **firstname()** na **getFirstName()**, **middleName** na **getLastName**, czy **MoglbyEwentualnieBycPodejrzany()** na **canBesuspected()**, bo jest ważne pisanie nazw wszystkich metod w jedny jężyku. 

```java
public class Prisoner {
    private final String firstName;

    private final String lastName;

    private final int judgementYear;

    private final int sentenceDuration;

    private final String pesel;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Prisoner(String firstName, String lastName, String pesel, int judgementYear, int sentenceDuration) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.judgementYear = judgementYear;
        this.sentenceDuration = sentenceDuration;
    }

    public String getPesel() {
        return pesel;
    }

    public boolean canBeSuspected() {
        return judgementYear + sentenceDuration >= getCurrentYear();
    }

    public int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public String toString() {
        return firstName+ " " + lastName;
    }
}
```

**b)** Zostala zmieniona klasa Prisoners Database. Zmieniono nazwe metody **render()** na **toString()**, **findAll()** na **getPrisoners()**, **getAllPrisons()** na **getPrisons()**.

```java
public class PrisonersDatabase {

    private final Map<String, Collection<Prisoner>> prisoners = new HashMap<String, Collection<Prisoner>>();

    public PrisonersDatabase() {
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
        return prisoners;
    }

    public Collection<String> getPrisons() {
        return prisoners.keySet();
    }

    private void addPrisoner(String category, Prisoner prisoner) {
        if (!prisoners.containsKey(category))
            prisoners.put(category, new ArrayList<Prisoner>());
        prisoners.get(category).add(prisoner);
    }
}
```

**c)** Zostala zmieniona nazwa klasy **PersonDataProvider** na **PersonDatabase**. Nazwa List **cracovCitizens**na **cracovPersons**, nazwa metody **getAllCracovCitizens** na **getCracovPersons**, została stworzona metoda **addCracovPerson(firstName,lastName,age)**, żeby zmiejszyc powtorzenie kodu.

```java
public class PersonDatabase {

    private final Collection<Person> cracovPersons = new ArrayList<Person>();

    public PersonDatabase() {
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
        return cracovPersons;
    }
    private void addCracovPerson(String firstName,String lastName,int age){
        this.cracovPersons.add(new Person(firstName,lastName,age));
    }
}
```
**d)** Została zmieniona klasa **Person**. Atrybuty **firstname**, **lastname** zmienione na **firstName**, **lastName**, nazwa metody **firstname()** na **getFirstName()**,metody **lastname()** na **getLastName()**,
```java
public class Person {
    private String firstName;

    private String lastName;

    private int age;

    public Person(String firstName, String lastName, int age) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String toString() {
        return firstName + " " + lastName;
    }
}
```

**e)** Została zmieniona klasa **Finder**, zmiany której wynikają z wynikają z wprowadzonych powyszej modyfikacji. 
```java
public class Finder {
    private final Collection<Person> allPersons;
    private final Map<String, Collection<Prisoner>> allPrisoners;

    public Finder(Collection<Person> allPersons, Map<String, Collection<Prisoner>> allPrisoners) {
        this.allPersons = allPersons;
        this.allPrisoners = allPrisoners;
    }

    public Finder(PersonDatabase personDatabase, PrisonersDatabase prisonersDatabase) {
        this(personDatabase.getCracovPersons(), prisonersDatabase.getPrisoners());
    }

    public void displayAllSuspectsWithName(String name) {
        ArrayList<Prisoner> suspectedPrisoners = new ArrayList<Prisoner>();
        ArrayList<Person> suspectedPersons = new ArrayList<Person>();

        for (Collection<Prisoner> prisonerCollection : allPrisoners.values()) {
            for (Prisoner prisoner : prisonerCollection) {
                if (!prisoner.canBeSuspected() && prisoner.getFirstName().equals(name)) {
                    suspectedPrisoners.add(prisoner);
                }
                if (suspectedPrisoners.size() >= 10) {
                    break;
                }
            }
            if (suspectedPrisoners.size() >= 10) {
                break;
            }
        }

        if (suspectedPrisoners.size() < 10) {
            for (Person person : allPersons) {
                if (person.getAge() > 18 && person.getFirstName().equals(name)) {
                    suspectedPersons.add(person);
                }
                if (suspectedPrisoners.size() + suspectedPersons.size() >= 10) {
                    break;
                }
            }
        }

        int t = suspectedPrisoners.size() + suspectedPersons.size();
        System.out.println("Znalazlem " + t + " pasujacych podejrzanych!");

        for (Prisoner n : suspectedPrisoners) {
            System.out.println(n.toString());
        }

        for (Person p : suspectedPersons) {
            System.out.println(p.toString());
        }
    }
}
```
