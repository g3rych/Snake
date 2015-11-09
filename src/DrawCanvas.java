import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DrawCanvas extends JPanel implements KeyListener {
    private Snake snake;
    private Apple apple;

    public DrawCanvas() {
        snake = new Snake();
        apple = new Apple();
        setPreferredSize(new Dimension(SnakeApp.CANVAS_WIDTH,SnakeApp.CANVAS_HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        addKeyListener(this);
        setFocusable(true);
        requestFocus();

        Thread gameLoop = new Thread() {
            public void run() {
               while (SnakeApp.gameStatus == GameStatus.RUN) {
                   snake.move();
                   repaint();
                   try {
                       Thread.sleep(115);
                   }catch (Exception e) {}
                   if (snake.isBorder()) JOptionPane.showMessageDialog(null,"GameOver");
                   if (snake.isApple(apple.getCenter())) apple.respawn();
               }
            }
        };
        gameLoop.start();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        //g2D.setComposite(ac);
        super.paintComponent(g2D);
        snake.draw(g2D);
        apple.draw(g2D);

    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
           if (snake.getDirection() != Direction.LEFT) snake.setDirection(Direction.RIGHT);
            snake.move();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (snake.getDirection() != Direction.DOWN) snake.setDirection(Direction.UP);
            snake.move();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (snake.getDirection() != Direction.UP) snake.setDirection(Direction.DOWN);
            snake.move();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (snake.getDirection() != Direction.RIGHT) snake.setDirection(Direction.LEFT);
            snake.move();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
