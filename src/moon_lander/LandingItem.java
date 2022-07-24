package moon_lander;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LandingItem {

    public int x;
    public int y;

    private BufferedImage LandingImg;

    public int LandingImg_Width;
    public int LandingImg_Height;

    private Random random;

    public LandingItem() {
        Initialize();
        LoadContent();
    }

    public void Initialize() {
        random=new Random();
        ResetItem();
    }

    public void ResetItem(){
        x= random.nextInt(Framework.frameWidth-LandingImg_Width);
        y= random.nextInt(Framework.frameHeight-LandingImg_Height-100);
    }
    public void LoadContent(){

        try {
            URL LandingImgUrl = this.getClass().getResource("/resources/images/rocket_landing.png");
            LandingImg = ImageIO.read(LandingImgUrl);
            LandingImg_Width = LandingImg.getWidth();
            LandingImg_Height = LandingImg.getHeight();
        } catch (IOException ex) {
            Logger.getLogger(LandingArea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

public void Draw(Graphics2D g2d)
{
    g2d.setColor(Color.white);
    g2d.drawString("Landingitem"+x+";"+y,10,80);
    g2d.drawImage(LandingImg,x,y,null);
}




}
