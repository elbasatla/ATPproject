package ImageFactory;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class NostalgicSkinImages extends ImageFactory {

    public NostalgicSkinImages(String skin_name) {
        super(skin_name);
    }

    @Override
    public Image getWallImage(){
        Image ans = null ;
        try {
            ans = new Image(new FileInputStream("resources/Images/wall_"+this.skin_name+"_skin.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ans;
    }
}
