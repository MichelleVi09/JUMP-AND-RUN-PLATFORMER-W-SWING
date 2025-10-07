# 2D Jump-And_Run Platformer using Swing
## Team Members
- Michelle Villagomez
- Mira Bhakta

  ## How to Run
1. Clone repository:
   ```bash
   git clone https://github.com/MichelleVi09/JUMP-AND-RUN-PLATFORMER-W-SWING.git
   cd JUMP-AND-RUN-PLATFORMER-W-SWING
2. Open in IntelliJ IDEA or Eclipse
3. Make sure JDK 17+ is configured in your IDE
4. Run 'Main.java' from the src/main/java folger

## Features Implemented
- **Player characters:**  
  Cat sprite for the main player, wolf sprites as enemies, plus background and ground sprites for the environment.

- **Physics system:**  
  Simple gravity system with vertical velocity (`velY`) and jump impulse. Player automatically falls back to the ground and can only jump when grounded.

- **Collision detection:**  
  Axis-aligned bounding box (AABB) rectangles used to check overlap between player and wolves. Collisions reduce lives and eventually trigger the Game Over state.

- **Restart function:**  
  Pressing **SPACE** while in the Game Over state resets the game back to Ready, clears wolves, resets timer and lives, and waits for another start.

- **Game states:**  
  READY (overlay message “Press SPACE to start”), RUNNING (gameplay active, timer counting), GAME OVER (overlay with time survived and best record).

- **Scrolling world:**  
  Background and ground move to the left to simulate forward motion. Wolves spawn on the right and move left.

- **HUD (Heads-Up Display):**  
  Displays current run time, best time, and remaining lives at the top left corner of the screen.

- **Difficulty scaling:**  
  As time increases, wolves spawn more frequently and scrolling can speed up, making the game harder the longer you survive.

## Controls
- **SPACE** – Start, Jump, Restart
- **W** – Jump (alternative key)
  
## Known Issues
- No known issues
  
## External Libraries
- Everything runs with just the standard JDK
