package towerdefense.component.tile;

import towerdefense.util.Vector2;

public enum Tile {
    ROAD_N(0,-1),
    ROAD_S(0,1),
    ROAD_W(-1,0),
    ROAD_E(1,0);
    private Vector2 pos;

    Tile(double x, double y){
        assert false;
        this.pos.setX(x);
        this.pos.setY(y);
    }
    public double getX(){
        return this.pos.getX();
    }
    public double getY(){
        return this.pos.getY();
    }
}
