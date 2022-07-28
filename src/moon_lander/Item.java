package moon_lander;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Item{
    public int x1;
    public int y1;

    private BufferedImage enlargement;
    public int enlargement_Width;
    public int enlargement_Height;

    private Random random;


    public Item()
    {
        Initialize();
        LooadContent();
    }
    public void Initialize() {
        random=new Random();
        ResetItem();
    }

    public void ResetItem() {
        x1= random.nextInt(Framework.frameWidth-50 - enlargement_Width- 50);
        y1= random.nextInt(Framework.frameHeight-100 - enlargement_Height-100);

    }


    public void LooadContent() {
        try {
            URL ItemImgUrl = this.getClass().getResource("/resources/images/star.png");
            enlargement = ImageIO.read(ItemImgUrl);
            enlargement_Width = enlargement.getWidth();
            enlargement_Height = enlargement.getHeight();
        } catch (IOException ex) {
            Logger.getLogger(LandingArea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Draw(Graphics2D g2d)
        {
            g2d.setColor(Color.white);
            g2d.drawImage(enlargement, x1, y1, null);
        }






}
