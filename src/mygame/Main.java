package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.app.state.VideoRecorderAppState;
import com.jme3.audio.AudioData.DataType;
import com.jme3.audio.AudioNode;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.font.BitmapText;
import com.jme3.input.ChaseCamera;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.system.AppSettings;
import com.jme3.ui.Picture;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    float speed_move = 1;
    float speed_rotate = 1;
    boolean go = false;
    Geometry geom;
    float move_x = 0;
    boolean rotater = false;
    float move_y;
    float move_z;
    private AudioNode audio_nature;

    float rotate_x;
    float rotate_y = 0;
    float rotate_z;
    Picture move_x1;
    Picture move_y1;
    Picture move_z1;
    Picture rotate_x1;
    Picture rotate_y1;
    Picture rotate_z1;
    Material mat;
    Picture move_x1_up;
    Picture red;
    Picture blue;
    Picture green;
    Picture yellow;
    Picture red1;
    Picture blue1;
    Picture green1;
    Picture yellow1;
    Picture move_x1_down;
    Picture move_y1_up;
    Picture move_y1_down;
    Picture move_z1_up;
    Picture move_z1_down;
    Picture rotate_x1_up;
    Picture rotate_x1_down;
    Picture rotate_y1_up;
    Picture rotate_y1_down;
    Picture rotate_z1_up;
    Picture rotate_z1_down;
    Picture move_xr;
    Picture move_yr;
    Picture move_zr;
    Picture rotate_xr;
    Picture rotate_yr;
    Picture rotate_zr;
    Picture randomcolor;
    Picture randomcolor1;
    Picture spawn;
    Picture trailt;
    Picture zeroloc;
    private AudioNode audio_gun;

    Picture zerorot;
    Picture rotate_r;
    Picture move_r;
    BitmapText move_xt1;
    BitmapText move_yt1;
    BitmapText move_zt1;
    BitmapText rotate_xt1;
    BitmapText rotate_yt1;
    BitmapText rotate_zt1;
    protected Spatial player;
    private Node shootables;
    Vector2f click2d;
    Vector3f click3d;
    Vector3f dir;
    String hit;
    boolean lit = false;
    ParticleEmitter fire;

    public static void main(String[] args) throws IOException {
        AppSettings settings = new AppSettings(true);
        settings.setResolution(1366, 768);
        // ... other properties, see below
        settings.setTitle("XTREME Cube v1.1");
        settings.setResizable(true);
        settings.setSettingsDialogImage("Textures/xtreme_cubev11.png");
        settings.setFullscreen(true);
        Main app = new Main();
        app.setSettings(settings);

        app.start();
    }

    @Override
    public void simpleInitApp() {
        Box b = new Box(1, 1, 1);
        geom = new Geometry("Box", b);

        mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);
        flyCam.setEnabled(false);
        ChaseCamera chaseCam = new ChaseCamera(cam, geom, inputManager);
        chaseCam.setMinDistance(6);
        chaseCam.setDownRotateOnCloseViewOnly(false);
        chaseCam.setSmoothMotion(true);
        Node track = (Node) assetManager.loadModel("Models/track.j3o");
        track.setLocalScale(3);
        track.setLocalTranslation(0, -3, 0);

        rootNode.attachChild(track);

        ScreenshotAppState screenShotState = new ScreenshotAppState();
        this.stateManager.attach(screenShotState);

        stateManager.attach(new VideoRecorderAppState()); //start recording

        move_x1 = new Picture("move_x");
        move_x1.setImage(assetManager, "Textures/plain.png", true);
        move_x1.setWidth(200);
        move_x1.setHeight(50);
        move_x1.setPosition(settings.getWidth() / 1.2f, settings.getHeight() / 1.22f);
        guiNode.attachChild(move_x1);
        move_y1 = new Picture("move_y");
        move_y1.setImage(assetManager, "Textures/plain.png", true);
        move_y1.setWidth(200);
        move_y1.setHeight(50);
        move_y1.setPosition(settings.getWidth() / 1.2f, settings.getHeight() / 1.35f);
        guiNode.attachChild(move_y1);
        move_z1 = new Picture("move_z");
        move_z1.setImage(assetManager, "Textures/plain.png", true);
        move_z1.setWidth(200);
        move_z1.setHeight(50);
        move_z1.setPosition(settings.getWidth() / 1.2f, settings.getHeight() / 1.5f);
        guiNode.attachChild(move_z1);

        rotate_x1 = new Picture("rotate_x");
        rotate_x1.setImage(assetManager, "Textures/plain.png", true);
        rotate_x1.setWidth(200);
        rotate_x1.setHeight(50);
        rotate_x1.setPosition(settings.getWidth() / 1.2f, settings.getHeight() / 2.22f);
        guiNode.attachChild(rotate_x1);
        rotate_y1 = new Picture("rotate_y");
        rotate_y1.setImage(assetManager, "Textures/plain.png", true);
        rotate_y1.setWidth(200);
        rotate_y1.setHeight(50);
        rotate_y1.setPosition(settings.getWidth() / 1.2f, settings.getHeight() / 2.65f);
        guiNode.attachChild(rotate_y1);
        rotate_z1 = new Picture("rotate_z");
        rotate_z1.setImage(assetManager, "Textures/plain.png", true);
        rotate_z1.setWidth(200);
        rotate_z1.setHeight(50);
        rotate_z1.setPosition(settings.getWidth() / 1.2f, settings.getHeight() / 3.3f);
        guiNode.attachChild(rotate_z1);

        zeroloc = new Picture("zeroloc");
        zeroloc.setImage(assetManager, "Textures/button_location.png", true);
        zeroloc.setWidth(200);
        zeroloc.setHeight(60);
        zeroloc.setPosition(settings.getWidth() / 1.2f, settings.getHeight() / 6f);
        guiNode.attachChild(zeroloc);
        initAudio();
        zerorot = new Picture("zerorot");
        zerorot.setImage(assetManager, "Textures/button_rotation.png", true);
        zerorot.setWidth(200);
        zerorot.setHeight(60);
        zerorot.setPosition(settings.getWidth() / 1.2f, settings.getHeight() / 16f);
        guiNode.attachChild(zerorot);

        move_x1_up = new Picture("move_x_up");
        move_x1_up.setImage(assetManager, "Textures/up.png", true);
        move_x1_up.setWidth(20);
        move_x1_up.setHeight(20);
        move_x1_up.setPosition(settings.getWidth() / 1.0175f, settings.getHeight() / 1.2f);
        guiNode.attachChild(move_x1_up);

        move_x1_down = new Picture("move_x_down");
        move_x1_down.setImage(assetManager, "Textures/down.png", true);
        move_x1_down.setWidth(20);
        move_x1_down.setHeight(20);
        move_x1_down.setPosition(settings.getWidth() / 1.23f, settings.getHeight() / 1.2f);
        guiNode.attachChild(move_x1_down);

        move_y1_up = new Picture("move_y_up");
        move_y1_up.setImage(assetManager, "Textures/up.png", true);
        move_y1_up.setWidth(20);
        move_y1_up.setHeight(20);
        move_y1_up.setPosition(settings.getWidth() / 1.0175f, settings.getHeight() / 1.32f);
        guiNode.attachChild(move_y1_up);

        move_y1_down = new Picture("move_y_down");
        move_y1_down.setImage(assetManager, "Textures/down.png", true);
        move_y1_down.setWidth(20);
        move_y1_down.setHeight(20);
        move_y1_down.setPosition(settings.getWidth() / 1.23f, settings.getHeight() / 1.32f);
        guiNode.attachChild(move_y1_down);

        move_z1_up = new Picture("move_z_up");
        move_z1_up.setImage(assetManager, "Textures/up.png", true);
        move_z1_up.setWidth(20);
        move_z1_up.setHeight(20);
        move_z1_up.setPosition(settings.getWidth() / 1.0175f, settings.getHeight() / 1.48f);
        guiNode.attachChild(move_z1_up);

        move_z1_down = new Picture("move_z_down");
        move_z1_down.setImage(assetManager, "Textures/down.png", true);
        move_z1_down.setWidth(20);
        move_z1_down.setHeight(20);
        move_z1_down.setPosition(settings.getWidth() / 1.23f, settings.getHeight() / 1.48f);
        guiNode.attachChild(move_z1_down);

        rotate_x1_up = new Picture("rotate_x_up");
        rotate_x1_up.setImage(assetManager, "Textures/up.png", true);
        rotate_x1_up.setWidth(20);
        rotate_x1_up.setHeight(20);
        rotate_x1_up.setPosition(settings.getWidth() / 1.0175f, settings.getHeight() / 2.2f);
        guiNode.attachChild(rotate_x1_up);

        rotate_x1_down = new Picture("rotate_x_down");
        rotate_x1_down.setImage(assetManager, "Textures/down.png", true);
        rotate_x1_down.setWidth(20);
        rotate_x1_down.setHeight(20);
        rotate_x1_down.setPosition(settings.getWidth() / 1.23f, settings.getHeight() / 2.63f);
        guiNode.attachChild(rotate_x1_down);

        rotate_y1_up = new Picture("rotate_y_up");
        rotate_y1_up.setImage(assetManager, "Textures/up.png", true);
        rotate_y1_up.setWidth(20);
        rotate_y1_up.setHeight(20);
        rotate_y1_up.setPosition(settings.getWidth() / 1.0175f, settings.getHeight() / 2.63f);
        guiNode.attachChild(rotate_y1_up);

        rotate_y1_down = new Picture("rotate_y_down");
        rotate_y1_down.setImage(assetManager, "Textures/down.png", true);
        rotate_y1_down.setWidth(20);
        rotate_y1_down.setHeight(20);
        rotate_y1_down.setPosition(settings.getWidth() / 1.23f, settings.getHeight() / 2.2f);
        guiNode.attachChild(rotate_y1_down);

        rotate_z1_up = new Picture("rotate_z_up");
        rotate_z1_up.setImage(assetManager, "Textures/up.png", true);
        rotate_z1_up.setWidth(20);
        rotate_z1_up.setHeight(20);
        rotate_z1_up.setPosition(settings.getWidth() / 1.0175f, settings.getHeight() / 3.28f);
        guiNode.attachChild(rotate_z1_up);

        rotate_z1_down = new Picture("rotate_z_down");
        rotate_z1_down.setImage(assetManager, "Textures/down.png", true);
        rotate_z1_down.setWidth(20);
        rotate_z1_down.setHeight(20);
        rotate_z1_down.setPosition(settings.getWidth() / 1.23f, settings.getHeight() / 3.28f);
        guiNode.attachChild(rotate_z1_down);

        move_xr = new Picture("move_xr");
        move_xr.setImage(assetManager, "Textures/restart.png", true);
        move_xr.setWidth(25);
        move_xr.setHeight(25);
        move_xr.setPosition(settings.getWidth() / 1.325f, settings.getHeight() / 1.2f);
        guiNode.attachChild(move_xr);

        move_yr = new Picture("move_yr");
        move_yr.setImage(assetManager, "Textures/restart.png", true);
        move_yr.setWidth(25);
        move_yr.setHeight(25);
        move_yr.setPosition(settings.getWidth() / 1.325f, settings.getHeight() / 1.32f);
        guiNode.attachChild(move_yr);

        move_zr = new Picture("move_zr");
        move_zr.setImage(assetManager, "Textures/restart.png", true);
        move_zr.setWidth(25);
        move_zr.setHeight(25);
        move_zr.setPosition(settings.getWidth() / 1.325f, settings.getHeight() / 1.48f);
        guiNode.attachChild(move_zr);

        rotate_xr = new Picture("rotate_xr");
        rotate_xr.setImage(assetManager, "Textures/restart.png", true);
        rotate_xr.setWidth(25);
        rotate_xr.setHeight(25);
        rotate_xr.setPosition(settings.getWidth() / 1.325f, settings.getHeight() / 2.2f);
        guiNode.attachChild(rotate_xr);

        rotate_yr = new Picture("rotate_yr");
        rotate_yr.setImage(assetManager, "Textures/restart.png", true);
        rotate_yr.setWidth(25);
        rotate_yr.setHeight(25);
        rotate_yr.setPosition(settings.getWidth() / 1.325f, settings.getHeight() / 2.63f);
        guiNode.attachChild(rotate_yr);

        rotate_zr = new Picture("rotate_zr");
        rotate_zr.setImage(assetManager, "Textures/restart.png", true);
        rotate_zr.setWidth(25);
        rotate_zr.setHeight(25);
        rotate_zr.setPosition(settings.getWidth() / 1.325f, settings.getHeight() / 3.28f);
        guiNode.attachChild(rotate_zr);

        spawn = new Picture("spawn");
        spawn.setImage(assetManager, "Textures/button_spawn-cube.png", true);
        spawn.setWidth(200);
        spawn.setHeight(50);
        spawn.setPosition(settings.getWidth() / 30f, settings.getHeight() / 1.2f);
        guiNode.attachChild(spawn);

        trailt = new Picture("trail");
        trailt.setImage(assetManager, "Textures/button_toggle-trail.png", true);
        trailt.setWidth(200);
        trailt.setHeight(50);
        trailt.setPosition(settings.getWidth() / 30f, settings.getHeight() / 1.31f);
        guiNode.attachChild(trailt);

        BitmapText col = new BitmapText(guiFont, false);
        col.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        col.setText("Color:");        // fake crosshairs :)
        col.setLocalTranslation( // center
                settings.getWidth() / 26f - guiFont.getCharSet().getRenderedSize() * 3,
                settings.getHeight() / 1.1f + col.getLineHeight() / 1.1f, 0);
        guiNode.attachChild(col);

        blue = new Picture("blue");
        blue.setImage(assetManager, "Textures/blue.png", true);
        blue.setWidth(30);
        blue.setHeight(30);
        blue.setPosition(settings.getWidth() / 14f, settings.getHeight() / 1.1f);
        guiNode.attachChild(blue);

        red = new Picture("red");
        red.setImage(assetManager, "Textures/red.png", true);
        red.setWidth(30);
        red.setHeight(30);
        red.setPosition(settings.getWidth() / 10f, settings.getHeight() / 1.1f);
        guiNode.attachChild(red);

        green = new Picture("green");
        green.setImage(assetManager, "Textures/green.png", true);
        green.setWidth(30);
        green.setHeight(30);
        green.setPosition(settings.getWidth() / 7.75f, settings.getHeight() / 1.1f);
        guiNode.attachChild(green);

        yellow = new Picture("yellow");
        yellow.setImage(assetManager, "Textures/yellow.png", true);
        yellow.setWidth(30);
        yellow.setHeight(30);
        yellow.setPosition(settings.getWidth() / 6.4f, settings.getHeight() / 1.1f);
        guiNode.attachChild(yellow);

        randomcolor = new Picture("colorchange");
        randomcolor.setImage(assetManager, "Textures/random.png", true);
        randomcolor.setWidth(30);
        randomcolor.setHeight(30);
        randomcolor.setPosition(settings.getWidth() / 5.5f, settings.getHeight() / 1.1f);
        guiNode.attachChild(randomcolor);
        BitmapText mov = new BitmapText(guiFont, false);
        mov.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        mov.setText("Movement");        // fake crosshairs :)
        mov.setLocalTranslation( // center
                settings.getWidth() / 1.18f - guiFont.getCharSet().getRenderedSize() / 3 * 2,
                settings.getHeight() / 1.1f + mov.getLineHeight() / 2, 0);
        guiNode.attachChild(mov);

        BitmapText rot = new BitmapText(guiFont, false);
        rot.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        rot.setText("Rotation");        // fake crosshairs :)
        rot.setLocalTranslation( // center
                settings.getWidth() / 1.18f - guiFont.getCharSet().getRenderedSize() / 3 * 2,
                settings.getHeight() / 1.8f + rot.getLineHeight() / 2, 0);
        guiNode.attachChild(rot);

        BitmapText col_trail = new BitmapText(guiFont, false);
        col_trail.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        col_trail.setText("Trail:");        // fake crosshairs :)
        col_trail.setLocalTranslation( // center
                settings.getWidth() / 26f - guiFont.getCharSet().getRenderedSize() * 3,
                settings.getHeight() / 1.4f + col_trail.getLineHeight() / 1.1f, 0);
        guiNode.attachChild(col_trail);

        blue1 = new Picture("trail_blue");
        blue1.setImage(assetManager, "Textures/blue.png", true);
        blue1.setWidth(30);
        blue1.setHeight(30);
        blue1.setPosition(settings.getWidth() / 14f, settings.getHeight() / 1.4f);
        guiNode.attachChild(blue1);

        red1 = new Picture("trail_red");
        red1.setImage(assetManager, "Textures/red.png", true);
        red1.setWidth(30);
        red1.setHeight(30);
        red1.setPosition(settings.getWidth() / 10f, settings.getHeight() / 1.4f);
        guiNode.attachChild(red1);

        green1 = new Picture("trail_green");
        green1.setImage(assetManager, "Textures/green.png", true);
        green1.setWidth(30);
        green1.setHeight(30);
        green1.setPosition(settings.getWidth() / 7.75f, settings.getHeight() / 1.4f);
        guiNode.attachChild(green1);

        yellow1 = new Picture("trail_yellow");
        yellow1.setImage(assetManager, "Textures/yellow.png", true);
        yellow1.setWidth(30);
        yellow1.setHeight(30);
        yellow1.setPosition(settings.getWidth() / 6.4f, settings.getHeight() / 1.4f);
        guiNode.attachChild(yellow1);

        randomcolor1 = new Picture("trail_colorchange");
        randomcolor1.setImage(assetManager, "Textures/random.png", true);
        randomcolor1.setWidth(30);
        randomcolor1.setHeight(30);
        randomcolor1.setPosition(settings.getWidth() / 5.5f, settings.getHeight() / 1.4f);
        guiNode.attachChild(randomcolor1);

        move_r = new Picture("move_r");
        move_r.setImage(assetManager, "Textures/restart2.png", true);
        move_r.setWidth(25);
        move_r.setHeight(25);
        move_r.setPosition(settings.getWidth() / 1.22f, settings.getHeight() / 1.125f);
        guiNode.attachChild(move_r);

        rotate_r = new Picture("rotate_r");
        rotate_r.setImage(assetManager, "Textures/restart2.png", true);
        rotate_r.setWidth(25);
        rotate_r.setHeight(25);
        rotate_r.setPosition(settings.getWidth() / 1.22f, settings.getHeight() / 1.85f);
        guiNode.attachChild(rotate_r);

        BitmapText move_xt = new BitmapText(guiFont, false);
        move_xt.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        move_xt.setText("X:");        // fake crosshairs :)
        move_xt.setLocalTranslation( // center
                settings.getWidth() / 1.28f - guiFont.getCharSet().getRenderedSize() / 3 * 2,
                settings.getHeight() / 1.18f + move_xt.getLineHeight() / 2, 0);
        guiNode.attachChild(move_xt);

        BitmapText move_yt = new BitmapText(guiFont, false);
        move_yt.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        move_yt.setText("Y:");        // fake crosshairs :)
        move_yt.setLocalTranslation( // center
                settings.getWidth() / 1.28f - guiFont.getCharSet().getRenderedSize() / 3 * 2,
                settings.getHeight() / 1.28f + move_yt.getLineHeight() / 2, 0);
        guiNode.attachChild(move_yt);

        BitmapText move_zt = new BitmapText(guiFont, false);
        move_zt.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        move_zt.setText("Z:");        // fake crosshairs :)
        move_zt.setLocalTranslation( // center
                settings.getWidth() / 1.28f - guiFont.getCharSet().getRenderedSize() / 3 * 2,
                settings.getHeight() / 1.44f + move_zt.getLineHeight() / 2, 0);
        guiNode.attachChild(move_zt);

        BitmapText rotate_xt = new BitmapText(guiFont, false);
        rotate_xt.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        rotate_xt.setText("X:");        // fake crosshairs :)
        rotate_xt.setLocalTranslation( // center
                settings.getWidth() / 1.28f - guiFont.getCharSet().getRenderedSize() / 3 * 2,
                settings.getHeight() / 2.15f + rotate_xt.getLineHeight() / 2, 0);
        guiNode.attachChild(rotate_xt);

        BitmapText rotate_yt = new BitmapText(guiFont, false);
        rotate_yt.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        rotate_yt.setText("Y:");        // fake crosshairs :)
        rotate_yt.setLocalTranslation( // center
                settings.getWidth() / 1.28f - guiFont.getCharSet().getRenderedSize() / 3 * 2,
                settings.getHeight() / 2.54f + rotate_yt.getLineHeight() / 2, 0);
        guiNode.attachChild(rotate_yt);

        BitmapText rotate_zt = new BitmapText(guiFont, false);
        rotate_zt.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        rotate_zt.setText("Z:");        // fake crosshairs :)
        rotate_zt.setLocalTranslation( // center
                settings.getWidth() / 1.28f - guiFont.getCharSet().getRenderedSize() / 3 * 2,
                settings.getHeight() / 3.1f + rotate_zt.getLineHeight() / 2, 0);
        guiNode.attachChild(rotate_zt);

        move_xt1 = new BitmapText(guiFont, false);
        move_xt1.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        move_xt1.setText(Float.toString(move_x));
        move_xt1.setLocalTranslation( // center
                settings.getWidth() / 1.18f - guiFont.getCharSet().getRenderedSize() / 3 * 2,
                settings.getHeight() / 1.18f + move_xt1.getLineHeight() / 2, 0);
        guiNode.attachChild(move_xt1);

        move_yt1 = new BitmapText(guiFont, false);
        move_yt1.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        move_yt1.setText(Float.toString(move_y));
        move_yt1.setLocalTranslation( // center
                settings.getWidth() / 1.18f - guiFont.getCharSet().getRenderedSize() / 3 * 2,
                settings.getHeight() / 1.28f + move_yt1.getLineHeight() / 2, 0);
        guiNode.attachChild(move_yt1);

        move_zt1 = new BitmapText(guiFont, false);
        move_zt1.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        move_zt1.setText(Float.toString(move_z));
        move_zt1.setLocalTranslation( // center
                settings.getWidth() / 1.18f - guiFont.getCharSet().getRenderedSize() / 3 * 2,
                settings.getHeight() / 1.44f + move_zt1.getLineHeight() / 2, 0);
        guiNode.attachChild(move_zt1);

        rotate_xt1 = new BitmapText(guiFont, false);
        rotate_xt1.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        rotate_xt1.setText(Float.toString(rotate_x));
        rotate_xt1.setLocalTranslation( // center
                settings.getWidth() / 1.18f - guiFont.getCharSet().getRenderedSize() / 3 * 2,
                settings.getHeight() / 2.12f + rotate_xt1.getLineHeight() / 2, 0);
        guiNode.attachChild(rotate_xt1);

        rotate_yt1 = new BitmapText(guiFont, false);
        rotate_yt1.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        rotate_yt1.setText(Float.toString(rotate_y));
        rotate_yt1.setLocalTranslation( // center
                settings.getWidth() / 1.18f - guiFont.getCharSet().getRenderedSize() / 3 * 2,
                settings.getHeight() / 2.5f + rotate_yt1.getLineHeight() / 2, 0);
        guiNode.attachChild(rotate_yt1);

        rotate_zt1 = new BitmapText(guiFont, false);
        rotate_zt1.setSize(guiFont.getCharSet().getRenderedSize() * 2);
        rotate_zt1.setText(Float.toString(rotate_z));
        rotate_zt1.setLocalTranslation( // center
                settings.getWidth() / 1.18f - guiFont.getCharSet().getRenderedSize() / 3 * 2,
                settings.getHeight() / 3.1f + rotate_zt1.getLineHeight() / 2, 0);
        guiNode.attachChild(rotate_zt1);
        initKeys();

        fire
                = new ParticleEmitter("Emitter", ParticleMesh.Type.Triangle, 30);
        Material mat_red = new Material(assetManager,
                "Common/MatDefs/Misc/Particle.j3md");
        mat_red.setTexture("Texture", assetManager.loadTexture(
                "Effects/Explosion/flame.png"));
        fire.setMaterial(mat_red);
        fire.setImagesX(2);
        fire.setImagesY(2); // 2x2 texture animation
        fire.setEndColor(new ColorRGBA(1f, 0f, 0f, 1f));   // red
        fire.setStartColor(new ColorRGBA(1f, 1f, 0f, 0.5f)); // yellow
        fire.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 0, 0));
        fire.setStartSize(1f);
        fire.setEndSize(1f);
        fire.setGravity(0, 0, 0);
        fire.setLowLife(1f);
        fire.setHighLife(1f);
        fire.getParticleInfluencer().setVelocityVariation(0.3f);
        fire.setLocalTranslation(geom.getLocalTranslation());

    }

    private void initAudio() {
        /* gun shot sound is to be triggered by a mouse click. */

 /* nature sound - keeps playing in a loop. */
        audio_nature = new AudioNode(assetManager, "Sounds/audio.wav", DataType.Stream);
        audio_nature.setLooping(true);  // activate continuous playing
        audio_nature.setPositional(false);
        audio_nature.setVolume(3);
        rootNode.attachChild(audio_nature);
        audio_nature.play(); // play continuously!

        audio_gun = new AudioNode(assetManager, "Sounds/mouseclick1.wav", DataType.Buffer);
        audio_gun.setPositional(false);
        audio_gun.setLooping(false);
        audio_gun.setVolume(2);
        rootNode.attachChild(audio_gun);

    }

    @Override
    public void simpleUpdate(float tpf) {
        click2d = inputManager.getCursorPosition();
        click3d = cam.getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 0f).clone();
        dir = cam.getWorldCoordinates(new Vector2f(click2d.x, click2d.y), 1f).subtractLocal(click3d).normalizeLocal();
        Vector3f forward = new Vector3f(move_x, move_y, move_z);
        geom.move(forward.mult(tpf).mult(speed_move));
        if (!rotater) {
            geom.rotate(rotate_x * tpf * speed_rotate, rotate_y * tpf * speed_rotate, rotate_z * tpf * speed_rotate);
        } else {
            geom.setLocalRotation(Matrix3f.ZERO);
            rotater = false;
        }
        move_xt1.setText(Float.toString(move_x));
        move_yt1.setText(Float.toString(move_y));
        move_zt1.setText(Float.toString(move_z));
        rotate_xt1.setText(Float.toString(rotate_x));
        rotate_yt1.setText(Float.toString(rotate_y));
        rotate_zt1.setText(Float.toString(rotate_z));
        fire.setLocalTranslation(geom.getLocalTranslation());
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    private void initKeys() {
        inputManager.addMapping("Click",
                new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addListener(actionListener, "Click");
    }
    private ActionListener actionListener = new ActionListener() {

        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("Click") && !keyPressed) {

                CollisionResults results = new CollisionResults();

                Ray ray = new Ray(new Vector3f(click2d.x, click2d.y, 0), dir);

                guiNode.collideWith(ray, results);

                System.out.println("----- Collisions? " + results.size() + "-----");
                for (int i = 0; i < results.size(); i++) {

                    float dist = results.getCollision(i).getDistance();
                    Vector3f pt = results.getCollision(i).getContactPoint();
                    hit = results.getCollision(i).getGeometry().getName();
                    System.out.println("* Collision #" + i);
                    System.out.println("  You shot " + hit + " at " + pt + ", " + dist + " wu away.");
                }

                if (results.size() > 0) {

                    CollisionResult closest = results.getClosestCollision();
                    audio_gun.playInstance();
                    if (hit == "move_x_up") {
                        move_x += 1;
                    } else if (hit == "move_y_up") {
                        move_y += 1;
                    } else if (hit == "move_z_up") {
                        move_z += 1;
                    } else if (hit == "rotate_x_up") {
                        rotate_x += 1;
                    } else if (hit == "rotate_y_up") {
                        rotate_y += 1;
                    } else if (hit == "rotate_z_up") {
                        rotate_z += 1;
                    } else if (hit == "move_x_down") {
                        move_x += -1;
                    } else if (hit == "move_y_down") {
                        move_y += -1;
                    } else if (hit == "move_z_down") {
                        move_z += -1;
                    } else if (hit == "rotate_x_down") {
                        rotate_y += -1;
                    } else if (hit == "rotate_y_down") {
                        rotate_x += -1;
                    } else if (hit == "rotate_z_down") {
                        rotate_z += -1;
                    } else if (hit == "colorchange") {
                        mat.setColor("Color", ColorRGBA.randomColor());
                        geom.setMaterial(mat);
                    } else if (hit == "spawn") {
                        Box a = new Box(1, 1, 1);
                        Geometry spawned = new Geometry("BoxNew", a);

                        spawned.setMaterial(geom.getMaterial());
                        spawned.setLocalTranslation(geom.getLocalTranslation());
                        spawned.setLocalRotation(geom.getLocalRotation());
                        rootNode.attachChild(spawned);
                    } else if (hit == "trail") {
                        if (lit) {
                            rootNode.detachChild(fire);
                            lit = false;
                        } else {
                            rootNode.attachChild(fire);
                            lit = true;
                        }

                    } else if (hit == "blue") {
                        mat.setColor("Color", ColorRGBA.Blue);
                        geom.setMaterial(mat);
                    } else if (hit == "red") {
                        mat.setColor("Color", ColorRGBA.Red);
                        geom.setMaterial(mat);
                    } else if (hit == "green") {
                        mat.setColor("Color", ColorRGBA.Green);
                        geom.setMaterial(mat);
                    } else if (hit == "yellow") {
                        mat.setColor("Color", ColorRGBA.Yellow);
                        geom.setMaterial(mat);
                    } else if (hit == "zeroloc") {
                        geom.setLocalTranslation(0, 0, 0);
                    } else if (hit == "zerorot") {
                        rotater = true;

                    } else if (hit == "move_xr") {
                        move_x = 0;
                    } else if (hit == "move_yr") {
                        move_y = 0;
                    } else if (hit == "move_zr") {
                        move_z = 0;
                    } else if (hit == "rotate_xr") {
                        rotate_x = 0;
                    } else if (hit == "rotate_yr") {
                        rotate_y = 0;
                    } else if (hit == "rotate_zr") {
                        rotate_z = 0;
                    } else if (hit == "move_r") {
                        move_x = 0;
                        move_y = 0;
                        move_z = 0;
                    } else if (hit == "rotate_r") {
                        rotate_z = 0;
                        rotate_x = 0;
                        rotate_y = 0;
                    } else if (hit == "trail_blue") {
                        fire.setEndColor(new ColorRGBA(ColorRGBA.Blue));   // red
                        fire.setStartColor(new ColorRGBA(1, 1, 1, 0.5f));
                    } else if (hit == "trail_red") {
                        fire.setEndColor(new ColorRGBA(1f, 0f, 0f, 1f));   // red
                        fire.setStartColor(new ColorRGBA(1f, 1f, 0f, 0.5f));
                    } else if (hit == "trail_green") {
                        fire.setEndColor(new ColorRGBA(ColorRGBA.Green));   // red
                        fire.setStartColor(new ColorRGBA(1f, 1f, 1f, .5f));
                    } else if (hit == "trail_yellow") {
                        fire.setEndColor(new ColorRGBA(ColorRGBA.Yellow));   // red
                        fire.setStartColor(new ColorRGBA(1f, 1f, 1f, .5f));
                    } else if (hit == "trail_colorchange") {
                        fire.setEndColor(new ColorRGBA(ColorRGBA.randomColor()));   // red
                        fire.setStartColor(new ColorRGBA(ColorRGBA.randomColor()));
                    }
                } else {

                }
            }
        }
    };
}
