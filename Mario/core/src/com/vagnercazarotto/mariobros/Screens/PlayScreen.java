package com.vagnercazarotto.mariobros.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
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

    // Tiled map variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    // Box2d Variables
    private World world;
    private Box2DDebugRenderer b2dr;




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

        // If you maintain the Object in sleep mod you don't need to calculate collisions
        world = new World(new Vector2(0,0),true);
        b2dr = new Box2DDebugRenderer();

        // we need to add some bodies to game screen
        BodyDef bdef = new BodyDef(); // definitions about body
        PolygonShape shape = new PolygonShape(); // define shapes for collisions
        FixtureDef fdef = new FixtureDef();
        Body body;

        // we need to create a body for every object in the world
        // Ok for the logic here: get the layer and then select the field Ground (number 2),
        // and then get all the objects by type (object class)

        // Create Ground - Layer 2
        for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight()/ 2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }


        /////////// Now for another Layer
        // create Pipes - Layer 3
        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight()/ 2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }



        // create Coins - Layer 4
        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight()/ 2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }



        // create Bricks - Layer 5
        for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight()/ 2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }


        // create Goombas- Layer 6
        for(MapObject object: map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight()/ 2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }


        // create Turtles- Layer 7
        for(MapObject object: map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight()/ 2);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2,rect.getHeight()/2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }


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

        /// Renderer our Box2DDebugLines
        b2dr.render(world,gamecam.combined);


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
