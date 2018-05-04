package com.vakuor.kingsandgoldmines;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.vakuor.kingsandgoldmines.view.Menu;

import java.util.Iterator;

public class MainCopy extends Game {
    SpriteBatch batch;
    Texture img;
    Texture playerTexture;
    Texture raindropTexture;
    Sound swordBlow;
    OrthographicCamera camera;
    Rectangle player;
    Array<Rectangle> raindrops;
    long lastDropTime;

    Vector3 touchPos = new Vector3();//переделать

    long javaHeap;//лишнее
    long nativeHeap;//лишнее

    @Override
    public void create () {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        playerTexture = new Texture("knight.png");
        player = new Rectangle();
        //swordBlow = new Sound
        raindropTexture = new Texture("badlogic.jpg");

        raindrops = new Array<Rectangle>();
        spawnRaindrop();

        swordBlow = Gdx.audio.newSound(Gdx.files.internal("swordBlow.wav"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        float asd = player.width;

        player.width = playerTexture.getWidth();
        player.height = playerTexture.getHeight();
        player.x = (Gdx.graphics.getWidth()/2)-(player.width/2);//check IT !!!!!!!!!!!!!!!!
        player.y = Gdx.graphics.getHeight()/2;


    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();
        Iterator<Rectangle> iter = raindrops.iterator();
        while(iter.hasNext()) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if(raindrop.y + 64 < 0) iter.remove();
            if(raindrop.overlaps(player)) {
                swordBlow.play();
                iter.remove();
            }
        }

        if(Gdx.input.isTouched()) {//плохо - создание экземпляра класса
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            player.x = touchPos.x - player.width/2;
        }

        //упарвление с клавы
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.x +=200*Gdx.graphics.getDeltaTime();

        if(player.x < 0) player.x = 0;
        if(player.x > 800 - 64) player.x = 800 - 64; // переделать

        javaHeap = Gdx.app.getJavaHeap();//потребление памяти Java//лишнее
        nativeHeap = Gdx.app.getNativeHeap();//нативной heap памяти//лишнее

        camera.update();



        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(img, 0, 0);
        batch.draw(playerTexture, player.x, player.y);
        for(Rectangle raindrop: raindrops) {
            batch.draw(raindropTexture, raindrop.x, raindrop.y);
        }


        batch.end();
    }


    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800-64);
        raindrop.y = Gdx.graphics.getHeight();
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }


    @Override
    public void dispose () {

        playerTexture.dispose();
        raindropTexture.dispose();
        swordBlow.dispose();
        img.dispose();
        batch.dispose();
    }
}
