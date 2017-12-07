package page_replacement;

public abstract class ReplacementAlgorithm
{
    protected int pageFaultCount;
    protected int pageFrameCount;
    
    public ReplacementAlgorithm(int pageFrameCount) throws Exception
    {
        if(pageFrameCount < 0)
        {
            throw IllegalArgumentException();
        }
        
        this.pageFrameCount = pageFrameCount;
        pageFaultCount = 0;
    }

    public int getPageFaultCount()
    {
        return pageFaultCount;
    }
    
    public abstract void insert(int pageNumber);
    
    public abstract void printList();
    
    private Exception IllegalArgumentException()
    {
        throw new UnsupportedOperationException("Illegal Argument.");
    }
}
