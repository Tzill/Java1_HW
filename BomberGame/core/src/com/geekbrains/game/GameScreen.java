package com.geekbrains.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class GameScreen implements Screen {
    private SpriteBatch batch;
    private Map map;
    private Bomberman player;
    private AnimationEmitter animationEmitter;
    private BombEmitter bombEmitter;
    private BitmapFont font32;

    public GameScreen(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void show() {
        map = new Map();
        animationEmitter = new AnimationEmitter();
        bombEmitter = new BombEmitter(animationEmitter);
        player = new Bomberman(map, bombEmitter);
        font32 = Assets.getInstance().getAssetManager().get("gomarice32.ttf", BitmapFont.class);
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        map.render(batch);
        player.render(batch);
        bombEmitter.render(batch);
        animationEmitter.render(batch);
        font32.draw(batch, "Score: 1000", 20, 700);
        batch.end();
    }

    public void update(float dt) {
        map.update(dt);
        player.update(dt);
        bombEmitter.update(dt);
        animationEmitter.update(dt);
        if (Gdx.input.justTouched()) {
            animationEmitter.createAnimation(Gdx.input.getX(), 720 - Gdx.input.getY(), MathUtils.random(1.0f, 10.0f), AnimationEmitter.AnimationType.EXPLOSION);
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        ScreenManager.getInstance().resize(width, height);
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
}
