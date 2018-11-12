package com.vagnercazarotto.mariobros.Sprites;

import com.badlogic.gdx.maps.MapObject;
import com.vagnercazarotto.mariobros.Screens.PlayScreen;

/**
 * Created by vagner on 02/02/2016.
 */
public class Pipes extends InteractijectveTileObject {
    public Pipes(PlayScreen screen,MapObject object){

        super(screen, object);
    }

    @Override
    public void onHeadHit() {

    }
}
