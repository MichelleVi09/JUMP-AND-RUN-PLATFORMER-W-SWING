package control;
import model.GameState;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {
    private final GameState model;

    public InputHandler(GameState model) {
        this.model = model;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //getting input from keyboard
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_SPACE || c == KeyEvent.VK_W) {
            if (model.isReady()) {
                model.start();      //first press starts
            } else if (model.isRunning()){
                model.player.jump(); //then jump
            }else {
                model.resetToReady(); //reset back to ready
            }
        }
    }
}
