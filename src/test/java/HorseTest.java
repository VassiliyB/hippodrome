import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

}
