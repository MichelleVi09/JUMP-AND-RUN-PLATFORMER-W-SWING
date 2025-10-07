package view;

import javax.imageio.ImageIO;
import java.io.IOException;

public class PlayerSprite extends Sprite {

    public PlayerSprite(int x, int y) { super(x, y); loadImage(); }

    @Override
    public void loadImage() {
        try {
            image = ImageIO.read(getClass().getResource("/sprites/cat_stand.png"));
        } catch (IOException e) {
            throw new RuntimeException("Could not load player sprite", e);
        }
    }
}

