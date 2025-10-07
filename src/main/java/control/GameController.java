package control;
import model.*;
import view.*;
import javax.swing.*;

public class GameController {
    //handles game logic
    private final GameState model = new GameState();
    //handles drawing of game
    private final GameView view = new GameView(model);
    private final Timer timer;

    public GameController() {
        //for receiving keyboard input
        view.setFocusable(true);
        //listens for spacebar, when it finds it, it'll make player jump
        view.addKeyListener(new InputHandler(model));
        timer = new Timer(1000 / 60, e -> model.tick());
    }

    public void show() {
        int WIDTH = 960, HEIGHT = 540; //fixed window
        //creates main window
        JFrame f = new JFrame("2D Runner");
        //to close when exit
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(view);
        //sizes window to width and height
        view.setPreferredSize(new java.awt.Dimension(WIDTH, HEIGHT));
        f.pack();
        //disables resizing
        f.setResizable(false);
        //creates window
        f.setLocationRelativeTo(null);
        //shows window
        f.setVisible(true);
        timer.start();
    }
}



