package moon_lander;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.security.DrbgParameters;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Object {
    public int x;
    public int y;


    private BufferedImage ObjectImg;
    public int ObjectWidth, ObjectHeight;

    private Random random;
    public void moveleft(){
        if((Framework.normal==true)&&(Framework.hard==false)) {
            x -= 7.5;
        }
        if((Framework.hard==true)&&(Framework.normal==true)){
            x-=15;
        }


    }
    public Object(){
        Initialize();
        LoadContent();
    }


    public void Initialize(){
        ResetObject();
    }
    public void ResetObject(){
        random = new Random();
        x = Framework.frameWidth - ObjectWidth;
        y = random.nextInt(Framework.frameHeight - ObjectHeight-150);
    }
    public void LoadContent(){
        try {
            URL ObjectImgUrl = this.getClass().getResource("/resources/images/rock.png");
            ObjectImg = ImageIO.read(ObjectImgUrl);
            ObjectWidth = ObjectImg.getWidth();
            ObjectHeight = ObjectImg.getHeight();

        } catch (IOException ex) {
            Logger.getLogger(LandingArea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Draw(Graphics2D g2d)
    {
        g2d.setColor(Color.white);
        g2d.drawImage(ObjectImg, x, y, null);
    }


}
