package com.vagnercazarotto.mariobros.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.vagnercazarotto.mariobros.MarioBros;
import com.vagnercazarotto.mariobros.Screens.PlayScreen;

/**
 * Created by vagner on 02/02/2016.
 */
public class Mario extends Sprite {
    public enum State{FALLING,JUMPING,STANDING,RUNNING};
    public State currentState;
    public State previousState;
    public World world;
    public Body b2body;
    private TextureRegion marioStand;
    private Animation marioRun;
    private Animation marioJump;
    private float stateTimer;
    private boolean runningRight;

    public Mario(World world,PlayScreen screen){
        // Do a call to master class and find the Little_Mario in the Atlas
        super(screen.getAtlas().findRegion("little_mario"));
        this.world = world;

        //// STATES
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        // Create a Array of texture Region
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (int i =0; i<3 ; i++)
            frames.add(new TextureRegion(getTexture(),(i *16 + 420),267,16,16));
        marioRun = new Animation(0.12f, frames);
        frames.clear();

        for (int i =0; i<1 ; i++)
            frames.add(new TextureRegion(getTexture(), (i*16 + 500) ,267,16,16));
        marioJump = new Animation(0.3f, frames);
        frames.clear();

        marioStand = new TextureRegion(getTexture(),421,267,16,16);

        defineMario();
        // Find Mario in the Sprite for Render
        marioStand = new TextureRegion(getTexture(),421,267,16,16); // Declare a initial position for mario
        setBounds(0,0,16 / MarioBros.PPM ,16 / MarioBros.PPM); // defines a position on the screen
        setRegion(marioStand);
    }

    public void defineMario(){
        BodyDef bdef = new BodyDef();  // new body definition
        bdef.position.set(32 / MarioBros.PPM ,32 /MarioBros.PPM);  // temp values
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
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();
        TextureRegion region;
        switch (currentState){
            case JUMPING:
                region = marioJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = marioRun.getKeyFrame(stateTimer,true);
                break;
            case FALLING:
            case STANDING:
            default:
                region = marioStand;
                break;
        }
        if ((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true,false);
            runningRight = false;
        } else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true,false);
            runningRight = true;
        }
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public State getState(){
        if (b2body.getLinearVelocity().y >0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING))
            return State.JUMPING;
        else if (b2body.getLinearVelocity().y >0)
            return State.FALLING;
        else if (b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANDING;
    }

}
