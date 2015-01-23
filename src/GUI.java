import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JApplet;

public class GUI extends JApplet implements KeyListener
{
    
    
    Maze maze = new Maze();
    public void init() 
    {
        File imported = new File("Maze.txt");
        maze = new Maze(imported);
//        maze = new Maze(10);
//        System.out.println(maze.toString());
        this.setSize(600,600);
        this.addKeyListener(this);
        this.setFocusable(true);
    }
    
    public void paint(Graphics g)
    {
        int sizeofbox = 600/(maze.size);
        for (int i = 0; i < maze.size; i++) 
        {
            for (int j = 0; j < maze.size; j++) 
            {
                if(maze.MazeMap[i][j] == 'X')
                {
                    g.setColor(Color.black);
                }
                if(maze.MazeMap[i][j] == 'S')
                {
                    g.setColor(Color.green);
                }
                if(maze.MazeMap[i][j] == ' ')
                {
                    g.setColor(Color.white);
                }
                if(maze.MazeMap[i][j] == '*')
                {
                    g.setColor(Color.red);
                }
                if(maze.MazeMap[i][j] == 'O')
                {
                    g.setColor(Color.white);
                }
                g.fillRect(i*sizeofbox, j*sizeofbox, sizeofbox, sizeofbox);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("SOLVE");
        if(e.getKeyChar() == 's' || e.getKeyChar() == 'S')
        {
            maze.solveMaze();
        }
        System.out.println("SOLVED");
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
