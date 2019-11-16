package towerdefense.component;

public class CommonFunc {
    public static final String GAME_NAME = "Tower Defense";
    public static final long TILE_SIZE = 64;
    private static final long TILE_HORIZONTAL = 20;
    private static final long TILE_VERTICAL = 12;

    public static final long UI_HORIZONTAL = 300;

    public static final long GAME_WIDTH = TILE_SIZE*TILE_HORIZONTAL;
    public static final long GAME_HEIGHT = TILE_SIZE*TILE_VERTICAL;

    public static final double NS_IN_SEC = 1000000000.0;
    public static final int MAP_TILE = (int) (TILE_VERTICAL*TILE_HORIZONTAL);
    public static final long CANVAS_WIDTH = TILE_SIZE * TILE_HORIZONTAL + UI_HORIZONTAL;
    public static final long CANVAS_HEIGHT = TILE_SIZE * TILE_VERTICAL;
//    private final int TILE_SIZE = 20;
    private final double SHOT_FADE_TIME =1.;

    //UI CONTROL
    public static volatile int UI_CUR = 1;
    public static final int UI_START = 1;
    public static final int UI_STAGE_CHOOSING = 2;
    public static final int UI_PLAYING = 3;
    public static final int UI_PAUSE = 4;
    public static final int UI_GAME_COMPLETE = 5;



    public static final int UI_BASE = 0;
}
