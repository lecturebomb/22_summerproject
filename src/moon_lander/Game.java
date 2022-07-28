package moon_lander;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Actual game.
 * 
 * @author www.gametutorial.net
 */

public class Game {

    private int cnt;
    private int n;
    private int it=0;
    private int lit =0;
    private int m=2;
    private  long pretime;
    private  int delay = 20;
    /**
     * The space rocket with which player will have to land.
     */
    private PlayerRocket playerRocket;
    /**
     * Landing area on which rocket will have to land.
     */
    private LandingArea landingArea;
    
    /**
     * Game background image.
     */
    private BufferedImage backgroundImg;
    
    /**
     * Red border of the frame. It is used when player crash the rocket.
     */
    private BufferedImage redBorderImg;


    private ArrayList<Object> objectList = new ArrayList<Object>();
    private Object object;
    private Item item;
    private Fuel fuel;

    private LandingItem litem;



    public Game()
    {
        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
        
        Thread threadForInitGame = new Thread() {
            @Override
            public void run(){


                // Sets variables and objects for the game.
                Initialize();
                // Load game files (images, sounds, ...)
                LoadContent();

                
                Framework.gameState = Framework.GameState.PLAYING;
                cnt = 0;
                while(true){
                    pretime = System.currentTimeMillis();
                    if(System.currentTimeMillis()-pretime < delay){
                        try {
                            Thread.sleep(delay - System.currentTimeMillis() + pretime);
                            ObjectInitialize();
                            objectMove();
                            landingMove();
                            fuelUse();
                            cnt ++;

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        threadForInitGame.start();
    }
    
    
   /**
     * Set variables and objects for the game.
     */
    private void Initialize()
    {
        playerRocket = new PlayerRocket();
        landingArea  = new LandingArea();
        item=new Item();
        fuel = new Fuel();
        litem= new LandingItem();

    }
    private void ObjectInitialize(){
        if (cnt % 100 == 0 ){
            object = new Object();
            objectList.add(object);
        }
    }
    private void objectMove(){
        object.moveleft();
    }
    public void landingMove(){
        if(landingArea.x>700){
            landingArea.i = -1;
        } else if (landingArea.x<0) {
            landingArea.i=1;
        }
        landingArea.landingAreaMove(m);
    }
    public void fuelUse(){
        if(cnt % 10 == 0){
            fuel.oil -= fuel.f;
        }
    }

    /**
     * Load game files - images, sounds, ...
     */
    private void LoadContent()
    {
        try
        {
            URL backgroundImgUrl = this.getClass().getResource("/resources/images/background.jpg");
            backgroundImg = ImageIO.read(backgroundImgUrl);
            
            URL redBorderImgUrl = this.getClass().getResource("/resources/images/red_border.png");
            redBorderImg = ImageIO.read(redBorderImgUrl);
        }
        catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    /**
     * Restart game - reset some variables.
     */
    public void RestartGame()
    {
        playerRocket.ResetPlayer();
        object.ResetObject();
        fuel.resetFuel();
        item.ResetItem();
        litem.ResetItem();
        m=2;
        it = 0;
        lit=0;
    }
    
    
    /**
     * Update game logic.
     * 
     * @param gameTime gameTime of the game.
     * @param mousePosition current mouse position.
     */
    public void UpdateGame(long gameTime, Point mousePosition)
    {
        // Move the rocket
        playerRocket.Update();



            if ((playerRocket.x + playerRocket.rocketImgWidth) > object.x && (object.x + object.ObjectWidth) > playerRocket.x && (playerRocket.y + playerRocket.rocketImgHeight) > object.y && (object.y + object.ObjectHeight) > playerRocket.y) {
                playerRocket.crashed = true;
                Framework.gameState = Framework.GameState.GAMEOVER;
            }// 장애물 만나면 게임 종료

            if ((playerRocket.x + playerRocket.rocketImgWidth) > item.x1 && (item.x1 + item.enlargement_Width) > playerRocket.x && (playerRocket.y + playerRocket.rocketImgHeight) > item.y1 && (item.y1 + item.enlargement_Height) > playerRocket.y) {
                it = 1;
            }// 아이템을 먹으면 it=1
            if((playerRocket.x+ playerRocket.rocketImgWidth)> litem.x&&(litem.x+ litem.LandingImg_Width)> playerRocket.x&&(playerRocket.y+ playerRocket.rocketImgHeight)> litem.y&&(litem.y+ litem.LandingImg_Height)> playerRocket.y)
            {
                lit=1;//랜딩 아이템을 먹으면 lit=1
            }
            if (fuel.oil == 0) {// 연료가 모두 떨어지면 게임 종료
                playerRocket.crashed = true;
                Framework.gameState = Framework.GameState.GAMEOVER;
            }

            // Checks where the player rocket is. Is it still in the space or is it landed or crashed?
            // First we check bottom y coordinate of the rocket if is it near the landing area.
            if (playerRocket.y + playerRocket.rocketImgHeight - 10 > landingArea.y) {
                // Here we check if the rocket is over landing area.
                if (it == 0) {
                    if ((playerRocket.x > landingArea.x) && (playerRocket.x < landingArea.x + landingArea.landingAreaImgWidth - playerRocket.rocketImgWidth)) {
                        if (playerRocket.speedY <= playerRocket.topLandingSpeed)
                            if(lit==1)
                                playerRocket.landed = true;
                            else {
                                playerRocket.crashed = true;
                                Framework.gameState = Framework.GameState.GAMEOVER;
                            }
                    } else {
                        playerRocket.crashed = true;
                        Framework.gameState = Framework.GameState.GAMEOVER;
                    }

                } else if (it == 1) {
                    if ((playerRocket.x > landingArea.x) && (playerRocket.x < landingArea.x + landingArea.landingLargeAreaImgWidth - playerRocket.rocketImgWidth)) {
                        if (playerRocket.speedY <= playerRocket.topLandingSpeed)
                            if(lit==1)
                                playerRocket.landed = true;
                            else {
                                playerRocket.crashed = true;
                                Framework.gameState = Framework.GameState.GAMEOVER;
                            }
                    } else {
                        playerRocket.crashed = true;
                        Framework.gameState = Framework.GameState.GAMEOVER;
                    }

                }
                playerRocket.crashed=true;
                Framework.gameState = Framework.GameState.GAMEOVER;
            }

            {
                // Here we check if the rocket speed isn't too high.


            }
            if (playerRocket.landed || playerRocket.crashed) {
                m = 0;
                fuel.f = 0;
            }

    }

    /**
     * Draw the game to the screen.
     * 
     * @param g2d Graphics2D
     * @param mousePosition current mouse position.
     */
    public void Draw(Graphics2D g2d, Point mousePosition)
    {
        g2d.drawImage(backgroundImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        g2d.setColor(Color.white);
        g2d.drawString("cnt: " +  cnt, 10, 80);

        if(it == 1){
            landingArea.Drawlarge(g2d);

        }
        else{
            landingArea.Draw(g2d);
        }
        if(it == 0){
            item.Draw(g2d);

        }
        if (lit==0){
            litem.Draw(g2d);
        }
        playerRocket.Draw(g2d);
        object.Draw(g2d);
        fuel.Draw(g2d);
    }
    
    
    /**
     * Draw the game over screen.
     * 
     * @param g2d Graphics2D
     * @param mousePosition Current mouse position.
     * @param gameTime Game time in nanoseconds.
     */
    public void DrawGameOver(Graphics2D g2d, Point mousePosition, long gameTime)
    {
        Draw(g2d, mousePosition);
        g2d.setColor(Color.white);

        g2d.drawString("Press space  or enter to restart.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 70);
        
        if(playerRocket.landed)
        {
            g2d.setColor(Color.white);

            g2d.drawString("You have successfully landed!", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3);
            g2d.drawString("You have landed in   " + gameTime / Framework.secInNanosec + " seconds.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 20);
        }
        else
        {
            g2d.setColor(Color.red);
            g2d.drawString("You have crashed the rocket !", Framework.frameWidth / 2 - 95, Framework.frameHeight / 3);
            g2d.drawImage(redBorderImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        }
    }
}
