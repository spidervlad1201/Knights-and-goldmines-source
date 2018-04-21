package com.vakuor.kingsandgoldmines;

import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;
import com.vakuor.kingsandgoldmines.Main;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		config.useCompass = false; // компас и аксель рекомендуется вырубать
		config.useAccelerometer = false;
		config.hideStatusBar = true;
		config.useRotationVectorSensor = true; // разобраться с ориентацией экрана

//		config.width = Gdx.graphics.getWidth();
//		config.height = Gdx.graphics.getHeight();
		initialize(new Main(), config);
	}
}
