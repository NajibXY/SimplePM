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
public class Pacman extends Entity{

    public Pacman(Direction currentDirection, Game game) {
        super(currentDirection, game);
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
