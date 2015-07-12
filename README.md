# Seek
Machine Learning Project

In this project I store data about user keyboard movement and use patters in that motion to predict the next motion they will make. Because of variability in movement more data is stored than just the most probable next move. In addition, probabilistic algorithms have the possibility of choosing a less than optimal path that the user might take. This randomness allows for a heatmap-like behavior that is used to direct a learning swarm system.

Various constants may be adjusted to fit different circumstances:

```java
~ ~ Rows and columns to divide the screen into processing cells ~ ~
public static final int ROWS = 30;
public static final int COLS = 20;

~ ~ The number of directions of travel possible ~ ~
public static final int NUM_MOVEMENTS = 5;

~ ~ The number of most probable movements to track ~ ~
public static final int NUM_TOP_VALUES = 3;

~ ~ Determines how aggressively the algorithm learns ~ ~
public static final double ETA = 0.07;
```

These circumstances are induced when screen setups present the user with a spatially related goal. When the user attempts to achieve that spatially related goal somewhat obvious patterns of movement are implied and subsequently followed. These patterns are what the computer learns to predict.