package moon_lander;

import java.awt.*;

public class Fuel {
    public double oil = 10;
    public double f = 0.1;

    public void resetFuel(){
        oil = 10;
        f = 0.1;
    }
    public void Draw(Graphics2D g2d)
    {
        if(oil <= 2){
            g2d.setColor(Color.red);
        }
        else {
            g2d.setColor(Color.white);
        }
        oil = Math.round(oil*10) / 10.0;
        g2d.drawString("Fuel: " + oil, 700, 40);
    }
}
