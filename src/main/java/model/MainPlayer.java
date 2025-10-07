package model;

public class MainPlayer implements Character {
    private final int fixedX = 120; //x position locked
    private int y; // players vertical position
    private int velY; //velocity speed
    private boolean isJumping; //prevents double jumping

    private int groundY = 475; //ground height, where player should land
    private final int gravity = 1; //how fast player falls
    private final int jumpImpulse = -18;//how far up player goes when jumping


    @Override
    public void jump() { //only jumps if not already in air
        if (!isJumping) {
            isJumping = true;
            velY = jumpImpulse; //makes player move upward
        }
    }

    @Override
    public void update() {
        velY += gravity; //pulls cat down
        y += velY; //aplies velocity to y position

        if (y >= groundY) { // if player has landed
            y = groundY;
            velY = 0;//reset velocity
            isJumping = false; //reset so player can jump
        }
    }

    public int getX() { return fixedX; }
    public int getY() { return y; }
    public void setY(int y) {this.y = y;}
    public void setGroundY(int g) { this.groundY = g; }
}
