/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Game;
import javafx.geometry.Point2D;
import static model.Corridor.CandyType.*;

/**
 *
 * @author Najib EL KHADIR
 */
public class Corridor extends Tile {
    
    public enum CandyType {
        EMPTY, NORMAL, SUPER;
    }
    
    private final Game game;
    private CandyType candy;
    private Entity entity;
    
    public Corridor(Point2D coords, Game game) {
        super(coords);
        this.game = game;
        this.candy = EMPTY;
        this.entity = null;
    }

    public Corridor(Point2D coords, Game game, CandyType candy) {
        super(coords);
        this.game = game;
        this.candy = candy;
        this.entity = null;
    }

    public Corridor(Point2D coords, Game game, Entity entity) {
        super(coords);
        this.game = game;
        this.candy = EMPTY;
        this.entity = entity;
    }
    
    public CandyType getType() {
        return this.candy;
    }

    public boolean isCandy() {
        return (this.candy != EMPTY);
    }

    public Entity getEntity() {
        return this.entity;
    }

    public void removeEntity() {
        this.entity = null;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
        if (this.isCandy()) {
            this.eatCandy();
        }
    }
    
    private boolean eatCandy() {
        if (this.entity instanceof Pacman) {
            switch(this.candy) {
                case NORMAL:
                    this.game.addScore(100);
                    break;
                case SUPER:
                    this.game.addScore(500);
                    break;
            }
            this.candy = EMPTY;
            return true;
        }
        return false;
    }
}
