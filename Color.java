import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Color {
    /**
     * Color enum represents different colors which can be use in the game by a Square
     *
     * Following list can be extended with other colors
     *
     * Current implementation of the enum has been sourced from:
     * https://stackoverflow.com/questions/1972392/java-pick-a-random-value-from-an-enum
     *
     */
    BLEU,
    ROUGE,
    VERT;

    private static final List<Color> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * Returns a random Color object with value from the enum.
     *
     * @return  the color randomly created
     */
    public static Color randomColor()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}






