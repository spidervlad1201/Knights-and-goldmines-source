package com.vakuor.kingsandgoldmines.utilities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class MyWorldObjects{

    World world;
    public static final float worldGravity = 10;


    public void MyWorldObjects(){
        world = new World(new Vector2(0,-worldGravity),true);
    }
}
