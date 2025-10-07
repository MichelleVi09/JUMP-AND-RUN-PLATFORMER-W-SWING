package view;

import javax.imageio.ImageIO;
import java.io.IOException;

public class GroundSprite extends Sprite {
    public GroundSprite(int x, int y) { super(x, y); loadImage(); }

    @Override
    public void loadImage() {
        try {
            image = ImageIO.read(getClass().getResource("/sprites/cat_sit-5.png.png"));
        } catch (IOException e) {
            throw new RuntimeException("Could not load ground", e);
        }
    }
}
