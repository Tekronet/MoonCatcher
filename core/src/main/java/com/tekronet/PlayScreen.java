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

public class PlayScreen implements Screen {

    private MoonCatcher game;
    private OrthographicCamera cam = new OrthographicCamera();
    private Viewport viewport = new FitViewport(1920, 1080, cam);
    private Vector3 cursorPos = new Vector3();
    private Ball ball = new Ball();
    private boolean first = true;
    int nx;
    int ny;
    private float period = 0.05f;
    private float timeSeconds = 0f;
    private Sprite progressBar = new Sprite(new Texture(Gdx.files.internal("bitmap2.png")));
    private float time = 6;

    GlyphLayout scoreGlyph = new GlyphLayout();

    public PlayScreen(MoonCatcher game) {
        this.game = game;
    }

    public void resizeBar() {
        progressBar.setSize(time * 50, progressBar.getHeight());
    }
    @Override
    public void show() {
        progressBar.setSize(time * 50, progressBar.getHeight());
        scoreGlyph.setText(game.mediumFont, Integer.toString(MoonCatcher.score));
        progressBar.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        progressBar.setScale(1.4f);
        progressBar.setPosition(1920/2 - progressBar.getWidth() * progressBar.getScaleX(), 1080/2 - progressBar.getHeight() * progressBar.getScaleY() - 12);
    }

    @Override
    public void render(float delta) {
        timeSeconds += Gdx.graphics.getDeltaTime();
        if (timeSeconds > period) {
            timeSeconds -= period;
            resizeBar();
            time -= 0.05;
        }

        cam.update();

        //mouse position
        cursorPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        cam.unproject(cursorPos);

        //rendering
        Gdx.gl.glClearColor(0, 0, 0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.shapeRenderer.setProjectionMatrix(cam.combined);
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.setColor(0f, 0.019f, 0.180f, 0);
        game.shapeRenderer.rect(-1920/2, -1080/2,1920, 1080);
        game.shapeRenderer.setColor(0f, 0.019f, 0.250f, 0);
        game.shapeRenderer.rect(-1920/2, 1080/2 -120, 1920, 120);
        game.shapeRenderer.end();

        if (first) {
            int x = ball.randomX();
            int y = ball.randomY();
            nx = x;
            ny = y;
            MoonCatcher.score++;
            first = false;
        }
        if (Gdx.input.justTouched() || Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if ((MoonCatcher.distanceBeetween(cursorPos.x, ball.sprite.getX() + 125, cursorPos.y, ball.sprite.getY() + 125)) <= 125) {
                nx = ball.randomX();
                ny = ball.randomY();
                MoonCatcher.score++;
                if (time > 6) {
                    time = 6;
                }
                if (MoonCatcher.score < 25) {
                    time += 0.50;
                }
                else if (MoonCatcher.score > 25 && MoonCatcher.score < 50) {
                    time += 0.45;
                }
                else if (MoonCatcher.score > 50 && MoonCatcher.score < 75){
                    time += 0.4f;
                }
                else {
                    time += 0.35f;
                }
            }
            else {
                game.setScreen(new GameOver(game));
            }
        }
        if ((int)ball.sprite.getX() != nx && (int)ball.sprite.getY() != ny) {
            if ((int)ball.sprite.getX() < nx)
                ball.sprite.setPosition(ball.sprite.getX() + ((nx - ball.sprite.getX())/8), ball.sprite.getY());
            else
                ball.sprite.setPosition(ball.sprite.getX() - ((ball.sprite.getX() - nx)/8), ball.sprite.getY());

            if ((int)ball.sprite.getY() < ny)
                ball.sprite.setPosition(ball.sprite.getX(), ball.sprite.getY() + ((ny - ball.sprite.getY())/8));
            else
                ball.sprite.setPosition(ball.sprite.getX(), ball.sprite.getY() - ((ball.sprite.getY() - ny)/8));
        }
        if (time <= 0) {
            game.setScreen(new GameOver(game));
        }
        game.batch.setProjectionMatrix(cam.combined);
        game.batch.begin();
        ball.sprite.draw(game.batch);
        progressBar.draw(game.batch);
        game.smallFont.draw(game.batch, "Score: " + Integer.toString(MoonCatcher.score), (float)-1920/2 + 20, (float)1080/2 -40);
        game.smallFont.draw(game.batch, "Time", (float)-1920/2 + 1300, (float)1080/2 - 40);
        game.batch.end();
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
