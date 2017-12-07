package page_replacement;

import java.util.LinkedList;
import java.util.Random;
import static page_replacement.Driver.f;
import static page_replacement.Driver.out;

public final class Rand extends ReplacementAlgorithm
{
    LinkedList<Integer> randomList = new LinkedList<>();
    static int rand;
    
    public Rand(int pageFrameCount) throws Exception
    {
        super(pageFrameCount);
        System.setOut(out);
        System.out.println("Random Replacement:");
        
        int[] refStrings = Driver.createRefStrings(f);
        for(int i = 0; i < refStrings.length; i++)
        {   
            System.out.print(refStrings[i] + " ----> ");
            insert(refStrings[i]);
        }
        System.out.println("\nNumber of Page Faults: " + pageFaultCount + "\n");
    }

    @Override
    public void insert(int pageNumber)
    {
        Random rn = new Random();
        rand = rn.nextInt(pageFrameCount);
        
        if(randomList.size() < pageFrameCount && !randomList.contains(pageNumber))
        {
            randomList.addFirst(pageNumber); //adds the reference string to the front page of the list
            pageFaultCount++;
            printList();
            System.out.println(" <page fault>");
        }
        else if (!randomList.contains(pageNumber))
        {
            randomList.set(rand, pageNumber); //adds the reference string to a random page in the list
            pageFaultCount++;
            printList();
            System.out.println(" <page fault>");
        }
        else
        {
            randomList.removeFirstOccurrence(pageNumber); //lru
            randomList.addFirst(pageNumber);              //replacement
            printList();
            System.out.println();
        }
    }

    @Override
    public void printList()
    {
        System.out.print(randomList.toString());
    }
    
}
