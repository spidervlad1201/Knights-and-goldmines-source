package com.vakuor.kingsandgoldmines.utilities.byMax;

import com.badlogic.gdx.Screen;
import com.vakuor.kingsandgoldmines.Main;

public abstract class AbstractScreenByMax implements Screen {

    protected Main game;

    AbstractScreenByMax(Main game){
        this.game = game;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
