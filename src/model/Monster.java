/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Game;
import util.Direction;
import javafx.geometry.Point2D;

/**
 *
 * @author Najib EL KHADIR
 */
public class Monster extends Entity{

    public Monster(Direction currentDir, Point2D coords, String spriteId, Game game, int interval) {
        super(currentDir, coords, spriteId, game, interval);
    }

   

    @Override
    protected Direction getNextDirection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    protected Direction getNextPath() {
        
    }
    
    @Override
    public boolean canKill(Entity enemy) {
        return (enemy instanceof Pacman && !((Pacman) enemy).isPower());
    }
}
