package com.vakuor.kingsandgoldmines.models;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.vakuor.kingsandgoldmines.utilities.GameActor;
import com.vakuor.kingsandgoldmines.utilities.MyWorldObjects;

import static com.vakuor.kingsandgoldmines.utilities.MyWorldObjects.spawnpoint;

public class PlayerActor extends GameActor {

    public PlayerActor(World world) {
        super(world);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(2,2);
        createBody(shape, BodyDef.BodyType.DynamicBody, spawnpoint);
        setPosition(spawnpoint.x,spawnpoint.y);
        coords = new Vector2(spawnpoint);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(coords.x,coords.y);
    }
}
