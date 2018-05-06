package com.vakuor.kingsandgoldmines.utilities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameActor extends Actor {

    World world;
    Body body;
    Sprite sprite;

    public GameActor(World world){
        this.world = world;
    }
    public void createBody(Shape shape, BodyDef.BodyType bodyType, Vector2 spawnpoint){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(spawnpoint);
        bodyDef.type = bodyType;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.restitution = 1;
        fixtureDef.density = 5;

        body.createFixture(fixtureDef);
    }
}
