package com.vakuor.kingsandgoldmines.utilities.byMax;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LoadingBarActor extends Actor {

    Animation animation;
    TextureRegion reg;
    float stateTime;

    public LoadingBarActor(Animation animation){
        this.animation = animation;
        reg = (TextureRegion) animation.getKeyFrame(0);
        setHeight(reg.getRegionHeight());
        setWidth(reg.getRegionWidth());
    }

    @Override
    public void act(float delta) {
        stateTime +=delta;
        reg= (TextureRegion) animation.getKeyFrame(stateTime);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(reg,getX(),getY());
    }
}

