package moon_lander;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Landing area where rocket will land.
 * 
 * @author www.gametutorial.net
 */

public class LandingArea {
    
    /**
     * X coordinate of the landing area.
     */
    public int x;
    public int i=1;

    /**
     * Y coordinate of the landing area.
     */
    public int y;
    
    /**
     * Image of landing area.
     */
    private BufferedImage landingAreaImg;
    private BufferedImage landingLargeAreaImg;
    
    /**
     * Width of landing area.
     */
    public int landingAreaImgWidth;
    public int landingLargeAreaImgWidth;



    private Random random;

    public void landingAreaMove(int n){
        x +=n*i;
    }
    
    
    public LandingArea()
    {
        Initialize();
        LoadContent();
    }

    
    private void Initialize()
    {   
        // X coordinate of the landing area is at 46% frame width.
//        x = (int)(Framework.frameWidth * 0.46);
        x = (int)(Framework.frameWidth*0.46);
        // Y coordinate of the landing area is at 86% frame height.
        y = (int)(Framework.frameHeight * 0.88);
    }


    private void LoadContent()
    {
        try
        {
            URL landingAreaImgUrl = this.getClass().getResource("/resources/images/landing_area.png");
            landingAreaImg = ImageIO.read(landingAreaImgUrl);
            landingAreaImgWidth = landingAreaImg.getWidth();

            URL landingLargeAreaImgUrl = this.getClass().getResource("/resources/images/landingLarge_area.png");
            landingLargeAreaImg = ImageIO.read(landingLargeAreaImgUrl);
            landingLargeAreaImgWidth = landingLargeAreaImg.getWidth();


        }
        catch (IOException ex) {
            Logger.getLogger(LandingArea.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    
    
    public void Draw(Graphics2D g2d)
    {
        g2d.drawImage(landingAreaImg, x, y, null);

    }
    public void Drawlarge(Graphics2D g2d)
    {
        g2d.drawImage(landingLargeAreaImg,x,y,null);
    }



}
