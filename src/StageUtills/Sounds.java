package StageUtills;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class Sounds {
    private static Sounds ourInstance = new Sounds();

    private MediaPlayer nostalgic_music ,gothic_music , btn_click , background_music , ingame_music , wall_1 , wall_2 , wall_3 , movement_1 , save , load;
    private final String BACKGROUND_MUSIC_PATH = "resources\\Sound\\cheerful_background.mp3";
    private final String IN_GAME_MUSIC_PATH = "resources\\Sound\\playfull_forgame.mp3";
    private final String BTN_CLICK_PATH = "resources\\Sound\\click.mp3";
    private final String WALL_SOUND_1 = "resources\\Sound\\wall1_sound.mp4";
    private final String WALL_SOUND_2 = "resources\\Sound\\wall2_sound.mp4";
    private final String WALL_SOUND_3 = "resources\\Sound\\wall3_sound.mp4";
    private final String MOVEMENT_1 = "resources\\Sound\\movement_sound.mp4";
    private final String MOVEMENT_2 = "resources\\Sound\\movement_2.mp4";
    private final String SAVE_SOUND = "resources\\Sound\\save_sound.mp4";
    private final String LOAD_SOUND = "resources\\Sound\\load_sound.mp4";
    private final String GHOTIC_INGAME = "resources\\Sound\\gothic_ingame.mp3";
    private final String NOSTALGIC_INGAME = "resources\\Sound\\nostalgic_ingame.mp3";

    private Media nostalgic_ingame , ghothic_ingame , click_media , ingame_media , wall_1_media , wall_2_media , wall_3_media , background_media , save_media , load_media , movement_1_media;
    public static Sounds getInstance() {
        return ourInstance;
    }

    private Sounds() {
        this.click_media = new Media(Paths.get(BTN_CLICK_PATH).toUri().toString());
        this.ingame_media = new Media(Paths.get(IN_GAME_MUSIC_PATH).toUri().toString());
        this.background_media = new Media(Paths.get(BACKGROUND_MUSIC_PATH).toUri().toString());
        this.wall_1_media = new Media(Paths.get(WALL_SOUND_1).toUri().toString());
        this.wall_2_media = new Media(Paths.get(WALL_SOUND_2).toUri().toString());
        this.wall_3_media = new Media(Paths.get(WALL_SOUND_3).toUri().toString());
        this.save_media = new Media(Paths.get(SAVE_SOUND).toUri().toString());
        this.load_media = new Media(Paths.get(LOAD_SOUND).toUri().toString());
        this.movement_1_media = new Media(Paths.get(MOVEMENT_1).toUri().toString());
        this.ghothic_ingame = new Media(Paths.get(GHOTIC_INGAME).toUri().toString());
        this.nostalgic_ingame = new Media(Paths.get(NOSTALGIC_INGAME).toUri().toString());
        this.nostalgic_music = new MediaPlayer(nostalgic_ingame);
        this.btn_click = new MediaPlayer(click_media);
        this.ingame_music = new MediaPlayer(ingame_media);
        this.background_music = new MediaPlayer(background_media);
        this.gothic_music = new MediaPlayer(ghothic_ingame);

    }

    //region Requests

    public void stopAllIngameMusic(){
        this.nostalgic_music.stop();
        this.gothic_music.stop();
        this.ingame_music.stop();
    }

    public void playNostalgicIngameMusic(){
        this.nostalgic_music.play();
    }

    public void stopNostalgicIngameMusic(){
        this.nostalgic_music.stop();
    }

    public void playGothicIngameMusic(){
        this.gothic_music.play();
    }

    public void stopGothicIngameMusic(){
        this.gothic_music.stop();
    }

    public void playMovement1Sound(){
        this.movement_1 = new MediaPlayer(this.movement_1_media);
        this.movement_1.play();
    }

    public void playSaveSound(){
        this.save = new MediaPlayer(this.save_media);
        this.save.play();
    }

    public void playLoadSound(){
        this.load = new MediaPlayer(this.load_media);
        this.load.play();
    }

    public void playWall1Sound(){
        this.wall_1 = new MediaPlayer(this.wall_1_media);
        this.wall_1.play();
    }

    public void playWall2Sound(){
        this.wall_2 = new MediaPlayer(this.wall_2_media);
        this.wall_2.play();
    }

    public void playWall3Sound(){
        this.wall_3 = new MediaPlayer(this.wall_3_media);
        this.wall_3.play();
    }

    public void playBackgroundMusic(){
        this.background_music.play();
    }

    public void stopBackgroundMusic(){
        this.background_music.stop();
    }

    public void playClickMusic(){
        Media click_media = new Media(Paths.get(BTN_CLICK_PATH).toUri().toString());
        this.btn_click = new MediaPlayer(click_media);
        this.btn_click.play();
    }

    public void playIngameMusic(){
        this.ingame_music.play();
    }

    public void stopIngameMsic(){
        this.ingame_music.stop();
    }

    //endregion

}

