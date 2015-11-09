import javax.swing.*;

public class SnakeApp extends JFrame {
    public static GameStatus gameStatus = GameStatus.RUN;
    public static final int CANVAS_WIDTH = 640;
    public static final int CANVAS_HEIGHT = 480;
    public SnakeApp() {
        add(new DrawCanvas());
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void setGameStatus(GameStatus gameStatus) {
        SnakeApp.gameStatus = gameStatus;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SnakeApp();
            }
        });
    }
}
