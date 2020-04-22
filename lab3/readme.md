# Laboratorium 3
## Wzorce projektowe

#### 1. Builder:
Zdefiniuj nową wersję funkcji składowej createMaze, która będzie przyjmować jako argument
obiekt budujący klasy MazeBuilder.

**a)** Stwórz klasę MazeBuilder, która definiuje interfejs służący do tworzenia labiryntów.
Co musi tam być zawarte? Wykorzystaj wiedzę nt. składowych, które są w labiryncie


```java
public interface MazeBuilder {
    void addRoom(Room room);
    void addDoor(Room r1,Room r2)throws Exception;
    void addCommonWall(Direction roomDirection,Room r1,Room r2)throws Exception;
}
```

**b)** Po utworzeniu powyższego interfejsu zmodyfikuj funkcje składową tak, aby przyjmowała jako parametr obiekt tej klasy.

```java
public class MazeGame {
    public Maze createMaze(StandardMazeBuilder builder) throws Exception {
        Room r1 = new Room(1), r2 = new Room(2);
        builder.addRoom(r1);
        builder.addRoom(r2);
        builder.addCommonWall(North,r1,r2);
        builder.addDoor(r1, r2);
        return builder.getCurrentMaze();
    }
}
```
**d)**  Stwórz klasę StandardBuilderMaze będącą implementacją MazeBuildera.
```java
public class StandardMazeBuilder implements MazeBuilder {
    private Maze currentMaze;

    public StandardMazeBuilder() {
        this.currentMaze = new Maze();
    }

    @Override
    public void addRoom(Room room) {
        room.setSide(South, new Wall());
        room.setSide(North, new Wall());
        room.setSide(East, new Wall());
        room.setSide(West, new Wall());
        currentMaze.addRoom(room);
    }

    @Override
    public void addDoor(Room r1, Room r2) throws Exception {
        Direction r1Door = null;
        for (Direction dir : Direction.values()) {
            if (r1.getSide(dir).equals(r2.getSide(dir.getOppositeSide()))) {
                r1Door = dir;
                break;
            }
        }
        if (r1Door == null) throw new Exception("Seems like rooms don't have common door");
        else {
            Door newDoor = new Door(r1, r2);
            r1.setSide(r1Door, newDoor);
            r2.setSide(r1Door.getOppositeSide(), newDoor);
        }
    }

    @Override
    public void addCommonWall(Direction r1Direction, Room r1, Room r2) throws Exception {
        MapSite side = r1.getSide(r1Direction);
        if (side == null) throw new Exception("Seems like such a room doesn't exist");
        r2.setSide(r1Direction.getOppositeSide(), side);
    }

    public Maze getCurrentMaze() {
        return this.currentMaze;
    }
}
```

**e)** Utwórz labirynt przy pomocy operacji createMaze, gdzie parametrem będzie obiekt
klasy StandardMazeBuilder.
```java
public class Main {
    public static void main(String[] args) throws Exception {
        MazeGame mazeGame = new MazeGame();
        MazeBuilder builder=new StandardMazeBuilder();
        Maze maze = mazeGame.createMaze((StandardMazeBuilder) builder);
        System.out.println(maze.getRoomNumbers());
    }
}
```
**f)** Stwórz kolejną podklasę MazeBuildera o nazwie CountingMazeBuilder. Budowniczy
tego obiektu w ogóle nie tworzy labiryntu, a jedynie zlicza utworzone komponenty
różnych rodzajów. Powinien mieć metodę GetCounts, która zwraca ilość elementów
```java
public class CountingMazeBuilder implements MazeBuilder {
    private int elementsNumber = 0;

    @Override
    public void addRoom(Room room) {
        elementsNumber += 5;

    }

    @Override
    public void addDoor(Room r1, Room r2) throws Exception {
        elementsNumber++;
    }

    @Override
    public void addCommonWall(Direction roomDirection, Room r1, Room r2) throws Exception {
        elementsNumber--;
    }

    int GetCounts() {
        return elementsNumber;
    }
}
```

