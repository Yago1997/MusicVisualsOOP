///Yago Lacerda C19481114
package city_scape;

import java.io.InputStream;
import city_scape.Main;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import javazoom.jl.player.Player;

public class Music extends Thread
{
    private static Player player;
    private boolean isLoop;
    private File file;
    private FileInputStream fis;
    private BufferedInputStream bis;
    
    public Music(String name, boolean isLoop) {
        try {
            file = new File(Main.class.getResource("../music/" + name).toURI());
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            player = new Player((InputStream)bis);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
 
    
    public static int getTime() {
        if (player == null) {
            return 0;
        }
        return player.getPosition();
    }
    
    public void close() {
        isLoop = false;
        player.close();
        interrupt();
    }
    
    @Override
    public void run() {
        try {
            do {
                Music.player.play();
                this.fis = new FileInputStream(this.file);
                this.bis = new BufferedInputStream(this.fis);
                this.player = new Player((InputStream)this.bis);
            } while (this.isLoop);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}