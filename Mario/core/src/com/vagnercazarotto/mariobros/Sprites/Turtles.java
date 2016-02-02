package com.vagnercazarotto.mariobros.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by vagner on 02/02/2016.
 */
public class Turtles extends InteractijectveTileObject{
    public Turtles(World world,TiledMap map,Rectangle bounds){

        super(world, map, bounds);
    }
}
