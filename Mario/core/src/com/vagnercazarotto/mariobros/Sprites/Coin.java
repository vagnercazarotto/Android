package com.vagnercazarotto.mariobros.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.vagnercazarotto.mariobros.MarioBros;
import com.vagnercazarotto.mariobros.Scenes.Hud;


/**
 * Created by vagner on 02/02/2016.
 */
public class Coin extends InteractijectveTileObject {
    // now we want to chang the icon for the Coin when Mario hit it
    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 28;


    public Coin(World world,TiledMap map,Rectangle bounds){

        super(world, map, bounds);
        // find the tile the find the icon
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        // we're setting the user data to object it self
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.COIN_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Coin","Collision");
        getCell().setTile(tileSet.getTile(BLANK_COIN));
        Hud.addScore(100);
    }
}
