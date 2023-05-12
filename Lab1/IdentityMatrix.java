public class IdentityMatrix extends Matrix{
	
	//Methods:
	
	IdentityMatrix(int M, int N)
	{
		super(M,N);
	}
	
	/////////////////////////////////////////////////////////////////////

	public boolean setNumbers(int numsinRow[])
	{
		boolean check;
		check= super.setNumbers(numsinRow);
		boolean flag = true;    
		  
		if(check==true)
		{
			if(super.M==super.N) // square matrix 
			{
				 for (int i = 0; i < M; i++) 
		    	    {
		    	        for (int j = 0; j < N; j++)
		    	        {
		    	        	 if(i == j && super.Numbers[i][j] != 1)
		    	        	 {    
		                         flag = false;    
		                         break;    
		                     }    
		    	        	 if(i != j && super.Numbers[i][j] != 0)
		                     {    
		                         flag = false;    
		                         break;    
		                     }  
		    	        }
		    	    }
				 
				 if(flag)    
                 {
                	 System.out.println("Given matrix is an identity matrix");
                	 return true;
                 }
                 else    
                 {
                	 System.out.println("Given matrix is not an identity matrix"); 
    				 return false;
                 }        
	    	}
			else
			{
				 System.out.println("Matrix should be a square matrix"); 
				 return false;
			}
		}
		else 
		{
			System.out.println("error in creating the matrix");	
			return false;
		}
	}
	
	/////////////////////////////////////////////////////////////////////
	
	public Matrix transpose(Matrix a)
	{
		/*
		Matrix c = new Matrix(M, N);
		if(a.setNumbers()==true) // identity matrix
		{
			return a;
		}
		else
		{
			c=super.transpose(a);
			return c;
		}
		*/
		a= super.transpose(a);
		return a;
	}
}
