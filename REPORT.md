# Project Report
- **Main Goal**: Replicate Google's T-Rex Dinosaur game in our own spin-off. 
## Design Decisions

### Architecture
We used an **MVC (Model–View–Controller)** pattern:  
- **Model**: Contains `GameState`, `MainPlayer`, `WolfEnemy`, and `GameListener`. Stores game data and logic (physics, enemy spawning, collisions, lives, timing).  
- **View**: `GameView` and sprite classes. Draws the player, wolves, background, ground, HUD, and overlay messages using Swing’s `paintComponent`.  
- **Controller**: `GameController` and `InputHandler`. Connects keyboard input (SPACE/W to start, jump, restart) to model actions, and runs the game loop with a Swing `Timer`.  

We chose **Swing** instead of JavaFX because Swing is lightweight, built into the JDK, and sufficient for simple 2D rendering and input.

### Data Structures
- **Game state**: Objects (`GameState`, `MainPlayer`, `WolfEnemy`) with fields for positions, velocities, lives, timers.  
- **Enemies**: Stored in an `ArrayList<WolfEnemy>`, updated each frame, and cleaned with an `Iterator`.  
- **Phase/State**: Enum (`READY`, `RUNNING`, `GAME_OVER`) for clear state transitions.  

These structures kept the code simple, readable, and extensible.

### Algorithms
- **Physics**: Player jump and gravity use velocity updates each tick (`velY += gravity`).  
- **Scrolling**: Background and ground scroll left using `scrollX` and modulus tiling.  
- **Enemy spawning**: Random chance (`rng.nextDouble() < spawnChance`) with cooldown ensures unpredictability.  
- **Collision detection**: Axis-aligned bounding box (AABB) checks between player and wolves. O(n) per frame, where n = wolves on screen.  
- **Complexity**: Effectively constant-time per frame since n stays small.

## Challenges Faced
1. **Keyboard input not firing**  
   - **Solution:** Ensured the view is focusable (`setFocusable(true)`) and requested focus after showing the frame.  

2. **Collision detection not working**  
   - **Solution:** Fixed bug using `w.getY()` instead of `w.getX()`. Adjusted hitbox sizes to match sprite dimensions and drew debug rectangles.  

3. **Wolves not spawning**  
   - **Solution:** Corrected spawn cooldown logic so wolves could spawn consistently.

## What We Learned
- Reinforced OOP concepts and MVC design.  
- Debugged graphics by drawing hitboxes and printing values.  
- Gained experience with Swing `Timer` and event-driven programming.
- Overall, gained familiarity with Swing. 

## If We Had More Time
- Add sprite animations (running/jumping).  
- Implement sound effects and music.  
- Save high scores with file I/O.  
- Add more obstacle types (pits, taller enemies).  
- Refactor collision into a `Collider` helper class.  
- Improve rendering structure for readability and performance.
