package com.vakuor.kingsandgoldmines.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.vakuor.kingsandgoldmines.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "kingsgamecfg";
		config.width = 640;
		config.height = 480;

		new LwjglApplication(new Main(), config);
	}
}
