package com.imaginat.tetriscombat;

import com.imaginat.tetriscombat.framework.Screen;
import com.imaginat.tetriscombat.framework.implementation.AndroidGame;

/**
 * Created by nat on 3/20/16.
 */
public class TetrisCombat extends AndroidGame {

    @Override
    public Screen getStartScreen() {
        return new TempScreen(this);
    }
}
