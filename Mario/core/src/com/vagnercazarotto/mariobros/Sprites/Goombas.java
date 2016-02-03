package com.vagnercazarotto.mariobros.Sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.vagnercazarotto.mariobros.MarioBros;
import com.vagnercazarotto.mariobros.Screens.PlayScreen;


/**
 * Created by vagner on 02/02/2016.
 */
public class Goombas extends Enemy {

    // Define animation
    private float stateTime;
    private Animation walkAnimation;
    private Array<TextureRegion> frames;

    public Goombas(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        // Define texture for goombas
        frames = new Array<TextureRegion>();
        //screen.getAtlas().findRegion("little_mario");
        for(int i=0;i<2;i++)
            frames.add(new TextureRegion(screen.getAtlas().findRegion("goomba"), i * 16, 0, 16, 16));
           //frames.add(new TextureRegion(screen.getAtlas().findRegion("little_mario"),(i *16 + 420),267,16,16));
        // Add our frames to Animation
        walkAnimation = new Animation(0.4f,frames);
        stateTime = 0;
        setBounds(getX(), getY(), 16 / MarioBros.PPM, 16 / MarioBros.PPM);
    }

    public void update(float dt){
        stateTime += dt;
        setPosition(b2body.getPosition().x - getWidth() /2,b2body.getPosition().y - getHeight()/2);
        setRegion(walkAnimation.getKeyFrame(stateTime, true)); // yes it's a loop animation
    }


    @Override
    protected void defineEnemy() {
        ///// Start with the same Definition as mario (copy paste)
        BodyDef bdef = new BodyDef();  // new body definition
        bdef.position.set(32 / MarioBros.PPM ,32 /MarioBros.PPM);  // temp values
        bdef.type = BodyDef.BodyType.DynamicBody;  // define type
        b2body = world.createBody(bdef);  // finaly create a body

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape(); // temp values
        shape.setRadius(6 / MarioBros.PPM); // temp values because we need a shape
        // create a fixture that have a category bit as a Mario
        fdef.filter.categoryBits = MarioBros.ENEMY_BIT;
        // enemy should clashes with other enemy
        fdef.filter.maskBits = MarioBros.GROUND_BIT |
                MarioBros.COIN_BIT |
                MarioBros.BRICK_BIT |
                MarioBros.ENEMY_BIT |
                MarioBros.MARIO_BIT |
                MarioBros.OBJECT_BIT;

        fdef.shape = shape;  // define a shape
        b2body.createFixture(fdef);




    }

}
