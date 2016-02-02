package com.vagnercazarotto.mariobros.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.vagnercazarotto.mariobros.MarioBros;

/**
 * Created by vagner on 02/02/2016.
 */
public class Mario extends Sprite {
    public World world;
    public Body b2body;

    public Mario(World world){
        this.world = world;
        defineMario();
    }

    public void defineMario(){
        BodyDef bdef = new BodyDef();  // new body definition
        bdef.position.set(32/ MarioBros.PPM ,32 /MarioBros.PPM);  // temp values
        bdef.type = BodyDef.BodyType.DynamicBody;  // define type
        b2body = world.createBody(bdef);  // finaly create a body

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape(); // temp values
        shape.setRadius(5 / MarioBros.PPM); // temp values because we need a shape

        fdef.shape = shape;  // define a shape
        b2body.createFixture(fdef);
    }

}
