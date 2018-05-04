package com.vakuor.kingsandgoldmines.utilities;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

public class Animator implements ApplicationListener {

    private static final int FRAME_COLS = 6;
    private static final int FRAME_ROWS = 5;

    public Animation walkAnimation;
    public Texture walkSheet;
    public TextureRegion[] walkFrames;
    public SpriteBatch spriteBatch;
    public TextureRegion currentFrame;

    private Animation animation;
    private TextureAtlas textureAtlas;
    private Array<TextureAtlas.AtlasRegion> atlasRegions;
    private float stateTime;

    private boolean alive = false;

    @Override
    public void create() {
        walkSheet = new Texture(Gdx.files.internal("animation_sheet.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for(int i = 0; i < FRAME_ROWS; i++){
            for(int j = 0; j< FRAME_COLS; j++){
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(0.025f, walkFrames);
        spriteBatch = new SpriteBatch();
        stateTime = 0f;
    }


    public void create(TextureAtlas textureAtlas, float frameTime){
        this.textureAtlas = textureAtlas;
        textureAtlas.dispose();
        animation = new Animation(frameTime,textureAtlas.getRegions());
        stateTime = 0f;
    }

    public void create(String internalPackFile, float frameTime){
        textureAtlas = new TextureAtlas(internalPackFile);
        animation = new Animation(frameTime,textureAtlas.getRegions());
        stateTime = 0f;
    }

    private void iterat(){///////////////////////////////////////////////
        Iterator<TextureAtlas.AtlasRegion> reg = atlasRegions.iterator();
        while (reg.hasNext()) {
            TextureAtlas.AtlasRegion currentReg = reg.next();
            //raindrop.y -= 200 * Gdx.graphics.getDeltaTime();

            //reg.remove();
        }
        /*while (iter.hasNext()) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (raindrop.y + 64 < 0)
                iter.remove();
            if (raindrop.overlaps(bucket)) {
                dropsGathered++;
                dropSound.play();
                iter.remove();
            }
        }*/
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();

        currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true);

        spriteBatch.begin();
        spriteBatch.draw(currentFrame, 50, 50);
        spriteBatch.end();
    }

    public void render(boolean a) {
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();

        currentFrame = (TextureRegion) animation.getKeyFrame(stateTime, true);

        spriteBatch.begin();
        spriteBatch.draw(currentFrame, 50, 50);
        spriteBatch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    public boolean isAlive(){return alive;}
    public void setAlive(boolean alive){this.alive = alive;}

    @Override
    public void dispose() {

    }
}
