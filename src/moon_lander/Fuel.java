package moon_lander;

import java.awt.*;

public class Fuel {
    public double oil = 10;
    public double f = 0.1;

    public void resetFuel(){
        oil = 10;
        f = 0.1;
    }
    public void Draw(Graphics2D g)
    {
        if(oil <= 2){
            g.setColor(Color.red);
        }
        else {
            g.setColor(Color.white);
        }
        oil = Math.round(oil*10) / 10.0;
        g.drawString("Fuel: " + oil, 600, 40);
    }
}
