import java.util.Random;


public class GuitarString {

	// Define attributes here
	// Buffer and number of tics
	
	RingBuffer buffer;
	private int capacity;
	private int num_tics;
	
	/* ---------------------------------- *
	 * create a guitar string of the 	  *
	 * given frequency, using a sampling  *
	 * rate of 44,100					  *
	 * ---------------------------------- */
	public GuitarString(double frequency) 
	{
		capacity = (int)Math.round(44100 / frequency);
		buffer = new RingBuffer(capacity);
	}
	
	/* ------------------------------------- *
	 * create a guitar string whose size and * 
	 * initial values are given by the array *
	 * ------------------------------------- */
	public GuitarString(double[] init)     
	{
		num_tics = 0;
		capacity = init.length;
		buffer = new RingBuffer(capacity);
		for (int i = 0; i < capacity; i++){
			buffer.enqueue(init[i]);
		}
	}
	
	/* ------------------------------ *
	 * set the buffer to white noise  *
	 * ------------------------------ */
	public void pluck()                         
	{
		try {
			while (!buffer.isEmpty()) {
				buffer.dequeue();
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		for (int i = 0; i < capacity; i++){
			double sample = Math.random() - 0.5; // [0, 1] ==> [-0.5, 0.5]
			buffer.enqueue(sample);
		}
	}
	
	/* ------------------------------------
	 * advance the simulation one time step
	 * ------------------------------------ */
    public void tic()
    {
		double first = 0;
		try {
			first = buffer.dequeue();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		double second = buffer.peek();

		double sample = 0.994 * (first + second) * 0.5;
		buffer.enqueue(sample);

    }
    
    /* ---------------------------
     * Return the current sample
     * --------------------------- */
    public double sample()
    {
    	return buffer.peek();
    }
    
    /*
     *  Return number of tics
     */
    public int time()
    {
    	return num_tics++;
    }
    
}