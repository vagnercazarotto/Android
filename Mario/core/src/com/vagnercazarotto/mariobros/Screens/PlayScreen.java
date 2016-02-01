package com.vagnercazarotto.mariobros.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.vagnercazarotto.mariobros.MarioBros;

/**
 * Created by vagner on 01/02/2016.
 */
public class PlayScreen implements Screen{

    Texture texture;


    // Ok now, we need to add a custom constructor because we're actually sending the game itself
    private MarioBros game;
    public PlayScreen(MarioBros game){
        this.game = game;
        texture = new Texture("badlogic.jpg");
    }




    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // first thing , clear the screen
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Next we gotta draw attention to the screen but, we had the first
        // open up the batch files
        game.batch.begin();
        game.batch.draw(texture,0,0);
        game.batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
