package page_replacement;

import java.util.LinkedList;
import static page_replacement.Driver.f;
import static page_replacement.Driver.out;

public final class LRU extends ReplacementAlgorithm
{
    LinkedList<Integer> lruList = new LinkedList<>();

    public LRU(int pageFrameCount) throws Exception
    {
        super(pageFrameCount);
        System.setOut(out);
        System.out.println("Last Recently Used Replacement:"); 
        
        int[] refStrings = Driver.createRefStrings(f);
        for(int i = 0; i < refStrings.length; i++)
        {
            System.out.print(refStrings[i] + " ----> ");
            insert(refStrings[i]);
        }
        System.out.println("\nNumber of Page Faults: " + pageFaultCount + "\n\n");
    }

    @Override
    public void insert(int pageNumber)
    {
        if(lruList.size() < pageFrameCount && !lruList.contains(pageNumber))
        {
            lruList.addFirst(pageNumber); //adds the reference string to the front page of the list
            pageFaultCount++;
            printList();
            System.out.println(" <page fault>");
        }
        else if (!lruList.contains(pageNumber)) //same as FIFO
        {
            lruList.removeLast();
            lruList.push(pageNumber);
            pageFaultCount++;
            printList();
            System.out.println(" <page fault>");
        }
        else
        {
            lruList.removeFirstOccurrence(pageNumber); //removes the reference string
            lruList.addFirst(pageNumber); //adds the reference string to the front page of the list
            printList();
            System.out.println();
        }
    }

    @Override
    public void printList()
    {
        System.out.print(lruList.toString());
    }
    
}
