package view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class EnemySprite extends Sprite {
    public EnemySprite(int x, int y) { super(x, y); loadImage(); }

    @Override
    public void loadImage() {
        URL url = EnemySprite.class.getResource("/sprites/wolf.png");
        if (url == null) {
            throw new IllegalStateException("Missing resource: /sprites/wolf.png");
        }
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException("Could not load enemy sprite", e);
        }
    }

    //
    public void draw(Graphics2D g, int x, int y) {
        int w = image.getWidth();
        int h = image.getHeight();
        g.drawImage(image, x+w, y, -w, h, null);
    }
}

