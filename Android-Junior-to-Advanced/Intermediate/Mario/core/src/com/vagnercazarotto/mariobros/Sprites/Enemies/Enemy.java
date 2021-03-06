package com.vagnercazarotto.mariobros.Sprites.Enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.vagnercazarotto.mariobros.Screens.PlayScreen;


/**
 * Created by vagner on 03/02/2016.
 */
public abstract class Enemy extends Sprite{
    protected World world;
    protected PlayScreen screen;
    public Body b2body;
    public Vector2 velocity;

    public Enemy(PlayScreen screen,float x,float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x,y);
        defineEnemy();
        velocity = new Vector2(-1,-2);
        // Summon the Enemy in correct time
        b2body.setActive(false);
    }


    protected abstract void defineEnemy();
    public abstract void hitOnHead();
    public abstract void update(float dt);

    public void reverseVelocity(boolean x,boolean y){
        // verify if it's true and revert the velocity
        if (x)
            velocity.x = -velocity.x;
        if (y)
            velocity.y = -velocity.y;
    }



}
