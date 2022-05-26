package com.tekronet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenu implements Screen{

    private MoonCatcher game;
    private OrthographicCamera cam;
    private Viewport viewport;
    private Sprite ball;
    private int width;
    private int height;
    private Vector3 mousePos = new Vector3();
    private int bestScore;

    private GlyphLayout glyphTitle, glyphHint, glyphScore;

    public MainMenu(MoonCatcher game) {
        bestScore = SaveGame.getBestScore();
        this.game = game;
        cam = new OrthographicCamera();
        viewport = new FitViewport(1920, 1080, cam);
        ball = new Sprite(new Texture(Gdx.files.internal("ball.png")));
        ball.setPosition(-ball.getWidth() / 2, -ball.getHeight() / 2);
        glyphTitle = new GlyphLayout();
        glyphHint = new GlyphLayout();
        glyphScore = new GlyphLayout();
        glyphTitle.setText(game.bigFont, "Moon Catcher");
        glyphHint.setText(game.smallFont, "Tap ball to start game");
        glyphScore.setText(game.smallFont, "Best Score: " + bestScore);
        width = 1920;
        height = 1080;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.shapeRenderer.setProjectionMatrix(cam.combined);
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.setColor(0f, 0.019f, 0.180f, 0);
        game.shapeRenderer.rect(-1920/2, -1080/2,1920, 1080);
        game.shapeRenderer.end();

        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        game.bigFont.draw(game.batch, "Moon Catcher", -glyphTitle.width / 2, 1080 / 2 - 22);
        game.smallFont.draw(game.batch, "Tap ball to start game", -glyphHint.width / 2, -1080/2 + glyphHint.height + 30);
        game.smallFont.draw(game.batch, "Best Score: " + bestScore, -glyphScore.width / 2, 1080/2 - 132);
        game.microFont.draw(game.batch, "Tekronet 2022", 750, -495);
        ball.draw(game.batch);
        cam.update();
        mousePos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        cam.unproject(mousePos);
        game.batch.end();

        if (game.distanceBeetween(mousePos.x, 0, mousePos.y, 0) <= 125) {
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) || Gdx.input.justTouched()) {
                game.setScreen(new PlayScreen(this.game));
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        this.width = width;
        this.height = height;
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
        game.batch.dispose();
    }
}
