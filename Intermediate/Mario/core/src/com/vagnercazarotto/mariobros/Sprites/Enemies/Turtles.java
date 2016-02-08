package com.vagnercazarotto.mariobros.Sprites.Enemies;

import com.badlogic.gdx.math.Rectangle;
import com.vagnercazarotto.mariobros.Screens.PlayScreen;
import com.vagnercazarotto.mariobros.Sprites.InteractijectveTileObject;

/**
 * Created by vagner on 02/02/2016.
 */
public class Turtles extends InteractijectveTileObject {
    public Turtles(PlayScreen screen,Rectangle bounds){

        super(screen, bounds);
    }

    @Override
    public void onHeadHit() {

    }
}
