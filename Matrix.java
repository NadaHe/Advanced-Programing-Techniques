
public class Matrix implements Addable< Matrix> {
	
	//Data Members:

	public int Numbers[][];   
	int M,N;
	
	//constructor
	Matrix(int M , int N)
	{
		this.M=M;
		this.N=N;
		Numbers = new int[M][N];
		
		if(M<0 || N<0)
		{
			 System.out.println("Invalid input dimensions : "); 
		}
	}
	
	/////////////////////////////////////////////////////////////////////
	
    boolean setNumbers(int numsinRow[])
	{
    	if(numsinRow.length==0) return false;
    	
    	 
    	if(numsinRow.length == this.M*this.N)
    	{
    	    int count = 0;

    	    for (int i = 0; i < M; i++) 
    	    {
    	        for (int j = 0; j < N; j++)
    	        {
    	            if (count == numsinRow.length) break; // reach the end
    	            Numbers[i][j] = numsinRow[count];
    	            count++;
    	        }
    	    }
    		return true;
    	}
    	
    	else if(numsinRow.length < M*N)
    	{
    		System.out.println(" you entered numbers < the size o the matrix ");
    		return false;
    	}
    	else if(numsinRow.length > M*N)
    	{
    		System.out.println(" you entered numbers > the size o the matrix ");
    		return false;
    	}
    	else
    		return false;
	}
    
	/////////////////////////////////////////////////////////////////////
    
	
	void print(Matrix a)
	{
		for(int i = 0; i<this.M; i++) {
			for(int j = 0; j<this.N; j++)
			{ 
				System.out.print(Numbers[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	/////////////////////////////////////////////////////////////////////
	
	public Matrix transpose(Matrix a)
	{
		Matrix c = new Matrix(N, M);
		
		for(int i = 0; i<this.M; i++) 
		{
			for(int j = 0; j<this.N; j++) 
			{ 
				c.Numbers[j][i]=a.Numbers[i][j];
			}
		}
		return c;
	}
	
	/////////////////////////////////////////////////////////////////////
	
	public Matrix Add(Matrix a)
	{
		if(this.M != a.M || this.N != a.N) // size mismatch
		{
			System.out.println("can not compute sum of different size matrices ");
			throw new RuntimeException("Illegal matrix dimensions.");
		}
		
		Matrix c = new Matrix(M, N);
		for(int i = 0; i<M; i++) 
		{
			for(int j =0; j<N;j++)
			{ 
				c.Numbers[i][j] = this.Numbers[i][j] + a.Numbers[i][j];
			}
		}
		return c;	
	}
}