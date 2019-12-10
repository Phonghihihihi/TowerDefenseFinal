package towerdefense.component;

import java.util.Enumeration;

public final class GameConfig {
    public static final String GAME_NAME = "Tower Defense";
    public static final int TILE_SIZE = 64;
    public static final long TILE_HORIZONTAL = 18;
    public static final long TILE_VERTICAL = 12;

    public static final long UI_HORIZONTAL = 200;

    public static final long GAME_WIDTH = TILE_SIZE*TILE_HORIZONTAL;
    public static final long GAME_HEIGHT = TILE_SIZE*TILE_VERTICAL;

    public static final double PI_TO_DEGREE = 180/Math.PI;
    public static final double NS_IN_SEC = 1000000000.0;
    public static final int MAP_TILE = (int) (TILE_VERTICAL*TILE_HORIZONTAL);
    public static final long CANVAS_WIDTH = TILE_SIZE * TILE_HORIZONTAL + UI_HORIZONTAL;
    public static final long CANVAS_HEIGHT = TILE_SIZE * TILE_VERTICAL;


    public static final long START_MONEY = 500;

    public static final long START_HEALTH = 100;
  
    public static final double SPAWN_X = 64 + GameConfig.TILE_SIZE/2.0;
    public static final double SPAWN_Y = 640 + GameConfig.TILE_SIZE/2.0;
    public static final int SPAWN_RATE = 120;

    //Enemy Config

    //Normal
    public static final int NORMAL_ENEMY_SPEED = 2;
    public static final int NORMAL_ENEMY_ARMOR = 1;
    public static final int NORMAL_ENEMY_HEALTH = 80;
    public static final int NORMAL_ENEMY_REWARD = 30;
    public static final int NORMAL_ENEMY_WAVE_NUMBER = 5;
    public static final double NORMAL_ENEMY_WIDTH = 20;
    public static final double NORMAL_ENEMY_HEIGHT = 28;
    public static final String NORMAL_ENEMY_IMAGE_URL = "file:src/Assets/Enemy/Normal.png";

    //Smaller
    public static final int SMALLER_ENEMY_SPEED = 4;
    public static final int SMALLER_ENEMY_ARMOR = 1;
    public static final int SMALLER_ENEMY_HEALTH = 60;
    public static final int SMALLER_ENEMY_REWARD = 30;
    public static final int SMALLER_ENEMY_WAVE_NUMBER = 3;
    public static final double SMALLER_ENEMY_WIDTH = 18;
    public static final double SMALLER_ENEMY_HEIGHT = 28;
    public static final String SMALLER_ENEMY_IMAGE_URL = "file:src/Assets/Enemy/Smaller.png";

    //Tanker
    public static final int TANKER_ENEMY_SPEED = 1;
    public static final int TANKER_ENEMY_ARMOR = 20;
    public static final int TANKER_ENEMY_HEALTH = 100;
    public static final int TANKER_ENEMY_REWARD = 100;
    public static final int TANKER_ENEMY_WAVE_NUMBER = 3;
    public static final double TANKER_ENEMY_WIDTH = 53;
    public static final double TANKER_ENEMY_HEIGHT = 36;
    public static final String TANKER_ENEMY_URL = "file:src/Assets/Enemy/Tanker.png";

    //Boss
    public static final int BOSS_ENEMY_SPEED = 1;
    public static final int BOSS_ENEMY_ARMOR = 100;
    public static final int BOSS_ENEMY_HEALTH = 200;
    public static final int BOSS_ENEMY_REWARD = 1000;
    public static final int BOSS_ENEMY_WAVE_NUMBER = 1;
    public static final double BOSS_ENEMY_WIDTH = 1;
    public static final double BOSS_ENEMY_HEIGHT = 1;
    public static final String BOSS_ENEMY_URL = "file:src/Assets/Enemy/Boss.png";

    //Tower config

    //Normal Tower
    public static final int NORMAL_TOWER_PRICE = 20;
    public static final double NORMAL_TOWER_DAMAGE = 10;
    public static final double NORMAL_TOWER_FIRESPEED = 10;
    public static final double NORMAL_TOWER_RANGE = 200;

    public static final String NORMAL_TOWER_IMAGE_URL = "file:src/Assets/Tower/Normal.png";

    //Machine gun Tower
    public static final int MACHINE_GUN_TOWER_PRICE = 30;
    public static final double MACHINE_GUN_TOWER_DAMAGE = 5;
    public static final double MACHINE_GUN_TOWER_FIRESPEED = 10;
    public static final double MACHINE_GUN_TOWER_RANGE = 200;
  
    public static final String MACHINE_GUN_TOWER_IMAGE_URL = "file:src/Assets/Tower/Machine Gun.png";

    //Sniper Tower
    public static final int SNIPER_TOWER_PRICE = 50;
    public static final double SNIPER_TOWER_DAMAGE = 100;
    public static final double SNIPER_TOWER_FIRESPEED = 20;
    public static final double SNIPER_TOWER_RANGE = 300;
    public static final String SNIPER_TOWER_IMAGE_URL = "file:src/Assets/Tower/Sniper.png";


    private final double SHOT_FADE_TIME =1.;

    //BULLET
    public int TIME_BULLET = 20;
    public static String MACHINE_GUN_BULLET = "file:src/Assets/Bullet/towerDefense_tile272.png";
    public static String NORMAL_BULLET = "file:src/Assets/Bullet/towerDefense_tile295.png";
    public static String SNIPER_BULLET = "file:src/Assets/Bullet/towerDefense_tile251.png";
    //UI CONTROL
    public static volatile int UI_CUR = 1;

    //Reinforcement
    public static final int PLANE_SPEED = 4;


    public static enum STATUS {
        START, PLAYING, END;
    }
}
