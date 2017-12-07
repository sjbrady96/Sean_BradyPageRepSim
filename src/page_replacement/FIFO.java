package page_replacement;

import java.util.LinkedList;
import static page_replacement.Driver.f;
import static page_replacement.Driver.out;

public final class FIFO extends ReplacementAlgorithm
{
    LinkedList<Integer> fifoList = new LinkedList<>();

    public FIFO(int pageFrameCount) throws Exception
    {
        super(pageFrameCount);
        System.setOut(out);
        System.out.println("First In First Out Replacement:");
        
        int[] refStrings = Driver.createRefStrings(f);
        for(int i = 0; i < refStrings.length; i++) //for each reference string
        {
            System.out.print(refStrings[i] + " ----> ");
            insert(refStrings[i]);
        }
        System.out.println("\nNumber of Page Faults: " + pageFaultCount + "\n\n");
    }
    
    @Override
    public void insert(int pageNumber)
    {
        if(fifoList.size() < pageFrameCount && !fifoList.contains(pageNumber)) //list is not at the maximum size and
        {                                                                      //list does not conatian the reference string
            fifoList.push(pageNumber);
            pageFaultCount++;
            printList();
            System.out.println(" <page fault>");
        }
        else if (!fifoList.contains(pageNumber)) //list is at the maximum size and
        {                                        //list does not contain the reference string
            fifoList.removeLast();
            fifoList.push(pageNumber);
            pageFaultCount++;
            printList();
            System.out.println(" <page fault>");
        }
        else //list contains the reference string, no insertion
        {
            printList();
            System.out.println();
        }
    }
    
    @Override
    public int getPageFaultCount()
    {
        return pageFaultCount;
    }
    
    @Override
    public void printList()
    {
        System.out.print(fifoList.toString());
    }
}
