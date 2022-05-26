package com.tekronet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Ball {
    public int x = 0;
    public int y = 0;

    public Sprite sprite = new Sprite(new Texture("ball.png"));
    public Ball() {
        sprite.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public int randomX() {
        Random r = new Random();
        int bound = 1920 - 320;
        int random = r.nextInt(bound);
        return (-1920/2 + 20 + random);
    }
    public int randomY() {
        Random r = new Random();
        int bound = 1080 -320 - 120;
        int random = r.nextInt(bound);
        return (-1080/2 + 20 +random);
    }
}
