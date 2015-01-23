
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Maze 
{
    //Walls are the character 'X'
    char[][] MazeMap;
    int size;
    public Maze()
    {
        size = 25;
        CreateMaze(size);
    }
    
    public Maze(File file)
    {
        Scanner asd = new Scanner(System.in);
        try { asd = new Scanner(file); } catch (Exception ex) {};
        size = asd.nextInt();
        asd.nextLine();
        MazeMap = new char[size][size];
        for (int i = 0; i < size; i++) 
        {
            String temp = asd.nextLine();
            for (int j = 0; j < temp.length(); j++) 
            {
                MazeMap[j][i] = temp.charAt(j);
            }
        }
    }
    
    public Maze(char[][] a)
    {
        MazeMap = a;
    }
    
    public Maze(int size)
    {
        this.size = size;
        CreateMaze(size);
    }
    
    public void CreateMaze(int size)
    {
        MazeMap = new char[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++) 
            {
                MazeMap[i][j] = 'X';
            }
        }
        MazeMap[(int)(Math.random()*(size-2))+1][(int)(Math.random()*(size-2))+1] = 'S';
        while(canSolve())
        {
            for (int i = 0; i < MazeMap.length; i++) {
                for (int j = 0; j < MazeMap.length; j++) {
                    System.out.print(MazeMap[i][j]);
                }
                System.out.println("");
            }
            System.out.println("");
            System.out.println("");
            System.out.println("");
            XandY Start = new XandY((int)(Math.random()*(size-2))+1, (int)(Math.random()*(size-2))+1);
            if((int)(Math.random()*10) % 2 == 0)
            {
                XandY End = new XandY((int)(Math.random()*(size-2))+1, Start.getY());
                if(Start.getX() < End.getX())
                {
                    int amount = End.getX() - Start.getX();
                    for (int i = Start.getX(); i < Start.getX()+amount; i++) 
                    {
                        MazeMap[i][Start.getY()] = ' ';
                    }
                }
                else
                {
                    int amount = Start.getX() - End.getX();
                    for (int i = End.getX(); i < End.getX()+amount; i++) 
                    {
                        MazeMap[i][Start.getY()] = ' ';
                    }
                }
            }
            else
            {
                XandY End = new XandY(Start.getX(), (int)(Math.random()*(size-2))+1);
                if(Start.getY() < End.getY())
                {
                    int amount = End.getY() - Start.getY();
                    for (int i = Start.getY(); i < Start.getY()+amount; i++) 
                    {
                        MazeMap[Start.getX()][i] = ' ';
                    }
                }
                else
                {
                    int amount = Start.getY() - End.getY();
                    for (int i = End.getY(); i < End.getY()+amount; i++) 
                    {
                        MazeMap[Start.getX()][i] = ' ';
                    }
                }
            }
        }
    }
    
    public boolean canSolve()
    {
        int amountofturns = 0;
        //Makes Map and Location in map
        char[][] tempMap = MazeMap;
        XandY location = new XandY();
        //Finds correct location
        for (int i = 0; i < tempMap.length; i++) 
        {
            for (int j = 0; j < tempMap.length; j++) 
            {
                if(tempMap[i][j] == 'S')
                    location = new XandY(i,j);
            }
        }
        //Finds next move
        boolean isReturning = false;
        while(!location.isAtEdge(size) && amountofturns < 100000)
        {
            amountofturns++;
            if(isReturning == false)
            {
                if(tempMap[location.getX()+1][location.getY()] == ' ')
                {
                    if(tempMap[location.getX()][location.getY()] == ' ')
                        tempMap[location.getX()][location.getY()] = '*';
                    location = new XandY(location.getX()+1, location.getY());
                }
                else if(tempMap[location.getX()][location.getY()+1] == ' ')
                {
                    if(tempMap[location.getX()][location.getY()] == ' ')
                        tempMap[location.getX()][location.getY()] = '*';
                    location = new XandY(location.getX(), location.getY()+1);
                }
                else if(tempMap[location.getX()-1][location.getY()] == ' ')
                {
                    if(tempMap[location.getX()][location.getY()] == ' ')
                        tempMap[location.getX()][location.getY()] = '*';
                    location = new XandY(location.getX()-1, location.getY());
                }
                else if(tempMap[location.getX()][location.getY()-1] == ' ')
                {
                    if(tempMap[location.getX()][location.getY()] == ' ')
                        tempMap[location.getX()][location.getY()] = '*';
                    location = new XandY(location.getX(), location.getY()-1);
                }
                else
                {
                    isReturning = true;
                    tempMap[location.getX()][location.getY()] = 'O';
                }
            }
            else
            {
                if(tempMap[location.getX()][location.getY()-1] == '*')
                {
                    if(tempMap[location.getX()][location.getY()] == '*')
                        tempMap[location.getX()][location.getY()] = ' ';
                    location = new XandY(location.getX(), location.getY()-1);
                }
                else if(tempMap[location.getX()-1][location.getY()] == '*')
                {
                    if(tempMap[location.getX()][location.getY()] == '*')
                        tempMap[location.getX()][location.getY()] = ' ';
                    location = new XandY(location.getX()-1, location.getY());
                }
                else if(tempMap[location.getX()][location.getY()+1] == '*')
                {
                    if(tempMap[location.getX()][location.getY()] == '*')
                        tempMap[location.getX()][location.getY()] = ' ';
                    location = new XandY(location.getX(), location.getY()+1);
                }
                else if(tempMap[location.getX()+1][location.getY()] == '*')
                {
                    if(tempMap[location.getX()][location.getY()] == '*')
                        tempMap[location.getX()][location.getY()] = ' ';
                    location = new XandY(location.getX()+1, location.getY());
                }
                else
                {
                    isReturning = false;
                }
            }
        }
        return amountofturns < 100000;
    }
    
    public void solveMaze()
    {
        //Makes Map and Location in map
        char[][] tempMap = MazeMap;
        XandY location = new XandY();
        //Finds correct location
        for (int i = 0; i < tempMap.length; i++) 
        {
            for (int j = 0; j < tempMap.length; j++) 
            {
                if(tempMap[i][j] == 'S')
                    location = new XandY(i,j);
            }
        }
        //Finds next move
        boolean isReturning = false;
        while(!location.isAtEdge(size))
        {
            if(isReturning == false)
            {
                if(tempMap[location.getX()+1][location.getY()] == ' ')
                {
                    if(tempMap[location.getX()][location.getY()] == ' ')
                        tempMap[location.getX()][location.getY()] = '*';
                    location = new XandY(location.getX()+1, location.getY());
                }
                else if(tempMap[location.getX()][location.getY()+1] == ' ')
                {
                    if(tempMap[location.getX()][location.getY()] == ' ')
                        tempMap[location.getX()][location.getY()] = '*';
                    location = new XandY(location.getX(), location.getY()+1);
                }
                else if(tempMap[location.getX()-1][location.getY()] == ' ')
                {
                    if(tempMap[location.getX()][location.getY()] == ' ')
                        tempMap[location.getX()][location.getY()] = '*';
                    location = new XandY(location.getX()-1, location.getY());
                }
                else if(tempMap[location.getX()][location.getY()-1] == ' ')
                {
                    if(tempMap[location.getX()][location.getY()] == ' ')
                        tempMap[location.getX()][location.getY()] = '*';
                    location = new XandY(location.getX(), location.getY()-1);
                }
                else
                {
                    isReturning = true;
                    tempMap[location.getX()][location.getY()] = 'O';
                }
            }
            else
            {
                if(tempMap[location.getX()][location.getY()-1] == '*')
                {
                    if(tempMap[location.getX()][location.getY()] == '*')
                        tempMap[location.getX()][location.getY()] = ' ';
                    location = new XandY(location.getX(), location.getY()-1);
                }
                else if(tempMap[location.getX()-1][location.getY()] == '*')
                {
                    if(tempMap[location.getX()][location.getY()] == '*')
                        tempMap[location.getX()][location.getY()] = ' ';
                    location = new XandY(location.getX()-1, location.getY());
                }
                else if(tempMap[location.getX()][location.getY()+1] == '*')
                {
                    if(tempMap[location.getX()][location.getY()] == '*')
                        tempMap[location.getX()][location.getY()] = ' ';
                    location = new XandY(location.getX(), location.getY()+1);
                }
                else if(tempMap[location.getX()+1][location.getY()] == '*')
                {
                    if(tempMap[location.getX()][location.getY()] == '*')
                        tempMap[location.getX()][location.getY()] = ' ';
                    location = new XandY(location.getX()+1, location.getY());
                }
                else
                {
                    isReturning = false;
                }
            }
        }
        tempMap[location.getX()][location.getY()] = '*';
        for (int i = 0; i < size; i++) 
        {
            for (int j = 0; j < size; j++) 
            {
                if(tempMap[i][j] == 'O')
                    tempMap[i][j] = ' ';
            }
        }
        MazeMap = tempMap;
    }
    
    public String getCharMatrixtoString(char[][] im)
    {
        String toReturn = "";
        for (int i = 0; i < im.length; i++) 
        {
            for (int j = 0; j < im[0].length; j++) 
            {
                toReturn+=im[j][i]+" ";
            }
            toReturn+="\n";
        }
        return toReturn;
    }
    
    public String toString()
    {
        String toReturn = "";
        for (int i = 0; i < MazeMap.length; i++) 
        {
            for (int j = 0; j < MazeMap[0].length; j++) 
            {
                toReturn+=MazeMap[j][i]+" ";
            }
            toReturn+="\n";
        }
        return toReturn;
    }
}

class XandY
{
    
    private int x;
    private int y;
    public XandY()
    {
        x = y = 0;
    }
    
    public XandY(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public boolean isAtEdge(int size)
    {
        return x == 0 || y == 0 || x == size-1 || y == size-1;
    }
    
    public String toString()
    {
        return x + "  " + y; 
    }
}
