package com.vakuor.kingsandgoldmines.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Logger;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.vakuor.kingsandgoldmines.Main;

import sun.rmi.runtime.Log;

public class MainGameScreen implements Screen {

    Main game;
    Stage stage;
    Texture background;

    private OrthographicCamera camera;

    public MainGameScreen (Main game){
        this.game = game;

    }

    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        stage = new Stage(new ScreenViewport(camera));
        camera.position.set(stage.getWidth()/2,stage.getHeight()/2,0);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Actor actor

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
