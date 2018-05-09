package com.vakuor.kingsandgoldmines.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.vakuor.kingsandgoldmines.Main;
import com.vakuor.kingsandgoldmines.utilities.MyWorldObjects;

public class MainGameScreen implements Screen {

    Main game;
    Stage stage;
    Box2DDebugRenderer rend;
    MyWorldObjects objects;

    private OrthographicCamera camera;

    public MainGameScreen (Main game){
        this.game = game;
    }

    @Override
    public void show() {
        System.out.println("MainGameScreen.show");
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        //camera.setToOrtho(false, 800, 480);//лишнее?
        stage = new Stage(new ScreenViewport(camera));

        objects = new MyWorldObjects();

        camera.position.set(0,0,0);//stage.getHeight()/2

        stage.setDebugAll(true);//debug lines
        rend = new Box2DDebugRenderer();
//        createRect();
//        createWall();


    }

    @Override
    public void render(float delta) {
        //System.out.println("MainGameScreen.render");
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

       // if(Gdx.input.isKeyPressed(Input.Keys.D))

        stage.act(delta);
        objects.world.step((float)1/ game.fps,4,4);
        rend.render(objects.world,camera.combined);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
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
        stage.dispose();
        //game.dispose(); // Проверить в Main dispose;
    }
}
