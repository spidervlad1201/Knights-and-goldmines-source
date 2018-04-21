package com.vakuor.kingsandgoldmines.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.vakuor.kingsandgoldmines.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Knights and swords";
		config.width = 800;
		config.height = 480;

		new LwjglApplication(new Main(), config);
	}
}
