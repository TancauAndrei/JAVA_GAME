package states;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface StateMethods {

    void render(Graphics g);

     void update();

     void mouseClicked(MouseEvent e);

     void keyPressed(KeyEvent e);

     void keyReleased(KeyEvent e);
}
