package page_replacement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 * This program performs four page replacement algorithm simulations:
 * first in first out, optimal, last recently used, and random
 * 
 * @input Any text file consisting of all integers separated by spaces
 * @output 'replacement_output.txt'
 * 
 * @author Sean J. Brady
 * @version 1.0 12/01/2017
 */

public class Driver 
{
    static File f;
    static PrintStream out;
    
    public static void main(String[] args) throws Exception
    {
        //output file
        PrintStream stdout = System.out;
        out = new PrintStream(new FileOutputStream("replacement_output.txt"));
        
        //input file
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select source file");
        chooser.showOpenDialog(null);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f = chooser.getSelectedFile();
        
        //replacement algorithms
        System.out.print("(FIFO Replacement) "); 
        new FIFO(getFrameCount());
        System.setOut(stdout);
        
        System.out.print("(OPT Replacement) "); 
        new OPT(getFrameCount());
        System.setOut(stdout);
        
        System.out.print("(LRU Replacement) ");
        new LRU(getFrameCount());
        System.setOut(stdout);
        
        System.out.print("(Random Replacement) "); 
        new Rand(getFrameCount());
    }   
    
    //read the reference strings from a text file and
    //write the reference strings to an integer array
    public static int[] createRefStrings(File file) throws FileNotFoundException, IOException
    {
        ArrayList<Integer> refList = new ArrayList<>();
        Scanner scanner = new Scanner(f); 
        int count = 0;
        int i = 0;
        
        while(scanner.hasNextInt())
        {
            refList.add(scanner.nextInt());
            count++;
        }
        
        int[] refs = new int[count];
        while(i < refList.size())
        {
            refs[i] = refList.get(i);
            i++;
        }
        
        return refs;
    }
    
    public static int getFrameCount()
    {
        System.out.print("Enter the page frame count: ");
        Scanner in = new Scanner(System.in);
        return in.nextInt(); //returns the desired number of page frames
    }
}