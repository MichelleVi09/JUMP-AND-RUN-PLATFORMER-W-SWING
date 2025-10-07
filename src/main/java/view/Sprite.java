package view;
import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class Sprite {
    protected BufferedImage image;
    public int x, y;

    public Sprite(int x, int y) { this.x = x; this.y = y; }
    public abstract void loadImage();

    public void draw(Graphics2D g) { g.drawImage(image, x, y, null); }
    public BufferedImage getImage(){ return image; }
}
