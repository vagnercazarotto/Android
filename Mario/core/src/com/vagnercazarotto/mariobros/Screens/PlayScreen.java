package com.vagnercazarotto.mariobros.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vagnercazarotto.mariobros.MarioBros;
import com.vagnercazarotto.mariobros.Scenes.Hud;

/**
 * Created by vagner on 01/02/2016.
 */
public class PlayScreen implements Screen{


    // Ok now, we need to add a custom constructor because we're actually sending the game itself
    private MarioBros game;
    // now we need to create the graphic camera for the game look similar in many cellphones
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;


    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;



    public PlayScreen(MarioBros game){
        this.game = game;
        // create cam used to follow mario through world
        gamecam = new OrthographicCamera();
        // create a FitVIewPort to maintain virtual aspect radio
        gamePort = new FitViewport(MarioBros.V_WIDTH,MarioBros.V_HEIGHT,gamecam);
        // create our game HUD for scores/timers/level info
        hud = new Hud(game.batch);

        // start rendering the map
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);

    }



    public void handleInput(float dt){
        // Check if any input happening
        if (Gdx.input.isTouched())
            gamecam.position.x +=100 * dt;
    }

    public void update(float dt){
        handleInput(dt);
        gamecam.update();
        // this only will render what our camera can see
        renderer.setView(gamecam);
    }




    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update(delta);

        // first thing , clear the screen
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // clean the screen before
        renderer.render();

        // this will show the our camera we're see HUD
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // we need to change the size of our screen for the ViewPort
        gamePort.update(width,height);

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
