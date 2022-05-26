package com.tekronet;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.io.InputStream;
import java.util.Properties;

public class MoonCatcher extends Game {
	public SpriteBatch batch;
	public ShapeRenderer shapeRenderer;
	private FreeTypeFontGenerator fontGenerator;
	private FreeTypeFontGenerator.FreeTypeFontParameter fontParameterBig, fontParameterMedium, fontParameterSmall;
	public BitmapFont bigFont, mediumFont, smallFont, microFont;
	public static int score;

	@Override
	public void create() {
		SaveGame.initPrefs();
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();

		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Roboto-Regular.ttf"));
		fontParameterBig = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameterMedium = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameterSmall = new FreeTypeFontGenerator.FreeTypeFontParameter();

		fontParameterBig.size = 120;
		fontParameterBig.borderWidth = 2;
		fontParameterBig.borderColor = Color.WHITE;
		fontParameterBig.color = Color.valueOf("#00052e");

		fontParameterSmall.size = 50;
		fontParameterMedium.size = 70;

		fontParameterBig.magFilter = Texture.TextureFilter.Linear;
		fontParameterBig.minFilter = Texture.TextureFilter.Linear;
		fontParameterMedium.magFilter = Texture.TextureFilter.Linear;
		fontParameterMedium.minFilter = Texture.TextureFilter.Linear;
		fontParameterSmall.magFilter = Texture.TextureFilter.Linear;
		fontParameterSmall.minFilter = Texture.TextureFilter.Linear;
		bigFont = fontGenerator.generateFont(fontParameterBig);
		mediumFont = fontGenerator.generateFont(fontParameterMedium);
		smallFont = fontGenerator.generateFont(fontParameterSmall);
		fontParameterSmall.size = 30;
		microFont = fontGenerator.generateFont(fontParameterSmall);

		fontGenerator.dispose();

		setScreen(new MainMenu(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	public static int distanceBeetween(float x1, float x2, float y1, float y2) {
		return (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
}