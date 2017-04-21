
public class RingBuffer {

    //Attributes
    private double [] buffer;
    private int last; // index for the next open location
    private int first; // index for location of the head of the list
    private int count;

    //Default Constructor
    public RingBuffer(){
        last = 0;
        first = 0;
        count = 0;
        buffer = null;
    }

	
	/* -----------------------------
	 * Construct empty ring buffer,
	 * with given max capacity
	 * ----------------------------- */
	public RingBuffer(int capacity)  
	{
	    buffer = new double[capacity];
	    last = 0;
	    first = 0;
	    count = 0;
	}
	
	/* -----------------------------
	 * return number of items currently
	 *  in the buffer
	 * ----------------------------- */
    public int size() 
    {
    	return count; //Last - first % buffer.length
    }
    
    /* -----------------------------*
     * is the buffer empty 			*
     * (size equals zero)?			*
     * -----------------------------*/
    // 
    public boolean isEmpty()  
    {
    	return count == 0; // last == first
    }
    
    /* ---------------------------- *
     * is the buffer full  			*
     * (size equals capacity)?		*
     * ---------------------------- */
    public boolean isFull()
    {
    	return count == buffer.length;
    }
    
    /* -----------------------------*
     * add item x to the end		*
     * -----------------------------*/
    // 
    public void enqueue(double x)
    {
        if (!isFull())
        {
            buffer[last] = x;
            last = (last + 1) % buffer.length;
            count ++;
        }
    }
    
    /* ---------------------------- *
     * delete and return item from  *
     * the front					*
     * ---------------------------- */
    public double dequeue() throws Exception {
        if (!isEmpty())
        {
            double item = buffer[first];
            first = (first + 1) % buffer.length;
            count--;
            return item;
        }
        else {
            throw new Exception("Buffer is empty");
        }
    }
 
    /* ------------------------------- *
     * return (but do not delete) item *
     * from the front				   *
     * ------------------------------- */
    public double peek()
    {
    	return buffer[first];
    }
    
}

