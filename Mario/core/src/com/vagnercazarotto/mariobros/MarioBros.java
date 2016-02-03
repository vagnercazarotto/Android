package com.vagnercazarotto.mariobros;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vagnercazarotto.mariobros.Screens.PlayScreen;

//Starting with extend Game
public class MarioBros extends Game {
    // Use Scene2d.ui, it's provide a layout manager for widgets like actors, groups, drawing, events
    public static final int V_WIDTH = 400;
    public static final int V_HEIGHT= 208;
	public static final float PPM = 100;

	public static final short GROUND_BIT = 1;
	public static final short MARIO_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short COIN_BIT = 8;
	public static final short DESTROYED_BIT = 16;
    public static final short OBJECT_BIT = 32;
    public static final short ENEMY_BIT = 64;

	public SpriteBatch batch;
	// Sprite is a container who holds all of our images, textures , etc.
	// And render in to the monitor

	/// WARNING: Using AssetManager in a static way cause issues, especially in Android, instead you may want to pass
	/// around Assetmanager to those the classes that need it, We will use it in the static context to save time for now

	public static AssetManager manager;

	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("audio/music/mario_music.ogg", Music.class);
		manager.load("audio/sounds/coin.wav", Sound.class);
		manager.load("audio/sounds/bump.wav", Sound.class);
		manager.load("audio/sounds/bbrick.wav", Sound.class);
        manager.load("audio/sounds/think_i_do2.wav", Sound.class);
		manager.load("audio/sounds/apollo_problem.wav", Sound.class);
		manager.finishLoading();
		setScreen(new PlayScreen(this)); // Start rendering, but you need to pass the this app
	}

    @Override
    public void dispose() {
        super.dispose();
        manager.dispose();
        batch.dispose();
    }

    @Override
	public void render () {
		// delegate the render method to the screem
		super.render();
	}

    public static String bitToText(short bit){
        switch(bit){
            case 1:
                return "DEFAULT";
            case 2:
                return "MARIO";
            case 4:
                return "BRICK";
            case 8:
                return "COIN";
            case 16:
                return "DESTROYED";
            case 32:
                return "ENEMY";
            case 64:
                return "GROUND";
            default:
                return "DEFAULT";
        }
    }

}
