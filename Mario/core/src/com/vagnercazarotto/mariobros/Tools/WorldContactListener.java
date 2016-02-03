package com.vagnercazarotto.mariobros.Tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.vagnercazarotto.mariobros.Sprites.Enemy;
import com.vagnercazarotto.mariobros.Sprites.InteractijectveTileObject;
import com.vagnercazarotto.mariobros.MarioBros;

/**
 * Created by vagner on 03/02/2016.
 */
public class WorldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {

        // we need to verify if it's a collision in the head
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        //Gdx.app.log("Collision", MarioBros.bitToText(fixA.getFilterData().categoryBits) + " collides with " + MarioBros.bitToText(fixB.getFilterData().categoryBits));
        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        if (fixA.getUserData() == "head" || fixB.getUserData() == "head"){
            Fixture head = fixA.getUserData() == "head" ? fixA : fixB;
            Fixture object = head == fixA ? fixB : fixA;

            if (object.getUserData() != null && InteractijectveTileObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractijectveTileObject) object.getUserData()).onHeadHit();
            }
        }

        switch (cDef){
            case MarioBros.ENEMY_HEAD_BIT | MarioBros.MARIO_BIT:
                if (fixA.getFilterData().categoryBits == MarioBros.ENEMY_HEAD_BIT)
                    ((Enemy) fixA.getUserData()).hitOnHead();
                else if (fixB.getFilterData().categoryBits == MarioBros.ENEMY_HEAD_BIT)
                    ((Enemy) fixB.getUserData()).hitOnHead();
        }

    }


    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

}
