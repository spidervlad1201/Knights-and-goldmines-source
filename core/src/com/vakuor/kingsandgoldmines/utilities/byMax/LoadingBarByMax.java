package com.vakuor.kingsandgoldmines.utilities.byMax;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LoadingBarByMax extends Actor {

    Animation animation;
    TextureRegion reg;
    float stateTime;

    public LoadingBarByMax(Animation animation) {
        this.animation = animation;
        reg = (TextureRegion) animation.getKeyFrame(0);
    }

    @Override
    public void act(float delta) {
        stateTime += delta;
        reg = (TextureRegion) animation.getKeyFrame(stateTime);
    }

    public void draw(SpriteBatch batch, float parentAlpha) {
        batch.draw(reg, getX(), getY());
    }
}
