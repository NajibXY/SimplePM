/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import com.sun.javafx.scene.traversal.Direction;

/**
 *
 * @author Najib EL KHADIR
 */
public abstract class Entity implements Runnable{
    protected Direction currentDirection;

    public Entity(Direction currentDirection, Game game) {
        this.currentDirection = currentDirection;
    }
    
    
}
