package model;

public class WolfEnemy {
    private int x;              //current x
    private final int y;        //fixed vertical position
    private static final int SPEED = 6; //how fast it moves

    public WolfEnemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //moves wolf left
    public void update() { x -= SPEED; }

    //returns true when wolf has passed left edge
    public boolean isOffscreen() { return x < -150; }

    public int getX() { return x; }
    public int getY() { return y; }

}

