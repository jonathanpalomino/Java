/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package animacionconlineasenjava;

/**
 *
 * @author JONATHAN
 */
import java.awt.*;
import java.lang.Math;
import java.awt.event.*;
import java.awt.Graphics;
import java.applet.Applet;

/*
  <APPLET
      CODE=dibujar.class
      WIDTH=600
      HEIGHT=200 >
  </APPLET>
*/

public class dibujar extends Applet implements ActionListener, MouseListener, MouseMotionListener {
    Button bDraw, bLine, bOval, bRect, bRounded;
    Point dot[] = new Point[1000];
    Point start, end;
    int dots = 0;

    boolean mouseUp = false;
    boolean draw = false;
    boolean line = false;
    boolean oval = false;
    boolean rectangle = false;
    boolean rounded = false;

    public void init()
    {
        bLine = new Button("Dibujar rectas");
        bOval = new Button("Dibujar ovalos");
        bRect = new Button("Dibujar rectangulos");
        bRounded = new Button("Dibujar rectangulos redondeados");
        bDraw = new Button("Dibujo libre");

        add(bLine);
        add(bOval);
        add(bRect);
        add(bRounded);
        add(bDraw);

        bLine.addActionListener(this);
        bOval.addActionListener(this);
        bRect.addActionListener(this);
        bRounded.addActionListener(this);
        bDraw.addActionListener(this);

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void mousePressed(MouseEvent e)
    {
        mouseUp = false;
        start = new Point(e.getX(), e.getY());
    }

    public void mouseReleased(MouseEvent e)
    {
        if(line){
            end = new Point(e.getX(), e.getY());
        } else {
            end = new Point(Math.max(e.getX(), start.x),
                Math.max(e.getY(), start.y));
            start = new Point(Math.min(e.getX(), start.x),
                Math.min(e.getY(), start.y));
        }
        mouseUp = true;
        repaint();
    }

    public void mouseDragged(MouseEvent e)
    {
        if(draw){
            dot[dots] = new Point(e.getX(), e.getY());
            dots++;
            repaint();
        }
    }

    public void mouseClicked(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}

    public void paint (Graphics g)
    {
        if (mouseUp) {
            int width = end.x - start.x;
            int height = end.y - start.y;

            if(line){
                g.drawLine(start.x, start.y, end.x, end.y);
            }
            else if(oval){
                g.drawOval(start.x, start.y, width, height);
            }
            else if(rectangle){
                g.drawRect(start.x, start.y, width, height);
            }
            else if(rounded){
                g.drawRoundRect(start.x, start.y, width, height, 10, 10);
            }
            else if(draw){
                for(int loop_index = 0; loop_index < dots - 1; loop_index++){
                    g.drawLine(dot[loop_index].x, dot[loop_index].y,
                        dot[loop_index + 1].x, dot[loop_index + 1].y);
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        setFlagsFalse();
        if(e.getSource() == bDraw)draw = true;
        if(e.getSource() == bLine)line = true;
        if(e.getSource() == bOval)oval = true;
        if(e.getSource() == bRect)rectangle = true;
        if(e.getSource() == bRounded)rounded = true;
    }

    void setFlagsFalse()
    {
        rounded = false;
        line = false;
        oval = false;
        rectangle = false;
        draw = false;
    }
}
