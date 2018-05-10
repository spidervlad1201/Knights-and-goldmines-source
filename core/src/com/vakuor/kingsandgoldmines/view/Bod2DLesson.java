package com.vakuor.kingsandgoldmines.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.vakuor.kingsandgoldmines.Main;
import com.vakuor.kingsandgoldmines.utilities.MyWorldObjects;

public class Bod2DLesson implements Screen {

    Box2DDebugRenderer rend;
    OrthographicCamera camera;
    Body rect;
    Main game;
    MyWorldObjects objects;

    public Bod2DLesson(Main game){
        this.game = game;
    }
    @Override
    public void show() {
        System.out.println("Bod2DLesson.show");
        objects = new MyWorldObjects(game);
        rend = new Box2DDebugRenderer();
        camera = new OrthographicCamera(20,15);
        //createRect();
        //createWall();
    }

    private void createRect(){
        System.out.println("Bod2DLesson.createRect");
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,0);

        rect = objects.world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(2,2);
        fixtureDef.shape = shape;
        fixtureDef.density = 2;

        rect.createFixture(fixtureDef);

    }
    private void createWall(){
        System.out.println("Bod2DLesson.createWall");
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0,0);

        Body w = objects.world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        ChainShape shape = new ChainShape();
        shape.createChain(new Vector2[] {new Vector2(-15,15),new Vector2(-15,-5), new Vector2(15,-5),new Vector2(15,15)});
        //fixtureDef.density = 2;//Всегда 0

        fixtureDef.shape = shape;

        w.createFixture(fixtureDef);

    }

    @Override
    public void render(float delta) {
        System.out.println("Bod2DLesson.render");
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        rend.render(objects.world,camera.combined);
        objects.world.step((float)1/game.fps,4,4);
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
