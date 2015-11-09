import java.awt.*;
import java.util.LinkedList;

public class Snake {
    public static final int SEGMENT_SIZE = 10;
    public static final int velX = 5;
    public static final int velY = 5;
    LinkedList<Point> points = new LinkedList<>();
    Direction direction = Direction.UP;

    public Snake() {
        points.add(new Point(320, 240));
        points.add(new Point(320, 255));
        points.add(new Point(320, 270));
        points.add(new Point(320, 285));
    }

    public void draw(Graphics2D g2D) {
        for (Point p : points) {
            int x = (int) p.getX();
            int y = (int) p.getY();
            g2D.fill3DRect(x, y, SEGMENT_SIZE, SEGMENT_SIZE, true);
        }
    }

    public void move() {
        int x = (int) points.getFirst().getX();
        int y = (int) points.getFirst().getY();

        switch (direction) {
            case RIGHT: {
                points.getLast().move(x + SEGMENT_SIZE, y);
                points.addFirst(points.getLast());
                points.removeLast();
                break;
            }
            case UP: {
                points.getLast().move(x, y - SEGMENT_SIZE);
                points.addFirst(points.getLast());
                points.removeLast();
                break;
            }

            case LEFT: {
                points.getLast().move(x - SEGMENT_SIZE, y);
                points.addFirst(points.getLast());
                points.removeLast();
                break;
            }

            case DOWN: {
                points.getLast().move(x, y + SEGMENT_SIZE);
                points.addFirst(points.getLast());
                points.removeLast();
                break;
            }

        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isBorder() {
        for (Point p : points) {
            if (p.getX() >= SnakeApp.CANVAS_WIDTH || p.getX() <= 0) {
                SnakeApp.setGameStatus(GameStatus.OVER);
                return true;
            }
            if (p.getY() >= SnakeApp.CANVAS_HEIGHT || p.getY() <= 0) {
                SnakeApp.setGameStatus(GameStatus.OVER);
                return true;
            }
        }
        return false;
    }

    public boolean isApple(Point food) {
        if (points.getFirst().distance(food) <= 10.0) {
            increase();
            return true;
        }
        return false;
    }

    public void increase() {
        int x = (int) points.getFirst().getX();
        int y = (int) points.getFirst().getY();
        switch (direction) {
            case RIGHT:
                points.addFirst(new Point(x+ SEGMENT_SIZE,y));
                break;
            case LEFT:
                points.addFirst(new Point(x- SEGMENT_SIZE,y));
                break;
            case DOWN:
                points.addFirst(new Point(x,y+ SEGMENT_SIZE));
                break;
            case UP:
                points.add(new Point(x,y- SEGMENT_SIZE));
                break;
        }
    }
}
