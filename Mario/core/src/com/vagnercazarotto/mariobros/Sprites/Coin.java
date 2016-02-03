package com.vagnercazarotto.mariobros.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.vagnercazarotto.mariobros.MarioBros;
import com.vagnercazarotto.mariobros.Scenes.Hud;
import com.vagnercazarotto.mariobros.Screens.PlayScreen;


/**
 * Created by vagner on 02/02/2016.
 */
public class Coin extends InteractijectveTileObject {
    // now we want to chang the icon for the Coin when Mario hit it
    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 28;


    public Coin(PlayScreen screen,Rectangle bounds){

        super(screen, bounds);
        // find the tile the find the icon
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        // we're setting the user data to object it self
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.COIN_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Coin", "Collision");
        if (getCell().getTile().getId() == BLANK_COIN)
            MarioBros.manager.get("audio/sounds/bump.wav", Sound.class).play();
        else
            MarioBros.manager.get("audio/sounds/coin.wav",Sound.class).play();
        getCell().setTile(tileSet.getTile(BLANK_COIN));
        Hud.addScore(100);
    }
}
