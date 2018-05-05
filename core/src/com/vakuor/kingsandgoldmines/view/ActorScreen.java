package com.vakuor.kingsandgoldmines.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vakuor.kingsandgoldmines.Main;
import com.vakuor.kingsandgoldmines.models.Quad;
import com.vakuor.kingsandgoldmines.utilities.Animator;

import java.awt.Image;

public class ActorScreen implements Screen {


    private final Main game;


    Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;

    public ActorScreen(Main game){
        this.game = game;
    }
    @Override
    public void show() {
        camera = new OrthographicCamera();
        stage = new Stage(new ScreenViewport(camera));
        camera.position.set(new Vector3(0,0,0));

        stage.addActor(new Quad());
        Texture texture = new Texture("badlogic.jpg");
        com.badlogic.gdx.scenes.scene2d.ui.Image img = new com.badlogic.gdx.scenes.scene2d.ui.Image(texture);
        img.setBounds(0,0,img.getWidth(),img.getHeight());
        stage.addActor(img);
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

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

    }
}
