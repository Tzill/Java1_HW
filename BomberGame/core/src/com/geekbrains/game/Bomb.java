package com.geekbrains.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Bomb implements Poolable {
    private TextureRegion texture;
    private AnimationEmitter animationEmitter;
    private Map map;

    public int getCellX() {
        return cellX;
    }

    public int getCellY() {
        return cellY;
    }

    private int cellX, cellY;
    private int radius;

    public void setMaxTime() {
        this.time = maxTime + 1.0f;
    }

    private float time;
    private float maxTime;
    private boolean active;

    private boolean blockedUp;
    private boolean blockedDown;
    private boolean blockedLeft;
    private boolean blockedRight;

    private BombEmitter bombEmitter;

    @Override
    public boolean isActive() {
        return active;
    }

    public Bomb(AnimationEmitter animationEmitter, TextureRegion texture) {
        this.texture = texture;
        this.animationEmitter = animationEmitter;
    }

    public void update(float dt) {
        time += dt;
        if (time >= maxTime) {
            boom();
        }
    }

    public void boom() {
        active = false;
        animationEmitter.createAnimation(cellX * Rules.CELL_SIZE + Rules.CELL_HALF_SIZE, cellY * Rules.CELL_SIZE + Rules.CELL_HALF_SIZE, 4.0f, AnimationEmitter.AnimationType.EXPLOSION);
        for (int i = 1; i <= radius; i++) {
            if (map.isCellExist(cellX + i, cellY) && !blockedRight && (map.isCellDestructable(cellX + i, cellY) || map.isCellEmpty(cellX + i, cellY))) {
                if (bombEmitter.isBomb(cellX + i, cellY))
                    bombEmitter.findBomb(cellX + i, cellY).setMaxTime();
                if (map.isCellDestructable(cellX + i, cellY)) blockedRight = true;
                map.clearCell(cellX + i, cellY);
                animationEmitter.createAnimation((cellX + i) * Rules.CELL_SIZE + Rules.CELL_HALF_SIZE, cellY * Rules.CELL_SIZE + Rules.CELL_HALF_SIZE, 4.0f, AnimationEmitter.AnimationType.EXPLOSION);
            }
            if (map.isCellExist(cellX - i, cellY) && !blockedLeft && (map.isCellDestructable(cellX - i, cellY) || map.isCellEmpty(cellX - i, cellY))) {
                if (bombEmitter.isBomb(cellX - i, cellY))
                    bombEmitter.findBomb(cellX - i, cellY).setMaxTime();
                if (map.isCellDestructable(cellX - i, cellY)) blockedLeft = true;
                map.clearCell(cellX - i, cellY);
                animationEmitter.createAnimation((cellX - i) * Rules.CELL_SIZE + Rules.CELL_HALF_SIZE, cellY * Rules.CELL_SIZE + Rules.CELL_HALF_SIZE, 4.0f, AnimationEmitter.AnimationType.EXPLOSION);
            }
            if (map.isCellExist(cellX, cellY + i) && !blockedUp && (map.isCellDestructable(cellX, cellY + i) || map.isCellEmpty(cellX, cellY + i))) {
                if (bombEmitter.isBomb(cellX, cellY + i))
                    bombEmitter.findBomb(cellX, cellY+ i).setMaxTime();
                if (map.isCellDestructable(cellX, cellY + i)) blockedUp = true;
                map.clearCell(cellX, cellY + i);
                animationEmitter.createAnimation(cellX * Rules.CELL_SIZE + Rules.CELL_HALF_SIZE, (cellY + i) * Rules.CELL_SIZE + Rules.CELL_HALF_SIZE, 4.0f, AnimationEmitter.AnimationType.EXPLOSION);
            }
            if (map.isCellExist(cellX, cellY - i) && !blockedDown && (map.isCellDestructable(cellX, cellY - i) || map.isCellEmpty(cellX, cellY - i))) {
                if (bombEmitter.isBomb(cellX, cellY - i))
                    bombEmitter.findBomb(cellX, cellY - i).setMaxTime();
                if (map.isCellDestructable(cellX, cellY - i)) blockedDown = true;
                map.clearCell(cellX, cellY - i);
                animationEmitter.createAnimation(cellX * Rules.CELL_SIZE + Rules.CELL_HALF_SIZE, (cellY - i) * Rules.CELL_SIZE + Rules.CELL_HALF_SIZE, 4.0f, AnimationEmitter.AnimationType.EXPLOSION);
            }
        }
    }

    public void activate(int cellX, int cellY, float maxTime, int radius, Map map, BombEmitter bombEmitter){
        this.cellX = cellX;
        this.cellY = cellY;
        this.maxTime = maxTime;
        this.radius = radius;
        this.time = 0.0f;
        this.active = true;
        this.map = map;
        this.blockedDown = false;
        this.blockedUp = false;
        this.blockedLeft = false;
        this.blockedRight = false;
        this.radius = 6;
        this.bombEmitter = bombEmitter;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, cellX * Rules.CELL_SIZE, cellY * Rules.CELL_SIZE);
    }
}
