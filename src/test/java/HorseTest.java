import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {

    @Test
    public void nameNullExceptionMessage() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));

        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t\t", "\n\n\n\n\n"})
    public void nameBlancExceptionMessage(String name) {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));

        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void speedNegativeExceptionMessage() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse("Bucephalus", -1, 1));

        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    public void distanceNegativeExceptionMessage() {
        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> new Horse("Bucephalus", 1, -1));

        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void getNameTest() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Bucephalus", 1, 1);

        Field name = Horse.class.getDeclaredField("name");
        name.setAccessible(true);
        String actualName = (String) name.get(horse);

        assertEquals("Bucephalus", actualName);
    }

    @Test
    public void getSpeedTest() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Bucephalus", 1, 1);

        Field speed = Horse.class.getDeclaredField("speed");
        speed.setAccessible(true);
        Double actualSpeed = (Double) speed.get(horse);

        assertEquals(1, actualSpeed);
    }

    @Test
    public void getDistanceTest() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Bucephalus", 1, 1);

        Field distance = Horse.class.getDeclaredField("distance");
        distance.setAccessible(true);
        Double actualDistance = (Double) distance.get(horse);

        assertEquals(1, actualDistance);
    }

    @Test
    public void getDurationDefaultConstructor() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Bucephalus", 1);
        Field distance = Horse.class.getDeclaredField("distance");
        distance.setAccessible(true);

        Double actualDistance = (Double) distance.get(horse);
        assertEquals(0, actualDistance);
    }
}
