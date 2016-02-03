package com.vagnercazarotto.mariobros.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.vagnercazarotto.mariobros.MarioBros;

/**
 * Created by vagner on 02/02/2016.
 */
public abstract class InteractijectveTileObject{
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected com.badlogic.gdx.math.Rectangle bounds;
    protected Body body;
    protected Fixture fixture;


    public InteractijectveTileObject(World world,TiledMap map,com.badlogic.gdx.math.Rectangle bounds){
        this.world = world;
        this.map = map;
        this.bounds = bounds;


        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((float)((bounds.getX() + bounds.getWidth() / 2)/ MarioBros.PPM ),(float)((bounds.getY() + bounds.getHeight()/ 2) / MarioBros.PPM));

        body = world.createBody(bdef);

        shape.setAsBox((float)(bounds.getWidth()/2/ MarioBros.PPM),(float)(bounds.getHeight()/2/ MarioBros.PPM));
        fdef.shape = shape;
        // we capture all the point of collision, bricks, pipes , etc.
        fixture = body.createFixture(fdef);

    }

    public abstract void onHeadHit();
    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell(){
        // get the layer
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        // Ok, a little complex here,getCell needs integer values for X and Y, but we have changed the scales on mario so now we need to change
        // again to restore values. First multiple for mario PPM then divide for 16
        return layer.getCell((int)(body.getPosition().x * MarioBros.PPM /16),(int)(body.getPosition().y * MarioBros.PPM /16));
    }


}
