package page_replacement;

import static page_replacement.Driver.f;
import static page_replacement.Driver.out;

public final class OPT extends ReplacementAlgorithm
{
    int[] refStrings = Driver.createRefStrings(f);
    static int[] optList;
    static int max;
    
    public OPT(int pageFrameCount) throws Exception
    {
        super(pageFrameCount);
        System.setOut(out);
        System.out.println("Optimal Replacement:");
        
        int refCount = refStrings.length; //number of reference strings
        optList = new int[pageFrameCount]; //page frame list
        int[] refDistance = new int[pageFrameCount]; //list to monitor distance between reference strings
        int i;
        
        for(i = 0; i < pageFrameCount; i++) //initialize each page frame
        {
            optList[i] = -1;
            refDistance[i] = 0;
        }
        i = 0;
        while(i < refCount) //for each reference string
        {
            int j = 0;
            int flag = 0;
            while(j < pageFrameCount)
            {
                if(refStrings[i] == optList[j]) //reference string is already in the page frame list
                {
                    flag = 1;
                    System.out.print(refStrings[i] + " ----> ");
                    printList();
                    System.out.println();
                }
                j++;
            }
            j = 0;
            if(flag == 0) //reference string is not in the page frame list
            {
                if(i >= pageFrameCount)
                {
                    max = 0;
                    int k = 0;
                    while(k < pageFrameCount)
                    {
                        int distance = 0;
                        int jl = i + 1;
                        while(jl < refCount)
                        {
                            if(optList[k] != refStrings[jl]) //next page frame is not a match
                            {
                                distance++;
                            }
                            else
                            {
                                break; //next page frame is a match
                            }
                            jl++;
                        }
                        refDistance[k] = distance;
                        k++;
                    }
                    k = 0;
                    while(k < pageFrameCount - 1) //finds the reference string with the maximum distance
                    {
                        if(refDistance[max] < refDistance[k + 1])
                        {
                            max = k + 1;
                        }
                        k++;
                    }
                    insert(refStrings[i]);
                }
                else
                {
                    optList[i % pageFrameCount] = refStrings[i];
                }
                pageFaultCount++;
                System.out.print(refStrings[i] + " ----> ");
                printList();
                System.out.println(" <page fault>");
                while(j < pageFrameCount)
                {
                    j++;
                }
            }
            i++;
        }
        System.out.println("\nNumber of Page Faults: " + pageFaultCount + "\n\n");
    }

    @Override
    public void insert(int pageNumber)
    {
        optList[max] = pageNumber; //changes the page frame reference string with the maximum
                                   //distance to the current reference string
    }
    
    @Override
    public void printList()
    {
        System.out.print("[");
        for(int i = optList.length - 1; i >= 0; i--)
        {
            if(optList[i] != -1 && i != 0)
            {
                System.out.print(optList[i] + ", ");
            }
            else if(optList[i] != -1)
            {
                System.out.print(optList[i]);
            }
        }
        System.out.print("]");
    }
}
