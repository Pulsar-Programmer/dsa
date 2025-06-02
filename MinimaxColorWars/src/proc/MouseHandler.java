package proc;

import java.awt.*;
import java.awt.event.*;
import java.util.Optional;

public class MouseHandler implements MouseListener, MouseMotionListener {
    public int mouseX = 0;
    public int mouseY = 0;

    ///Here, we use optionals to represent the (bool, (i32, i32)).
    public Optional<Point> mouseClicked = Optional.empty();
    // public Optional<Point> mousePressed = Optional.empty();
    // public Optional<Point> mouseReleased = Optional.empty();

    public MouseHandler() {}

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClicked = Optional.of(e.getPoint());
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseClicked = Optional.empty();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
    
    
}