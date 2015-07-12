# Seek
Machine Learning Project

## Description

In this project I store data about user keyboard input and use patters in that input to predict the next movement the user will make. Due to variability in movement more data is stored than just the most probable next move. In addition, probabilistic algorithms have the possibility of choosing a less than optimal path that the user might take. This randomness allows for a more swarm-like behavior.

## Running the Project

To run the project in the console use the provided run file:

```bash
./run
```

This will compile the java files, run the graphics for the program, and then clean up the compile files after program termination.

## Adjusting Settings

Constant values may be adjusted to fit different circumstances:

```java
~ ~ Determines how aggressively the algorithm learns ~ ~
public static final double ETA = 0.07;

~ ~ Rows and columns to divide the screen into processing cells ~ ~
public static final int ROWS = 30;
public static final int COLS = 20;

~ ~ The number of most probable movements to track ~ ~
public static final int NUM_TOP_VALUES = 3;
```

These circumstances are induced when screen setups present the user with a spatially related goal. When the user attempts to achieve that spatially related goal somewhat obvious patterns of movement are implied and subsequently followed. These patterns are what the computer learns to predict.