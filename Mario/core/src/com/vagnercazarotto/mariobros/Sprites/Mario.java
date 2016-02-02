package com.vagnercazarotto.mariobros.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.vagnercazarotto.mariobros.MarioBros;
import com.vagnercazarotto.mariobros.Screens.PlayScreen;

/**
 * Created by vagner on 02/02/2016.
 */
public class Mario extends Sprite {
    public World world;
    public Body b2body;
    private TextureRegion marioStand;

    public Mario(World world,PlayScreen screen){
        // Do a call to master class and find the Little_Mario in the Atlas
        super(screen.getAtlas().findRegion("little_mario"));
        this.world = world;
        defineMario();
        // Find Mario in the Sprite for Render
        marioStand = new TextureRegion(getTexture(),421,267,16,16); // Declare a initial position for mario
        setBounds(0,0,16 / MarioBros.PPM ,16 / MarioBros.PPM); // defines a position on the screen
        setRegion(marioStand);
    }

    public void defineMario(){
        BodyDef bdef = new BodyDef();  // new body definition
        bdef.position.set(32/ MarioBros.PPM ,32 /MarioBros.PPM);  // temp values
        bdef.type = BodyDef.BodyType.DynamicBody;  // define type
        b2body = world.createBody(bdef);  // finaly create a body

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape(); // temp values
        shape.setRadius(6 / MarioBros.PPM); // temp values because we need a shape

        fdef.shape = shape;  // define a shape
        b2body.createFixture(fdef);
    }

    // New method to fix the texture in Mario
    public void update(float dt){
        // set the center of Mario
        setPosition(b2body.getPosition().x - getWidth()/2 , b2body.getPosition().y - getHeight() /2);
    }

}
