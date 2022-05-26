package com.tekronet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.Properties;

public class SaveGame {
    private static Preferences prefs;

    public static int getBestScore() {
        int score = prefs.getInteger("best_score", 0);
        return score;
    }
    public static void setBestScore(int score) {
        prefs.putInteger("best_score", score);
        prefs.flush();
    }
    public static void initPrefs() {
        prefs = Gdx.app.getPreferences("moon-catcher-prefs");
        if (!prefs.contains("best_score")) {
            prefs.putInteger("best_score", 0);
        }
    }
}
