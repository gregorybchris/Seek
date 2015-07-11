# Seek
Machine Learning Project

In this project I aim to store data about user keyboard movement and use patters in that motion to predict the next motion they will make. Because of variability in movement more data will be stored than just the most probable next move. In addition, probabilistic algorithms have the possibility of choosing a less than optimal path that the user might take. This randomness will allow a heatmap behavior that could be used to direct a sort of intelligent swarm system.

Various constants may be adjusted to fit different circumstances:

```java
~ ~ Width and height of screen in pixels ~ ~
WIDTH = 600;
HEIGHT = 600;

~ ~ Rows and columns to divide the screen into processing cells ~ ~
ROWS = 30;
COLS = 20;

~ ~ The number of directions of travel possible ~ ~
NUM_MOVEMENTS = 5;

~ ~ The number of most probable movements kept ready to access ~ ~
NUM_TOP_VALUES = 3;

~ ~ Determines how aggressively the algorithm learns ~ ~
ETA = 0.07;
```

These circumstances are induced when screen setups present the user with a spatially related goal. When the user attempts to achieve that spatially related goal somewhat obvious patterns of movement are implied and subsequently followed. These patterns are what the computer will learn to predict.