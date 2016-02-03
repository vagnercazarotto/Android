package com.vagnercazarotto.mariobros.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;



/**
 * Created by vagner on 02/02/2016.
 */
public class Coin extends InteractijectveTileObject {
    public Coin(World world,TiledMap map,Rectangle bounds){

        super(world, map, bounds);
        // we're setting the user data to object it self
        fixture.setUserData(this);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Hit the Coin","");
    }
}
