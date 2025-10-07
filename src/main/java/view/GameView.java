package view;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameView extends JPanel implements IView, GameListener {
    private final GameState model;

    private final PlayerSprite playerSprite = new PlayerSprite(0, 0);
    private final EnemySprite enemySprite = new EnemySprite(0, 0);
    private final BackgroundSprite background = new BackgroundSprite(0, 0);
    private final GroundSprite ground = new GroundSprite(0, 0);

    //for moving the player up or down
    private static final int PLAYER_Y = 390;

    //constructor
    public GameView(GameState model) {
        //stores model
        this.model = model;
        //sets panel's fixed size
        setPreferredSize(new Dimension(model.worldWidth, model.worldHeight));
        setBackground(Color.BLACK);
        //so model can ask view to repaint
        model.addListener(this);

        //set player Y once
        model.player.setY(PLAYER_Y);
        model.player.setGroundY(PLAYER_Y);
    }

    @Override
    public void showView() {
        //creates window
        JFrame f = new JFrame("2D Runner");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(this);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setResizable(false); // fixed size
        f.setVisible(true);
    }

    @Override
    public void refresh() { repaint(); }

    @Override
    public void onModelChanged() { repaint(); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth(), h = getHeight();
        int groundY = getHeight() - model.groundHeight;

        Graphics2D g2 = (Graphics2D) g;

        //gets background
        BufferedImage bg = background.getImage();
        int bgW = bg.getWidth();
        //wraps offset so tiling is seamless
        int sx = Math.floorMod(model.getScrollX(), bgW);
        //loops across screen
        for (int x = -sx; x < w; x += bgW) {
            g2.drawImage(bg, x, 0, bgW, h, null);
        }

        //draws dirt at groundY
        BufferedImage dirt = ground.getImage();
        int gw = dirt.getWidth();
        int s2 = Math.floorMod(model.getScrollX(), gw);
        for (int x = -s2; x < w; x += gw) {
            g2.drawImage(dirt, x, groundY, gw, model.groundHeight, null);
        }

        /* FOR DEBUGGING PURPOSES
        //debug hitboxes
        g2.setColor(Color.green);
        g2.drawRect(model.player.getX(), model.player.getY(), 60, 60);
        for (WolfEnemy wlf : model.wolves){
            g2.drawRect(wlf.getX(), wlf.getY(), 64, 64);
        }
        */


        //reads players current position from model and draws image there
        playerSprite.x = model.player.getX();
        playerSprite.y = model.player.getY();
        playerSprite.draw(g2);

        for (WolfEnemy wlf : model.wolves) {
            enemySprite.draw(g2, wlf.getX(), wlf.getY());
        }

        //hud
        g2.setColor(Color.white);
        g2.setFont(getFont().deriveFont(Font.BOLD, 16f));
        String timeStr = String.format("Time: %.2fs", model.getRunSeconds());
        String bestStr = String.format("Best: %.2fs", model.getBestSeconds());
        String livesStr = "Lives:" + model.getLives();
        g2.drawString(timeStr, 12, 24);
        g2.drawString(bestStr, 12, 44);
        g2.drawString(livesStr, 12, 64);

        //for when game starts, to display message press space to start
        if (model.isReady()){
            g2.setColor(new Color(0,0,0,140));
            g2.fillRect(0,0,w,h);
            g2.setColor(Color.white);
            g2.setFont(getFont().deriveFont(Font.BOLD, 28f));
            String msg = "Press SPACE to start";
            int strW = g2.getFontMetrics().stringWidth(msg);
            int strH = g2.getFontMetrics().getAscent();
            g2.drawString(msg, (w - strW) / 2, (h-strH) / 2);
        }else if (model.isGameOver()){
            g2.setColor(new Color(0, 0, 0, 160));
            g2.fillRect(0,0,w,h);
            g2.setColor(Color.white);
            g2.setFont(getFont().deriveFont(Font.BOLD, 28f));
            String over = "Game Over";
            String run = String.format("Time: %.2fs", model.getRunSeconds());
            String best = String.format("Best: %.2fs", model.getBestSeconds());
            String again = "Press SPACE to try again";
            int y = h/2 -30;
            for (String s :new String[] {over, run, best, again}){
                int sw = g2.getFontMetrics().stringWidth(s);
                int sa = g2.getFontMetrics().getAscent();
                g2.drawString(s, (w-sw) /2, y);
                y += sa + 12;
            }
        }
    }
}

