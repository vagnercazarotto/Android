package com.vagnercazarotto.mariobros.Sprites.Enemies;

import com.vagnercazarotto.mariobros.Screens.PlayScreen;

/**
 * Created by vagner on 02/02/2016.
 */
public class Turtles extends Enemy {


    public Turtles(PlayScreen screen, float x, float y) {
        super(screen, x, y);
    }

    @Override
    protected void defineEnemy() {

    }

    @Override
    public void hitOnHead() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void reverseVelocity(boolean x, boolean y) {
        super.reverseVelocity(x, y);
    }
}
