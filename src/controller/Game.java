/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.javafx.scene.traversal.Direction;
import java.util.ArrayList;
import java.util.Observable;
import javafx.geometry.Point2D;
import model.Corridor;
import model.Corridor.CandyType;
import model.Entity;
import model.Monster;
import model.MonsterDoor;
import model.Pacman;
import model.Tile;
import model.Wall;
import static util.Direction.*;

/**
 *
 * @author Najib EL KHADIR
 */
public class Game extends Observable {

    private final int dimension;
    private Pacman pac;
    private Tile[][] board;
    private boolean running;
    private int score;
    private ArrayList<Monster> monsters;

    public Game() {
        this.running = false;
        this.dimension = 21;
        this.score = 0;
        this.initBoard();
    }

    public void start() {
        this.running = true;
        this.monsters.forEach((m) -> {
            m.start();
        });
        this.pac.start();
    }

    public void stop() {
        this.monsters.forEach((m) -> {
            m.stop();
        });
        this.pac.stop();
        this.running = false;
    }

    public void initBoard() {
        this.monsters = new ArrayList();
        this.board = new Tile[this.dimension][this.dimension];

        String[] monsterSpriteIds;
        monsterSpriteIds = new String[]{"bashful.png", "pokey.png", "shadow.png", "speedy.png"};
        int monsterInc = 0;

        String[] boardData = new String[]{
            "011111111111111111110",
            "01CCCCCCCC1CCCCCCCC10",
            "01S11C111C1C111C11S10",
            "01CCCCCCCC1CCCCCCCC10",
            "01C11C1C11111C1C11C10",
            "01CCCC1CCC1CCC1CCCC10",
            "01111C111010111C11110",
            "00001C1000M0001C10000",
            "11111C1011D1101C11111",
            "00000C001MMM100C00000",
            "11111C101111101C11111",
            "00001C100000001C10000",
            "01111C101111101C11110",
            "01CCCCCCCC1CCCCCCCC10",
            "01C11C111C1C111C11C10",
            "01SC1CCCCCPCCCCC1CS10",
            "011C1C1C11111C1C1C110",
            "01CCCC1CCC1CCC1CCCC10",
            "01C111111C1C111111C10",
            "01CCCCCCCCCCCCCCCCC10",
            "011111111111111111110"

        };

        for (int y = 0; y < 21; y++) {
            String lineData = boardData[y];
            for (int x = 0; x < 21; x++) {
                char c = lineData.charAt(x);
                Point2D coords = new Point2D(x, y);
                switch (c) {
                    case '0':
                        this.board[x][y] = new Corridor(coords, this);
                        break;
                    case '1':
                        this.board[x][y] = new Wall(coords);
                        break;
                    case 'C':
                        this.board[x][y] = new Corridor(coords, this, CandyType.NORMAL);
                        break;
                    case 'S':
                        this.board[x][y] = new Corridor(coords, this, CandyType.SUPER);
                        break;
                    case 'M':
                        String monsterSpriteId = monsterSpriteIds[monsterInc];
                        monsterInc++;
                        Monster monster = new Monster(UP, coords, monsterSpriteId, this, 350 + 50 * monsterInc);
                        this.monsters.add(monster);
                        this.board[x][y] = new Corridor(coords, this, monster);
                        break;
                    case 'P':
                        this.pac = new Pacman(UP, coords, "pacman.png", this, 300);
                        this.board[x][y] = new Corridor(coords, this, this.pac);
                        break;
                    case 'D':
                        this.board[x][y] = new MonsterDoor(coords, this, UP);
                        break;
                }
            }
        }

    }

    public int getDimension() {
        return this.dimension;
    }

    public Tile[][] getBoard() {
        return this.board;
    }

    public ArrayList<Monster> getGhosts() {
        return this.monsters;
    }

    public Pacman getPacMan() {
        return this.pac;
    }
    
    public boolean isFinished() {
        return !this.running;
    }

    public Point2D getNextCoords(Point2D coords, Direction direction) {
        switch (direction) {
            case UP:
                return coords.add(0, -1);
            case DOWN:
                return coords.add(0, 1);
            case LEFT:
                return coords.add(-1, 0);
            case RIGHT:
                return coords.add(1, 0);
            default:
                return coords;
        }
    }
    
    public Tile getTileByCoords(Point2D coords) {
        return this.board[(int) coords.getX()][(int) coords.getY()];
    }
    
    
    public void addScore(int score) {
        this.score += score;
    }
    
    public int getScore() {
        return this.score;
    }
    
    public void move(Entity e, Direction d) {

    }
    
    //// too resume

}
