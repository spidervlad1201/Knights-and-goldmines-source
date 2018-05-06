package com.vakuor.kingsandgoldmines.models;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.vakuor.kingsandgoldmines.utilities.GameActor;

public class Arrow extends GameActor {

    public Arrow(World world) {
        super(world);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(2,1);
        createBody(shape, BodyDef.BodyType.DynamicBody,PlayerActor.);
    }
}
