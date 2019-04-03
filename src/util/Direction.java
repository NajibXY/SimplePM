/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javafx.scene.input.KeyCode;


/**
 *
 * @author Najib EL KHADIR
 */
public enum Direction {
    LEFT, UP, RIGHT, DOWN;

    public static Direction get(KeyCode code) {
        switch (code) {
            case LEFT:
                return LEFT;
            case UP:
                return UP;
            case RIGHT:
                return RIGHT;
            case DOWN:
                return DOWN;
        }
        return null;
    }

    public Direction getOpposed() {
        switch (this) {
            case LEFT:
                return RIGHT;
            case UP:
                return DOWN;
            case RIGHT:
                return LEFT;
            case DOWN:
                return UP;
        }
        return null;
    }
}