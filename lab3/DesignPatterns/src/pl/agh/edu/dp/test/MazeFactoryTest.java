package pl.agh.edu.dp.test;

import org.junit.jupiter.api.Test;
import pl.agh.edu.dp.labirynth.factories.MazeFactory;

import static org.junit.jupiter.api.Assertions.*;

class MazeFactoryTest {

    @Test
    void getInstance() {
        MazeFactory factory = MazeFactory.getInstance();

        assertEquals(factory, MazeFactory.getInstance());
        assertEquals(factory, MazeFactory.getInstance());

        MazeFactory factory2 = MazeFactory.getInstance();

        assertEquals(factory2, MazeFactory.getInstance());
        assertEquals(factory2, MazeFactory.getInstance());

        assertEquals(factory, factory2);
    }
}