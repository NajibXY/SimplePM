/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import com.sun.javafx.scene.traversal.Direction;
import java.util.Observable;

/**
 *
 * @author Najib EL KHADIR
 */
public class Game extends Observable{
    private int width;
    private int height;
    private Entity[][] tabEntities;
    private Tile[][] board;

    public Game(int width, int height, Entity[][] tabEntities, Tile[][] board) {
        this.width = width;
        this.height = height;
        this.tabEntities = tabEntities;
        this.board = board;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public Entity[][] getTabEntities() {
        return tabEntities;
    }      
    
    
    public void initialize(){
        
    }
    
    public boolean endGame(){
        
        return false;
    }
    
    public void move (Entity e, Direction d){
        
    }
}
