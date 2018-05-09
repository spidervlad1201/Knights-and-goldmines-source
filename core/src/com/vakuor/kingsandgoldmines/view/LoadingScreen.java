package com.vakuor.kingsandgoldmines.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.vakuor.kingsandgoldmines.Main;
import com.vakuor.kingsandgoldmines.tools.GifDecoder;
import com.vakuor.kingsandgoldmines.utilities.byMax.LoadingBarActor;

public class LoadingScreen implements Screen {

    private Main game;

    private Stage stage;
    private Actor loadingBar;
    Animation<TextureRegion> animation;
    float elapsed;

    //private Viewport viewport;
    //private OrthographicCamera camera;


    //ScreenViewport viewport;

    public LoadingScreen(Main game) {
        System.out.println("LoadingScreen.constructor\n");
        this.game = game;

        //game.manager.load("",.class);
        //game.manager.finishLoading();
        stage = new Stage();
        //camera = new OrthographicCamera();
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP,Gdx.files.internal("visual/animations/trump.gif").read() );
        loadingBar = new LoadingBarActor(animation);
        loadingBar.setPosition(Menu.screenWidth/2-loadingBar.getWidth()/2,Menu.screenHeight/2-loadingBar.getHeight()/2);
        //viewport = new ScreenViewport(camera);
        //camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void show() {
        System.out.println("Menu.show\n");
        stage.addActor(loadingBar);
    }

    @Override
    public void render(float delta) {
        System.out.println("LoadingScreen.render\n");

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        camera.update();
//        game.batch.setProjectionMatrix(camera.combined);

        elapsed += delta;

        stage.act(delta);
        stage.draw();

        //game.batch.begin();
        //game.batch.draw(animation.getKeyFrame(elapsed),0,0);
        //game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        //width = 480*width/height;
        //height = 480;
        //stage.setViewport(width, height,Viewport viewport = new ScreenViewport());

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
//    long loadStart = TimeUtils.nanoTime();
//    public PreloaderCallback getPreloaderCallback () {
//        final Canvas canvas = Canvas.createIfSupported();
//        canvas.setWidth("" + (int)(config.width * 0.7f) + "px");
//        canvas.setHeight("70px");
//        getRootPanel().add(canvas);
//        final Context2d context = canvas.getContext2d();
//        context.setTextAlign(TextAlign.CENTER);
//        context.setTextBaseline(TextBaseline.MIDDLE);
//        context.setFont("18pt Calibri");
//
//        return new PreloaderCallback() {
//            @Override
//            public void done () {
//                context.fillRect(0, 0, 300, 40);
//            }
//
//            @Override
//            public void loaded (String file, int loaded, int total) {
//                System.out.println("loaded " + file + "," + loaded + "/" + total);
//                String color = Pixmap.make(30, 30, 30, 1);
//                context.setFillStyle(color);
//                context.setStrokeStyle(color);
//                context.fillRect(0, 0, 300, 70);
//                color = Pixmap.make(200, 200, 200, (((TimeUtils.nanoTime() - loadStart) % 1000000000) / 1000000000f));
//                context.setFillStyle(color);
//                context.setStrokeStyle(color);
//                context.fillRect(0, 0, 300 * (loaded / (float)total) * 0.97f, 70);
//
//                context.setFillStyle(Pixmap.make(50, 50, 50, 1));
//                context.fillText("loading", 300 / 2, 70 / 2);
//            }
//
//            @Override
//            public void error (String file) {
//                System.out.println("error: " + file);
//            }
//        };
//    }

