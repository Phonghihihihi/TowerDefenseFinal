package towerdefense.util;

public class Vector2 {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Vector2(double x, double y){
        this.setX(x);
        this.setY(y);
    }

    public Vector2(Vector2 other){
        this.setX(other.x);
        this.setY(other.y);
    }

    public Vector2 minus(Vector2 other){
        return new Vector2(this.getX()-other.getX(),this.getY()-other.getY());
    }

    public double distancTo(Vector2 other){
        return Math.sqrt((this.getX()-other.getX())*(this.getX()-other.getX())
                        + (this.getY()-other.getY())*(this.getY()-other.getY()));
    }

    public void nomalize(){
        double l = x*x + y*y;
        x = x/Math.sqrt(l);
        y = y/Math.sqrt(l);
    }
}
