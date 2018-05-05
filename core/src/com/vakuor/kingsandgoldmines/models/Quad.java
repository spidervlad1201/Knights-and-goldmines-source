package com.vakuor.kingsandgoldmines.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Quad extends Actor {
    private Sprite sprite;
    public Quad(){
        sprite = new Sprite(new Texture(Gdx.files.internal("knight.png")));
        sprite.setBounds(getX(),getY(),getWidth(),getHeight());

        setBounds(0,0,10,10);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        sprite.setBounds(getX(),getY(),getWidth(),getHeight());
    }
}
