package com.vagnercazarotto.mariobros.Sprites.Items;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.vagnercazarotto.mariobros.MarioBros;
import com.vagnercazarotto.mariobros.Screens.PlayScreen;
import com.vagnercazarotto.mariobros.Sprites.Mario;

/**
 * Created by vagner on 08/02/2016.
 */
public class Mushroom extends Item {

    public Mushroom(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        setRegion(screen.getAtlas().findRegion("mushroom"), 0, 0, 17, 17);
        velocity = new Vector2(0.7f,0);
    }

    @Override
    public void defineItem() {
        ///// Start with the same Definition as mario (copy paste)
        BodyDef bdef = new BodyDef();  // new body definition
        bdef.position.set(getX() ,getY());
        bdef.type = BodyDef.BodyType.DynamicBody;  // define type
        body = world.createBody(bdef);  // finaly create a body

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape(); // temp values
        shape.setRadius(6 / MarioBros.PPM);

        fdef.filter.categoryBits = MarioBros.ITEM_BIT;
        fdef.filter.maskBits = MarioBros.MARIO_BIT |
                MarioBros.OBJECT_BIT |
                MarioBros.GROUND_BIT |
                MarioBros.COIN_BIT |
                MarioBros.BRICK_BIT;

        fdef.shape = shape;  // define a shape
        body.createFixture(fdef).setUserData(this);
    }

    @Override
    public void use(Mario mario) {
        destroy();
        mario.grow();
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        velocity.y = body.getLinearVelocity().y;
        body.setLinearVelocity(velocity);
    }
}
