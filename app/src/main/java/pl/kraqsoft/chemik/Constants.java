package pl.kraqsoft.chemik;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by krakus on 12/27/2017.
 */

public class Constants {
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static final int ATOM_RADIUS = 50;
    public static final int ATOM_HITBOX = 60;
    public static final int TEXT_SIZE = ATOM_RADIUS/2;
    public static final String[] ATOMS = {"H", "C", "O", "He", "Na", "Si", "Au", "Pt", "S", "Ag"};
    public static final String[][] PATTERN_ARRAY = {
            {"H","H","O"},
            {"H", "Na", "O"},
            {"C","C","H","H","H","H","H","H","O"},
            {"C","H","H","H","H"},
            {"C","O","O"}
    };

    public static String getRandomAtom(){
        return ATOMS[new Random().nextInt(ATOMS.length)];
    }
    public static String[] getRandomPattern(){return PATTERN_ARRAY[new Random().nextInt(PATTERN_ARRAY.length)];}

}
