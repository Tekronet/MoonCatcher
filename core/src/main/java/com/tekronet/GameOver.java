package com.tekronet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameOver implements Screen {

    MoonCatcher game;
    private OrthographicCamera cam = new OrthographicCamera();
    private Viewport viewport = new FitViewport(1920, 1080, cam);
    GlyphLayout gameOverGlyph = new GlyphLayout();
    GlyphLayout scoreGlyph = new GlyphLayout();
    GlyphLayout bestScoreGlyph = new GlyphLayout();
    GlyphLayout hintGlyph = new GlyphLayout();
    private int bestScore;
    private int score;

    public GameOver(MoonCatcher game) {
        this.game = game;
        gameOverGlyph.setText(game.bigFont, "Game Over");
        bestScoreGlyph.setText(game.smallFont, "Best score: " + Integer.toString(SaveGame.getBestScore()));
        scoreGlyph.setText(game.smallFont, "Score: " + Integer.toString(MoonCatcher.score));
        hintGlyph.setText(game.smallFont, "Tap to play again");
        score = MoonCatcher.score;
        MoonCatcher.score = 0;

        if (score > SaveGame.getBestScore()) {
            SaveGame.setBestScore(score);
        }
        bestScore = SaveGame.getBestScore();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        cam.update();
        Gdx.gl.glClearColor(0, 0, 0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.shapeRenderer.setProjectionMatrix(cam.combined);
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.setColor(0f, 0.019f, 0.180f, 0);
        game.shapeRenderer.rect(-1920/2, -1080/2,1920, 1080);
        game.shapeRenderer.end();

        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        game.bigFont.draw(game.batch, "Game Over", -gameOverGlyph.width/2, 1080/2 - 22);
        game.smallFont.draw(game.batch, "Score: " + score, -scoreGlyph.width / 2, 1080/2 - 140);
        game.smallFont.draw(game.batch, "Best Score: " + bestScore, -bestScoreGlyph.width / 2, 1080/2 - 195);
        game.smallFont.draw(game.batch, "Tap to play again", -hintGlyph.width / 2, -1080/2 + hintGlyph.height + 30);
        game.batch.end();

        if (Gdx.input.justTouched()) {
            game.setScreen(new MainMenu(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
