package org.acadeamiadecodigo.mainiacs;

import org.acadeamiadecodigo.mainiacs.Elements.*;
import org.academiadecodigo.simplegraphics.graphics.*;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;


public class Game {

    /**
     * PADDING & CELL DIMENSIONS
     */
    public static int PADDING = 10;
    private static int PIXELCELL = 10;

    /**
     * Window Game Size
     */
    private static int height = 600;
    private static int width = 600;

    private Picture background;
    private static Elements[] elements;
    private static Text text;
    private static int score = 0;

    private static Ship ship;

    private static boolean gameOver;

    private Picture over = new Picture(0 + PADDING,0 + PADDING, "resources/Game_Over.png");

    public static int getPIXELCELL() {
        return PIXELCELL;
    }

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

    public int getPADDING() {
        return PADDING;
    }


    public static void showScore(){
        text = new Text(347, 35,"Score : " + score);
        score++;
        Rectangle rectangle = new Rectangle(324,20,65,40);
        rectangle.fill();
        rectangle.setColor(Color.WHITE);

        text.draw();
        text.grow(20,15);
        text.setColor(Color.BLUE);
    }

    public static void showScoreLess(){
        text = new Text(347, 35,"Score : " + score);
        score--;
        Rectangle rectangle = new Rectangle(324,20,65,40);
        rectangle.fill();
        rectangle.setColor(Color.WHITE);

        text.draw();
        text.grow(20,15);
        text.setColor(Color.BLUE);
    }

    /**
     * Ship Moving
     */
    public void init() throws InterruptedException {


        background = new Picture(0 + PADDING, 0 + PADDING, "resources/sea.png");
        background.draw();

        Music music = new Music();
        music.startMusic();

        ship = new Ship();
        ship.init();

        showScore();



        elements = new Elements[2000];


            for (int i = 0; i < elements.length; i++) {

                while (gameOver == false)  {

                elements[i] = ElementsFactory.getNewElement();
                System.out.println(elements[i]);
                elements[i].move();
                //checkCollision();


                //System.out.println("this is the for element " + elements[i]);

            }

            over.draw();  //game over background pic

        }



    }


    /**
     * Collision
     */
    public static void checkCollision() {

        for (Elements e : elements) {

            if (e == null) {
                continue;
            }

            /*
            System.out.println(e.getPicture().getY() + " this is the e y");
            System.out.println(e.getPicture().getX() + " this is the e x");
            System.out.println(ship.getPicture().getY() + " this is the ship y");
            System.out.println(ship.getPicture().getX() + " this is the ship x");
            */

            int posEY = e.getPicture().getY();
            int posSY = ship.getPicture().getY();
            int posEX = e.getPicture().getX();
            int posSX = ship.getPicture().getX();

            if(posEX+36 > posSX && posEX < posSX+36 && posEY+36 > posSY && posEY < posSY+36) {
                //System.out.println("crush here" + e.getPicture() + ship.getPicture());

                if (e instanceof Shipwrecked) {
                    showScore();
                    e.getPicture().delete();

                } else if (e instanceof Door) {
                        showScoreLess();
                        e.getPicture().delete();

                    } else {
                    e.getPicture().delete();
                    ship.getPicture().delete();
                    gameOver = true;
                }
            }
        }
    }
}