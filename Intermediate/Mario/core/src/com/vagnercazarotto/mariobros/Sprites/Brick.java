package com.vagnercazarotto.mariobros.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.vagnercazarotto.mariobros.MarioBros;
import com.vagnercazarotto.mariobros.Scenes.Hud;
import com.vagnercazarotto.mariobros.Screens.PlayScreen;


/**
 * Created by vagner on 02/02/2016.
 */
public class Brick extends InteractijectveTileObject {
    public Brick(PlayScreen screen,Rectangle bounds){

        super(screen,bounds);
        // we're setting the user data to object it self
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("Brick","Collision");
        setCategoryFilter(MarioBros.DESTROYED_BIT);
        getCell().setTile(null);
        Hud.addScore(200);
        MarioBros.manager.get("audio/sounds/think_i_do2.wav",Sound.class).play();
    }
}
