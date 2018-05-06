package com.vakuor.kingsandgoldmines.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
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
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        //camera.setToOrtho(false, 800, 480);//лишнее?

        stage = new Stage(new ScreenViewport(camera));

        objects = new MyWorldObjects();


//        if(world != null) System.out.println("WORLDISNOTNULL");
//        else System.out.println("null");
//        Gdx.app.exit();
//        objects.MyWorldObjects();
        //world = MyWorldObjects.world;

        //background.
        //background.//
        camera.position.set(0,0,0);//stage.getHeight()/2
        stage.setDebugAll(true);

        rend = new Box2DDebugRenderer();
//        createRect();
//        createWall();


    }

    @Override
    public void render(float delta) {
        System.out.println("MainGameScreen.render");
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //game.batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        //Actor actor

        //world.step(1/60f,4,4);
        camera.update();

        //stage.act(delta);
       // stage.draw();

        rend.render(objects.world,camera.combined);
        objects.world.step((float)1/ game.fps,4,4);
        stage.act(delta);
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
