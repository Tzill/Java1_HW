package com.geekbrains.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BomberGame extends Game {
    private SpriteBatch batch;

    // Домашнее задание
    // 1. Разбор кода, для сдачи просто файл либо с вопросами, либо сообщение "все понятно"
    // 2. * Уничтожение бомбами ящиков, взрыв не должен продолжаться если влетел в стену или в ящик
    // 3. ** Поджигание соседних бомб

    @Override
    public void create() {
        batch = new SpriteBatch();
        ScreenManager.getInstance().init(this, batch);
        ScreenManager.getInstance().changeScreen(ScreenManager.ScreenType.MENU);
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        getScreen().render(dt);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
