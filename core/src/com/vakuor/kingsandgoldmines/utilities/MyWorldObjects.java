package com.vakuor.kingsandgoldmines.utilities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.WorldManifold;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.vakuor.kingsandgoldmines.controls.MyContactListener;
import com.vakuor.kingsandgoldmines.controls.Player;

public class MyWorldObjects{

    World world;
    public static final float worldGravity = 10;
    public static final float width=1;
    public static final float height=1;
    public static Vector2 spawnpoint;


    public void MyWorldObjects(){
        world = new World(new Vector2(0,-worldGravity),true);

        spawnpoint = new Vector2(2,2);

        world.setContactListener(new MyContactListener(world));
        createWorld();
    }
    private void createWorld(){
        //создание игрока
        BodyDef def = new BodyDef();
        //установить тип тела
        def.type = BodyDef.BodyType.DynamicBody;
        //создать объект с определёнными заранее параметрами
        Body boxP = world.createBody(def);
        Player player = new Player(boxP);
        //переместить объект по указанным координатам
        //player.getBody().setTransform(1.0f, 4.0f, 0);
       // player.getBody().setFixedRotation(true);

        //создание платформы
        //platforms.add(new MovingPlatform(world, 3F, 3, 1,0.25F, 2, 0, 2));

        //создание блоков
        for(int i=0;i<width; ++i){
            Body boxGround = createBlock(BodyDef.BodyType.StaticBody, 0.5F, 0.5F, 2);
            boxGround.setTransform(i,0,0);
            boxGround.getFixtureList().get(0).setUserData("bd");
            boxGround = createBlock(BodyDef.BodyType.StaticBody, 0.5F, 0.5F, 0);
            boxGround.setTransform(i,height-1,0);
            boxGround.getFixtureList().get(0).setUserData("b");
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
