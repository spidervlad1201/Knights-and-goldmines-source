package com.vakuor.kingsandgoldmines;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.vakuor.kingsandgoldmines.view.Menu;

public class Main extends Game {
	SpriteBatch batch;
	Texture img;
	Texture playerTexture;
	Sound swordBlow;
    OrthographicCamera camera;
    Rectangle player;

	long javaHeap;//лишнее
	long nativeHeap;//лишнее

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		playerTexture = new Texture("knight.png");
		player = new Rectangle();
		//swordBlow = new Sound

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

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(playerTexture, player.x, player.y);



        javaHeap = Gdx.app.getJavaHeap();//потребление памяти Java//лишнее
        nativeHeap = Gdx.app.getNativeHeap();//нативной heap памяти//лишнее

		camera.update();


		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
