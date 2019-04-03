/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Game;
import javafx.geometry.Point2D;
import util.Direction;

/**
 *
 * @author Najib EL KHADIR
 */
public class MonsterDoor extends Corridor{
    private final Direction permittedDir;
    
    public MonsterDoor(Point2D coords, Game game, Direction direction) {
        super(coords, game);
        this.permittedDir = direction;
    }
    
    public Direction getPass() {
        return this.permittedDir;
    }
}

