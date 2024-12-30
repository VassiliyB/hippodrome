import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

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
    public void getDistanceDefaultConstructor() throws NoSuchFieldException, IllegalAccessException {

        Horse horse = new Horse("Bucephalus", 1);
        Field distance = Horse.class.getDeclaredField("distance");
        distance.setAccessible(true);

        Double actualDistance = (Double) distance.get(horse);
        assertEquals(0, actualDistance);
    }

    @Test
    public void moveUseGetRandom() {
        try(MockedStatic<Horse> mockedHorseStatic = Mockito.mockStatic(Horse.class)) {

            new Horse("Bucephalus", 1, 1).move();
            mockedHorseStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.8, 0.5, 0.3, 0.456, 100.555})
    public void moveAssignsValueToDistance(double random) {
        try(MockedStatic<Horse> mockedHorseStatic = Mockito.mockStatic(Horse.class)) {
            
            Horse horse = new Horse("Bucephalus", 5.0, 4.0);
            mockedHorseStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(4.0 + 5.0 * random, horse.getDistance());
        }
    }
}
