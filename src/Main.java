import java.io.File;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author S542021
 */
public class Main {
    
    public static void main(String[] args) 
    {
//        Scanner asd = new Scanner(System.in);
//        System.out.println(asd.nextLine().length());
        File imported = new File("Maze.txt");
        Maze a = new Maze(10);
        System.out.println(a.toString());
        System.out.println(a.canSolve());
    }
    
}
