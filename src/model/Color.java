package src.model;

import java.util.*;

/**
 * src.model.Color enum represents different colors which can be use in the game by a src.model.Square
 *
 * Following list can be extended with other colors
 *
 * Current implementation of the enum has been sourced from:
 * https://stackoverflow.com/questions/1972392/java-pick-a-random-value-from-an-enum
 *
 */
public enum Color {
    BLUE,
    RED,
    GREEN,
    RAINBOW;

    private static final List<Color> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * Returns a random src.model.Color object with value from the enum.
     *
     * @return  the color randomly created
     */
    public static Color randomColor()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
    public java.awt.Color getAwtColor() {
        return map.get(this);
    }

    private static final Map<Color, java.awt.Color> map;
    static {
        Map<Color, java.awt.Color> initMap = new HashMap<Color, java.awt.Color>();
        initMap.put(Color.BLUE, java.awt.Color.BLUE);
        initMap.put(Color.RED, java.awt.Color.RED);
        initMap.put(Color.GREEN, java.awt.Color.GREEN);
        initMap.put(Color.RAINBOW, java.awt.Color.CYAN);
        initMap.put(null, java.awt.Color.DARK_GRAY);
        map = Collections.unmodifiableMap(initMap);
    }

}






