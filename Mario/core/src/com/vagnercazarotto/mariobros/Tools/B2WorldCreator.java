package com.vagnercazarotto.mariobros.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.vagnercazarotto.mariobros.Sprites.Brick;
import com.vagnercazarotto.mariobros.Sprites.Coin;
import com.vagnercazarotto.mariobros.Sprites.Goombas;
import com.vagnercazarotto.mariobros.Sprites.Ground;
import com.vagnercazarotto.mariobros.Sprites.Pipes;
import com.vagnercazarotto.mariobros.Sprites.Turtles;


/**
 * Created by vagner on 02/02/2016.
 */
public class B2WorldCreator {
    public B2WorldCreator(World world,TiledMap map){
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
            new Ground(world,map,rect);
        }


        /////////// Now for another Layer
        // create Pipes - Layer 3
        for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Pipes(world,map,rect);
        }



        // create Coins - Layer 4
        for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Coin(world,map,rect);
        }



        // create Bricks - Layer 5
        for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Brick(world,map,rect);
        }


        // create Goombas- Layer 6
        for(MapObject object: map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Goombas(world,map,rect);
        }


        // create Turtles- Layer 7
        for(MapObject object: map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            com.badlogic.gdx.math.Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new Turtles(world,map,rect);
        }
    }

}
