import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Apple {
    private Point center;
    private Random random = new Random();
    private BufferedImage img;
    private String path;


    public Apple() {
        center = new Point(240,320);
        System.out.println(path = System.getProperty("user.dir")+"\\Apple.png");

        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("ops");
        }

    }
    public void draw(Graphics2D g2D) {
        int x = (int) center.getX();
        int y = (int) center.getY();
        //g2D.fillOval(x,y,10,10);
        g2D.drawImage(img,x-5,y-5,null);

    }
    public void respawn() {
        int x = random.nextInt(SnakeApp.CANVAS_WIDTH-100)+50;
        int y = random.nextInt(SnakeApp.CANVAS_HEIGHT-75)+50;
        center.move(x,y);
    }

    public Point getCenter() {
        return center;
    }
}
