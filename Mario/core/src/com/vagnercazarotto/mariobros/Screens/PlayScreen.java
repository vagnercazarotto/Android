package com.vagnercazarotto.mariobros.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vagnercazarotto.mariobros.MarioBros;
import com.vagnercazarotto.mariobros.Scenes.Hud;
import com.vagnercazarotto.mariobros.Sprites.Goombas;
import com.vagnercazarotto.mariobros.Sprites.Mario;
import com.vagnercazarotto.mariobros.Tools.B2WorldCreator;
import com.vagnercazarotto.mariobros.Tools.WorldContactListener;

/**
 * Created by vagner on 01/02/2016.
 */
public class PlayScreen implements Screen{


    // Ok now, we need to add a custom constructor because we're actually sending the game itself
    private MarioBros game;
    private TextureAtlas atlas;

    // now we need to create the graphic camera for the game look similar in many cellphones
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    // Tiled map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    // Box2d Variables
    private World world;
    private Box2DDebugRenderer b2dr;

    //sprites
    private Mario player;
    private Music music;

    // Create a temp Enemy
    private Goombas goombas;




    public PlayScreen(MarioBros game){
        // Start Atlas
        atlas = new TextureAtlas("Mario_and_Enemies.pack");


        this.game = game;
        // create cam used to follow mario through world
        gamecam = new OrthographicCamera();
        // create a FitVIewPort to maintain virtual aspect radio
        gamePort = new FitViewport(MarioBros.V_WIDTH / MarioBros.PPM ,MarioBros.V_HEIGHT / MarioBros.PPM  ,gamecam);
        // create our game HUD for scores/timers/level info
        hud = new Hud(game.batch);

        // start rendering the map
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map , 1/MarioBros.PPM );
        gamecam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);

        // If you maintain the Object in sleep mod you don't need to calculate collisions
        world = new World(new Vector2(0,-10),true);
        b2dr = new Box2DDebugRenderer();

        //Reorganize the code
        new B2WorldCreator(this);

        // Create Mario ; )
        player = new Mario(this);

        world.setContactListener(new WorldContactListener());

        music = MarioBros.manager.get("audio/music/mario_music.ogg",Music.class);
        music.setLooping(true);
        music.play();

        goombas = new Goombas(this,5.64f,.1f);


    }



    public void handleInput(float dt){
        // Add control here
        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.UP)){
            player.b2body.applyLinearImpulse(new Vector2(0,4f),player.b2body.getWorldCenter(),true);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2 ){
            player.b2body.applyLinearImpulse(new Vector2(1f,0),player.b2body.getWorldCenter(),true);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2 ){
            player.b2body.applyLinearImpulse(new Vector2(-1f,0),player.b2body.getWorldCenter(),true);
        }

    }

    public void update(float dt){
        handleInput(dt);

        // we need to define how many times we'll render the screen
        world.step(1/60f,6,2);

        // Now update the Mario // renderer
        player.update(dt);
        // we need to pass a update into our hud
        hud.update(dt);

        // Fix Camera in X axe, because we want camera jump like Mario
        gamecam.position.x = player.b2body.getPosition().x;

        gamecam.update();
        // this only will render what our camera can see
        renderer.setView(gamecam);

        // Goomba needs to be update
        goombas.update(dt);
    }


    // New method
    public TextureAtlas getAtlas(){
        return atlas;
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //separate our update logic from render
        update(delta);

        // first thing , clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // clean the screen before
        renderer.render();

        /// Renderer our Box2DDebugLines
        b2dr.render(world, gamecam.combined);


        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        // we need to render Mario
        player.draw(game.batch);
        // we need to render Goomba
        goombas.draw(game.batch);
        game.batch.end();

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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }

    public TiledMap getMap(){
        return map;
    }

    public World getWorld(){
        return world;
    }

}
