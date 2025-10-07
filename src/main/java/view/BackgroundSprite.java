package view;

import javax.imageio.ImageIO;
import java.io.IOException;

public class BackgroundSprite extends Sprite {
    public BackgroundSprite(int x, int y) { super(x, y); loadImage(); }

    @Override
    public void loadImage() {
        try {
            image = ImageIO.read(getClass().getResource("/sprites/New Piskel-1.png.png"));
        } catch (IOException e) {
            throw new RuntimeException("Could not load background", e);
        }
    }
}

