package com.vakuor.kingsandgoldmines.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vakuor.kingsandgoldmines.Main;
import com.vakuor.kingsandgoldmines.utilities.Animator;

public class Menu implements Screen {

    private final Main game;

    Music menuMusic;
    int screenHeight = Gdx.graphics.getHeight();
    int screenWidth = Gdx.graphics.getWidth();
    //float aspectRatio = 1;
    Animator animator;
    Texture wallpaper;

    Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;

    public Menu(final Main game){
        this.game = game;
        camera = new OrthographicCamera();
        //viewport = new ScreenViewport(camera);

        animator = new Animator();
        animator.create();
        wallpaper = new Texture(Gdx.files.internal("wallpaper.jpg"));
        camera.setToOrtho(false, 800, 480);

        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("mainmenutheme.wav"));
        menuMusic.setLooping(true);

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport(camera));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);


        menuMusic.play();
        animator.render();
        game.batch.begin();

        game.batch.draw(wallpaper,0,0,screenWidth,screenHeight);
        game.font.draw(game.batch, "Welcome to Knights and Goldmines", 100, 150);
        game.font.draw(game.batch, "Tap anywhere to begin", 100, 100);

        game.batch.end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            game.setScreen(new GameScreenForStudy(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        //aspectRatio = (float) width/height;
        screenHeight = height;
        screenWidth = width;
        //viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        //game.dispose();

        menuMusic.dispose();
        animator.dispose();
    }

}
