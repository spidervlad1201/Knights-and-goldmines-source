package com.vakuor.kingsandgoldmines.view;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.vakuor.kingsandgoldmines.Main;
import com.vakuor.kingsandgoldmines.utilities.Animator;

public class GameScreenForStudy implements Screen {
    final Main game;

    Texture dropImage;
    Texture bucketImage;
    Animator animator;
    TextureAtlas atlasArcher;
    /*TextureAtlas.AtlasRegion archerRegion;
    TextureAtlas.AtlasRegion archerRegion2;
    TextureAtlas.AtlasRegion archerRegion3;
    TextureAtlas.AtlasRegion lastRegion = archerRegion;
    int lastkeyframe=1;*/
    Sound dropSound;
    Music rainMusic;
    OrthographicCamera camera;
    Rectangle bucket;
    Array<Rectangle> raindrops;
    long lastDropTime;
    int dropsGathered;
    float deltaTime=0;

    public GameScreenForStudy(final Main gam) {
        this.game = gam;

        // загрузка изображений для капли и ведра, 64x64 пикселей каждый
        dropImage = new Texture(Gdx.files.internal("badlogic.jpg"));
        bucketImage = new Texture(Gdx.files.internal("knight.png"));
        /*atlasArcher = new TextureAtlas("output/Archer/Archer.atlas");
        archerRegion = atlasArcher.findRegion("archerBody1");
        archerRegion2 = atlasArcher.findRegion("archerBody2");
        archerRegion3 = atlasArcher.findRegion("archerBody3");*/
        //Sprite archerSprite = atlasArcher.createSprite("archerBody2");
        // загрузка звукового эффекта падающей капли и фоновой "музыки" дождя
        dropSound = Gdx.audio.newSound(Gdx.files.internal("swordBlow.wav"));

//        rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
//        rainMusic.setLooping(true);

        // создает камеру
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        // создается Rectangle для представления ведра
        bucket = new Rectangle();
        // центрируем ведро по горизонтали
        bucket.x = 0;//800 / 2 - 64 / 2
        // размещаем на 20 пикселей выше нижней границы экрана.
        bucket.y = 0;

        bucket.width = 64;
        bucket.height = 64;

        animator = new Animator();
        //animator.create(atlasArcher,0.2f);

        //lastRegion = archerRegion;

        // создает массив капель и возрождает первую
        raindrops = new Array<Rectangle>();
        spawnRaindrop();

    }

    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800 - 64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void render(float delta) {
        // очищаем экран темно-синим цветом.
        // Аргументы для glClearColor красный, зеленый
        // синий и альфа компонент в диапазоне [0,1]
        // цвета используемого для очистки экрана.
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // сообщает камере, что нужно обновить матрицы.
        camera.update();
        // сообщаем SpriteBatch о системе координат
        // визуализации указанных для камеры.
        game.batch.setProjectionMatrix(camera.combined);

        deltaTime+=delta;

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            bucket.x += 200 * Gdx.graphics.getDeltaTime();
            animator.render(true);
        /*
            if(deltaTime>0.1f) {
                switch (lastkeyframe) {
                    case 1: {
                        lastRegion = archerRegion2;
                        lastkeyframe = 2;
                        break;
                    }
                    case 2: {
                        lastRegion = archerRegion3;
                        lastkeyframe = 3;
                        break;
                    }
                    case 3: {
                        lastRegion = archerRegion;
                        lastkeyframe = 1;
                        break;
                    }
                }
                deltaTime = 0;
            }*/
        }

        if (Gdx.input.isKeyPressed(Keys.UP)) {
            camera.zoom-=Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            camera.zoom+=Gdx.graphics.getDeltaTime();
        }

        // начитаем новую серию, рисуем ведро и
        // все капли
        game.batch.begin();
        game.font.draw(game.batch, "Drops Collected: " + dropsGathered, 0, 480);
        //game.batch.draw(lastRegion, bucket.x, bucket.y,120,120);
        for (Rectangle raindrop : raindrops) {
            game.batch.draw(dropImage, raindrop.x, raindrop.y);
        }
        game.batch.end();

        // обработка пользовательского ввода
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.x = touchPos.x - 64 / 2;
        }


        // убедитесь, что ведро остается в пределах экрана
        if (bucket.x < 0)
            bucket.x = 0;
        if (bucket.x > 800 - 64)
            bucket.x = 800 - 64;

        // проверка, нужно ли создавать новую каплю
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
            spawnRaindrop();

        // движение капли, удаляем все капли выходящие за границы экрана
        // или те, что попали в ведро. Воспроизведение звукового эффекта
        // при попадании.
        Iterator<Rectangle> iter = raindrops.iterator();
        while (iter.hasNext()) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (raindrop.y + 64 < 0)
                iter.remove();
            if (raindrop.overlaps(bucket)) {
                dropsGathered++;
                dropSound.play();
                iter.remove();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // воспроизведение фоновой музыки
//        // когда отображается экрана
//        rainMusic.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        dropImage.dispose();
        bucketImage.dispose();
        dropSound.dispose();
        rainMusic.dispose();
    }

}