/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Game;
import java.util.ArrayList;
import util.Direction;
import javafx.geometry.Point2D;

/**
 *
 * @author Najib EL KHADIR
 */
public class Monster extends Entity {
    
    private boolean outside;
    //private boolean scared;

    public Monster(Direction currentDir, Point2D coords, String spriteId, Game game, int interval) {
        super(currentDir, coords, spriteId, game, interval);
    }
    
    public void setOutside(boolean outside) {
        this.outside = outside;
    }
    
    public boolean isOutside() {
        return this.outside;
    }

    /*
    public boolean isScared() {
        return this.scared;
    }
    
    public void scare(){
        this.scared = true;
    }
     */
    private ArrayList<Direction> getPossibleDirections() {
        ArrayList<Direction> possibleDirections = new ArrayList();
        for (Direction direction : Direction.values()) {
            if (this.currentDir.getOpposed() != direction) {
                Point2D adjacentCoords = this.game.getNextCoords(this.coords, direction);
                if (this.game.isReachable(adjacentCoords)) {
                    Tile tile = this.game.getTileByCoords(adjacentCoords);
                    if (tile instanceof Corridor) {
                        if (!(tile instanceof MonsterDoor) || ((MonsterDoor) tile).getPass() == direction) {
                            Entity entity = ((Corridor) tile).getEntity();
                            if (entity == null || entity instanceof Pacman) {
                                possibleDirections.add(direction);
                            }
                        }
                    }
                }
            }
        }
        if (possibleDirections.isEmpty()) {
            possibleDirections.add(this.currentDir.getOpposed());
            possibleDirections.add(Direction.RIGHT);
        }
        return possibleDirections;
    }
    
    private Direction getClosestDirection(Point2D aimedCoords) {
        Direction nextDirection = this.currentDir;
        ArrayList<Direction> possibleDirections = this.getPossibleDirections();
        
        double maxDistance = Double.MAX_VALUE;
        for (Direction direction : possibleDirections) {
            Point2D nextCoords = this.game.getNextCoords(this.coords, direction);
            double distance = aimedCoords.distance(nextCoords);
            if (distance < maxDistance) {
                maxDistance = distance;
                nextDirection = direction;
            }
        }
        
        return nextDirection;
    }
    
    @Override
    public Direction getNextDirection() {
        Point2D aimedCoords;
        if (this.isOutside()) {
            aimedCoords = this.game.getPacMan().getCoords();
        } else {
            MonsterDoor monsterdoor = this.game.getMonsterDoor();
            aimedCoords = this.game.getNextCoords(monsterdoor.getCoords(), monsterdoor.getPass());
        }
        return this.getClosestDirection(aimedCoords);
    }
    
    @Override
    public boolean canKill(Entity enemy) {
        return (enemy instanceof Pacman);
    }
}
