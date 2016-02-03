package com.vagnercazarotto.mariobros.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vagnercazarotto.mariobros.MarioBros;
import com.vagnercazarotto.mariobros.Screens.PlayScreen;
import com.vagnercazarotto.mariobros.Sprites.Brick;
import com.vagnercazarotto.mariobros.Sprites.Coin;
import com.vagnercazarotto.mariobros.Sprites.Goombas;


/**
 * Created by vagner on 02/02/2016.
 */
public class B2WorldCreator {
    public Array<Goombas> getGoombas() {
        return goombas;
    }

    // Create a Array for Enemies
    private Array<Goombas> goombas;

    public B2WorldCreator(PlayScreen screen){
        World world = screen.getWorld();
        TiledMap map = screen.getMap();

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



//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set((float)((rect.getX() + rect.getWidth() / 2)/ MarioBros.PPM ),(float)((rect.getY() + rect.getHeight()/ 2) / MarioBros.PPM));
//
//            body = world.createBody(bdef);
//
//            shape.setAsBox((float)(rect.getWidth()/2/ MarioBros.PPM),(float)(rect.getHeight()/2/ MarioBros.PPM));
//            fdef.shape = shape;
//            // here we define collision
//            fdef.filter.categoryBits = MarioBros.OBJECT_BIT;
//            // we capture all the point of collision, bricks, pipes , etc.
//            body.createFixture(fdef);



            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM, (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / MarioBros.PPM, rect.getHeight() / 2 / MarioBros.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);




            //new Ground(screen,rect);
        }


        /////////// Now for another Layer
        // create Pipes - Layer 3
        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();
//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set((float)((rect.getX() + rect.getWidth() / 2)/ MarioBros.PPM ),(float)((rect.getY() + rect.getHeight()/ 2) / MarioBros.PPM));
//
//            body = world.createBody(bdef);
//
//            shape.setAsBox((float)(rect.getWidth()/2/ MarioBros.PPM),(float)(rect.getHeight()/2/ MarioBros.PPM));
//            fdef.shape = shape;
//            // here we define collision
//            fdef.filter.categoryBits = MarioBros.OBJECT_BIT;
//            // we capture all the point of collision, bricks, pipes , etc.
//            body.createFixture(fdef);



            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / MarioBros.PPM, (rect.getY() + rect.getHeight() / 2) / MarioBros.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / MarioBros.PPM, rect.getHeight() / 2 / MarioBros.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = MarioBros.OBJECT_BIT;
            body.createFixture(fdef);



            //new Pipes(screen,rect);
        }



        // create Coins - Layer 4
        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();

//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set((float)((rect.getX() + rect.getWidth() / 2)/ MarioBros.PPM ),(float)((rect.getY() + rect.getHeight()/ 2) / MarioBros.PPM));
//
//            body = world.createBody(bdef);
//
//            shape.setAsBox((float)(rect.getWidth()/2/ MarioBros.PPM),(float)(rect.getHeight()/2/ MarioBros.PPM));
//            fdef.shape = shape;
//            // here we define collision
//            fdef.filter.categoryBits = MarioBros.OBJECT_BIT;
//            // we capture all the point of collision, bricks, pipes , etc.
//            body.createFixture(fdef);




           new Coin(screen,rect);
        }



        // create Bricks - Layer 5
        for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();
//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set((float)((rect.getX() + rect.getWidth() / 2)/ MarioBros.PPM ),(float)((rect.getY() + rect.getHeight()/ 2) / MarioBros.PPM));
//
//            body = world.createBody(bdef);
//
//            shape.setAsBox((float)(rect.getWidth()/2/ MarioBros.PPM),(float)(rect.getHeight()/2/ MarioBros.PPM));
//            fdef.shape = shape;
//            // here we define collision
//            fdef.filter.categoryBits = MarioBros.OBJECT_BIT;
//            // we capture all the point of collision, bricks, pipes , etc.
//            body.createFixture(fdef);


            new Brick(screen,rect);
        }


        // create Goombas- Layer 6
        goombas = new Array<Goombas>();
        for(MapObject object: map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();
            goombas.add(new Goombas(screen,rect.getX()/MarioBros.PPM,rect.getY()/MarioBros.PPM));
        }


        // create Turtles- Layer 7
        for(MapObject object: map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();

//
//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set((float)((rect.getX() + rect.getWidth() / 2)/ MarioBros.PPM ),(float)((rect.getY() + rect.getHeight()/ 2) / MarioBros.PPM));
//
//            body = world.createBody(bdef);
//
//            shape.setAsBox((float)(rect.getWidth()/2/ MarioBros.PPM),(float)(rect.getHeight()/2/ MarioBros.PPM));
//            fdef.shape = shape;
//            // here we define collision
//            fdef.filter.categoryBits = MarioBros.OBJECT_BIT;
//            // we capture all the point of collision, bricks, pipes , etc.
//            body.createFixture(fdef);


           // new Turtles(screen,rect);
        }
    }

}
