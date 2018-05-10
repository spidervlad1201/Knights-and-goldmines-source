package com.vakuor.kingsandgoldmines.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.vakuor.kingsandgoldmines.Main;
import com.vakuor.kingsandgoldmines.controls.Player;
import com.vakuor.kingsandgoldmines.view.Menu;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class MyWorldObjects {

    Main game;

    public static World world;
    public static final float worldGravity = 10;
    public static final float width=1;
    public static final float height=1;
    public static Vector2 spawnpoint;
    public Texture background;
    private Vector2 worldSize;
    private Vector2 blockSize;

    public TiledMap map;
    public MapLayer layer;
    public MapObjects mapObjects;
    TiledMapTileSet tileSet;

    public MyWorldObjects(Main game){

        this.game = game;
        world = new World(new Vector2(0,-worldGravity),true);

        background = new Texture("visual/images/wallpaper.jpg");//Land = mapName;
        spawnpoint = new Vector2(0,2);
        worldSize = new Vector2(64,32);
        blockSize  = new Vector2(8,8);

        //layer = map.getLayers().get(0);

        map = new TmxMapLoader().load("logical/maps/Map/map.tmx");
        //tileSet = map.getTileSets().getTileSet(0);
        if(map!=null) System.out.println("notnullmap");
        System.out.println(map.getLayers().getCount());
        //if(tileSet!=null)System.out.println("notnull");
//        System.out.println(tileSet.getTile(0).
//                getObjects().getCount());
        System.out.println(map.getLayers().get(0).getObjects().getCount());
        System.out.println(map.getLayers().get(1).getObjects().getCount());
        //Gdx.app.exit();
        System.out.println("asd");
        //map.getTileSets().addTileSet();
        //game.manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        //game.manager.load("logical/maps/Land/Land.tmx",TiledMap.class);
        //game.manager.finishLoading();
        //isLoaded();
        //System.out.println("Next");
        //map = game.manager.get("logical/maps/Land/Land.tmx");
        layer = map.getLayers().get(0);
        //Iterator<String> iter = map.getTileSets().getTileSet(0).getProperties().getKeys();
        //while (iter.hasNext()) { System.out.println(iter.next()); iter.remove(); }
        //map.getTileSets().getTileSet(0).
        mapObjects = layer.getObjects();
        //layer.getObjects().get(0).getProperties().put();
        /*for(int i=0;i<25;i++)
        System.out.println(map.getTileSets().getTile(i).getId());
        System.out.println(map.getTileSets().getTileSet(0).getTile(0).
                getObjects().getCount());*/
        //System.out.println(mapObjects.getCount());
        //world.setContactListener(new MyContactListener(world));
        createWorld();
        /* iter.Keys
imageheight
spacing
imagewidth
firstgid
tileheight
tilewidth
margin
imagesource*/
    }
    private void isLoaded() {

        try{game.manager.finishLoading();}
        catch (GdxRuntimeException ex){isLoaded();}
        finally {
            isLoaded();
        }

    }
    private void createWall(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0,0);

        Body w = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        ChainShape shape = new ChainShape();
        shape.createChain(new Vector2[] {new Vector2(0,worldSize.y),new Vector2(0,0), new Vector2(worldSize.x,0),worldSize});
        //fixtureDef.density = 2;//Всегда 0

        fixtureDef.shape = shape;

        w.createFixture(fixtureDef);

    }
    private void createWorld(){
        //createWall();

        //создание игрока
        ////BodyDef def = new BodyDef();
        //установить тип тела
        ////def.type = BodyDef.BodyType.DynamicBody;
        //создать объект с определёнными заранее параметрами
        ////Body boxP = world.createBody(def);
        ////Player player = new Player(boxP);
        //переместить объект по указанным координатам
        //player.getBody().setTransform(1.0f, 4.0f, 0);
       // player.getBody().setFixedRotation(true);

        //создание платформы
        //platforms.add(new MovingPlatform(world, 3F, 3, 1,0.25F, 2, 0, 2));

        //создание блоков
        for(int i=1;i<=worldSize.x; i++){
            for(int j=1;j<=worldSize.y;j++) {
                Body boxGround = createBlock(BodyDef.BodyType.StaticBody, blockSize.x, blockSize.y, 0);
                boxGround.setTransform(blockSize.x*(i*2-1), blockSize.y*(j*2-1), 0);
                boxGround.getFixtureList().get(0).setUserData("bd");
            }
        }
    }

    /**
     * Создание блока.
     * @param type тип
     * @param width ширина
     * @param height высота
     * @param density плотность
     * @return
     */
    private Body createBlock(BodyDef.BodyType type, float width, float height, float density) {
        BodyDef def = new BodyDef();
        def.type = type;
        Body box = world.createBody(def);
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width, height);
        box.createFixture(poly, density);
        poly.dispose();

        return box;
    }
    /**
     * Опреелить, нахоится ли игрок на земле/платформе.
     * @param deltaTime
     * время, прошешее с прошлой прорисовки.
     * @return
     */
    /*private boolean isPlayerGrounded(float deltaTime) {
        world.groundedPlatform = null;
        List<Contact> contactList = world.getWorld().getContactList();
        for(int i = 0; i < contactList.size(); i++) {
            Contact contact = contactList.get(i);
            if(contact.isTouching() && (contact.getFixtureA() == world.getPlayer().playerSensorFixture ||
                    contact.getFixtureB() == world.getPlayer().playerSensorFixture)) {

                Vector2 pos = world.getPlayer().getPosition();
                WorldManifold manifold = contact.getWorldManifold();
                boolean below = true;
                for(int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
                    below &= (manifold.getPoints()[j].y < pos.y - 0.4f);
                }

                if(below) {
                    if(contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData(). equals("p")) {
                        world.groundedPlatform =  (MovingPlatform)contact.getFixtureA(). getBody().getUserData();
                        if (!keys.get(Input.Keys.LEFT) && !keys.get(Input.Keys.RIGHT))
                            contact.setFriction(200F);
                        else
                            contact.setFriction(0F);
                    }

                    if(contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData(). equals("p")) {
                        world.groundedPlatform = (MovingPlatform)contact.getFixtureB(). getBody().getUserData();
                        if (!keys.get(Input.Keys.LEFT) && !keys.get(Input.Keys.RIGHT))
                            contact.setFriction(200F);
                        else
                            contact.setFriction(0F);
                    }
                    return true;
                }

                return false;
            }
        }
        return false;
    }*/
}
