/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.geometry.Point2D;

/**
 *
 * @author Najib EL KHADIR
 */
public class Tile {
    protected final Point2D coords;
    
    public Tile(Point2D coords) {
        this.coords = coords;
    }
    
    public Point2D getCoords() {
        return this.coords;
    }
}
