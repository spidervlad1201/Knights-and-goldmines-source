package com.vakuor.kingsandgoldmines;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.vakuor.kingsandgoldmines.view.ActorScreen;
import com.vakuor.kingsandgoldmines.view.GameScreenForStudy;
import com.vakuor.kingsandgoldmines.view.MainGameScreen;
import com.vakuor.kingsandgoldmines.view.Menu;

import java.util.logging.Logger;


public class Main extends Game {
	public SpriteBatch batch;
	public BitmapFont font;


//    OrthographicCamera camera;
//	Vector3 touchPos = new Vector3();//переделать

	long javaHeap;//лишнее
	long nativeHeap;//лишнее

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
        this.setScreen(new MainGameScreen(this));
//		camera = new OrthographicCamera();
//		camera.setToOrtho(false, 800, 480);

	}

	@Override
	public void render () {
//		Gdx.gl.glClearColor(0, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) Gdx.app.exit();

		super.render();

        javaHeap = Gdx.app.getJavaHeap();//потребление памяти Java//лишнее
        nativeHeap = Gdx.app.getNativeHeap();//нативной heap памяти//лишнее

//		if(Gdx.input.isTouched()) {//плохо - создание экземпляра класса
//			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//			camera.unproject(touchPos);
//			player.x = touchPos.x - player.width/2;
//		}
//
//		//упарвление с клавы
//		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) player.x -= 200 * Gdx.graphics.getDeltaTime();
//		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) player.x +=200*Gdx.graphics.getDeltaTime();
//
//
//		camera.update();
//		batch.setProjectionMatrix(camera.combined);
//		batch.begin();
//
//		batch.draw(img, 0, 0);
//		batch.draw(playerTexture, player.x, player.y);
//
//		batch.end();
	}


	@Override
	public void dispose () {
		font.dispose();
		batch.dispose();
	}
}
