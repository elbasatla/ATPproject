package StageUtills;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

public class Sounds {
    private static Sounds ourInstance = new Sounds();

    private MediaPlayer btn_click , background_music , ingame_music;
    private final String BACKGROUND_MUSIC_PATH = "resources\\Sound\\cheerful_background.mp3";
    private final String IN_GAME_MUSIC_PATH = "resources\\Sound\\playfull_forgame.mp3";
    private final String BTN_CLICK_PATH = "resources\\Sound\\click.mp3";

    public static Sounds getInstance() {
        return ourInstance;
    }

    private Sounds() {
        Media click_media = new Media(Paths.get(BTN_CLICK_PATH).toUri().toString());
        Media ingame_media = new Media(Paths.get(IN_GAME_MUSIC_PATH).toUri().toString());
        Media background_media = new Media(Paths.get(BACKGROUND_MUSIC_PATH).toUri().toString());
        this.btn_click = new MediaPlayer(click_media);
        this.ingame_music = new MediaPlayer(ingame_media);
        this.background_music = new MediaPlayer(background_media);

        this.btn_click.setVolume(1);
        this.background_music.setVolume(1);
        this.ingame_music.setVolume(1);
    }


    //region Requests
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

