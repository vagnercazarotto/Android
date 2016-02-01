package com.vagnercazarotto.mariobros;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vagnercazarotto.mariobros.Screens.PlayScreen;

//Starting with extend Game
public class MarioBros extends Game {
	public SpriteBatch batch;
	// Sprite is a container who holds all of our images, textures , etc.
	// And render in to the monitor

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this)); // Start rendering, but you need to pass the this app
	}

	@Override
	public void render () {
		super.render(); // delegate the render method to the screem
	}
}
