package com.geekbrains.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BombEmitter extends ObjectPool<Bomb> {
    private AnimationEmitter animationEmitter;
    private TextureRegion textureRegion;

    @Override
    protected Bomb newObject() {
        return new Bomb(animationEmitter, textureRegion);
    }

    public BombEmitter(AnimationEmitter animationEmitter) {
        this.animationEmitter = animationEmitter;
        this.textureRegion = Assets.getInstance().getAtlas().findRegion("bomb");
        this.addObjectsToFreeList(10);
    }

    public boolean isBomb(int cellX, int cellY) {
        for (int i = 0; i < activeList.size(); i++) {
            if (activeList.get(i).getCellX() == cellX && activeList.get(i).getCellY() == cellY) return true;
        }
        return false;
    }

    public Bomb findBomb(int cellX, int cellY) {
        for (int i = 0; i < activeList.size(); i++) {
            if (activeList.get(i).getCellX() == cellX && activeList.get(i).getCellY() == cellY) return activeList.get(i);
        }
        return null;
    }

    public void update(float dt) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).update(dt);
        }
        checkPool();
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < activeList.size(); i++) {
            activeList.get(i).render(batch);
        }
    }
}
