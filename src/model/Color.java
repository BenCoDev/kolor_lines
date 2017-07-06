package model;

import java.util.*;

/**
 * Color enum represents different colors which can be use in the game by a Square
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

    /**
     * Returns a random Color object with value from the enum.
     *
     * @return  the color randomly created
     */
    public static Color randomColor()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    public java.awt.Color[] getAwtColors() {
        return map.get(this);
    }

    private static final Map<Color, java.awt.Color[]> map;
    static {
        Map<Color, java.awt.Color[]> initMap = new HashMap<Color, java.awt.Color[]>();
        initMap.put(Color.BLUE, new java.awt.Color[]{
            java.awt.Color.BLUE
        });
        initMap.put(Color.RED, new java.awt.Color[]{
            java.awt.Color.RED
        });
        initMap.put(Color.GREEN, new java.awt.Color[]{
            java.awt.Color.GREEN
        });
        initMap.put(Color.RAINBOW, new java.awt.Color[]{
            java.awt.Color.RED,
            java.awt.Color.YELLOW,
            java.awt.Color.GREEN,
            java.awt.Color.BLUE,
            java.awt.Color.MAGENTA
        });
        initMap.put(null, new java.awt.Color[]{
            java.awt.Color.DARK_GRAY
        });
        map = Collections.unmodifiableMap(initMap);
    }

    private static final List<Color> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
}






