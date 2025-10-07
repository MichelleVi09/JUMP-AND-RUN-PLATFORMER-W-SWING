package model;

import java.util.*;

public class GameState {
    //game area size
    public int worldWidth = 960;
    public int worldHeight = 540;
    //how tall ground is
    public int groundHeight = 700;
    //counts how many ticks have passed
    private int tickCount = 0;


    //instance of MainPlayer
    public final MainPlayer player = new MainPlayer();
    //list of wolves
    public final List<WolfEnemy> wolves = new ArrayList<>();
    //random source for spawning
    private final Random rng = new Random();
    private int spawnCooldown = 0;
    private double spawnChance = 0.04;


    //scroll offset for background and ground tiling
    private int scrollX = 0;
    //how fast world scrolls
    private static final int SCROLL_SPEED = 6;

    //y position for wolf
    public int WOLF_Y = 370;

    //lives
    private int lives = 3;
    private int runTicks = 0; //ticks for current run
    private int bestTicks = 0; //highscore

    //used for starting the game
    public enum Phase {READY, RUNNING, GAME_OVER}
    private Phase phase = Phase.READY;

    //hitbox sizes
    private static final int PLAYER_W = 40;
    private static final int PLAYER_H = 40;
    private static final int WOLF_W = 64;
    private static final int WOLF_H = 64;

    //listeners
    private final List<GameListener> listeners = new ArrayList<>();
    public void addListener(GameListener l) { listeners.add(l); }
    //calls onModelChanged()
    private void notifyListeners() { for (var l : listeners) l.onModelChanged(); }


    //for getting what phase scene is in
    public boolean isReady() {return phase == Phase.READY;}
    public boolean isRunning() {return phase == Phase.RUNNING;}
    public boolean isGameOver() {return phase == Phase.GAME_OVER;}
    public void start() {phase = Phase.RUNNING;}
    public void resetToReady(){
        phase = Phase.READY;
        lives = 3;
        runTicks = 0;
        wolves.clear();
        scrollX = 0;
        spawnCooldown = 0;
    }

    public int getLives() {return  lives;}
    public double getRunSeconds() {return runTicks / 60.0;}
    public double getBestSeconds() {return bestTicks / 60.0;}
    public int getScrollX() { return scrollX; }

    public void tick() {
        if (!isRunning()){
            //tells listeners model changed
            notifyListeners();
            return;
        }
        //timer
        runTicks++;
        tickCount++;

        //after 60 seconds, itll make it harder and faster
        if (tickCount % 3600 == 0){
            spawnChance = Math.min(0.20, spawnChance + 0.01);
        }
        //scrolls left slightly
        scrollX -= SCROLL_SPEED;

        //create wolf
        if (spawnCooldown <= 0) {
            if (rng.nextDouble() < 0.04) {
                int startX = worldWidth + 50;
                wolves.add(new WolfEnemy(startX, WOLF_Y));
                spawnCooldown = 30;
            }
        } else {
            spawnCooldown--;
        }

        //applies gravity or jump to player
        player.update();

        //spawn wolves
        for (Iterator<WolfEnemy> it = wolves.iterator(); it.hasNext();) {
            WolfEnemy w = it.next();
            //for each wolf, moves left
            w.update();
            if (w.isOffscreen()) {it.remove(); continue;}//make wolves disappear

            //collisions
            int px = player.getX();
            int py = player.getY();
            int wx = w.getX();
            int wy = w.getY();


            boolean overlap = px < wx + WOLF_W && px + PLAYER_W > wx && py < wy + WOLF_H && py +PLAYER_H > wy;
            if (overlap) {
                it.remove(); //remove wolf that collided
                lives--;
                if (lives <=0){
                    if (runTicks > bestTicks) bestTicks = runTicks; //updating record
                    phase = Phase.GAME_OVER;
                    break;
                }
            }
        }

        notifyListeners();
    }
}

