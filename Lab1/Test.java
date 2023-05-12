import java.util.Scanner;

public class Test {

	public static void main(String[] args)
	{
		 Scanner input = new Scanner(System.in);
		 int row1, col1;
		 int row2, col2;
		 
		 System.out.print("Enter num of rows of first matrix : ");
		 row1=input.nextInt();
		 System.out.print("Enter num of columns of first matrix : ");
		 col1=input.nextInt();
		 
		 System.out.print("Enter num of rows of second matrix : ");
		 row2=input.nextInt();
		 System.out.print("Enter num of columns of second matrix : ");
		 col2=input.nextInt();
		
		 int array1[]=new int[row1*col1];
		 int array2[]=new int[row2*col2];
		 
		 System.out.print("Enter the numbers of array1: ");
		 
		 for(int i=0; i<row1*col1; i++)  
		 {     
		 array1[i]=input.nextInt();   //reading array elements from the user
		 }  
		 
		 System.out.print("Enter the numbers of array2: ");
		 
		 for(int i=0; i<row2*col2; i++)  
		 {     
		 array2[i]=input.nextInt();   //reading array elements from the user
		 }  
		
		 
		 Matrix m1=new Matrix(row1,col1);
		 m1.setNumbers(array1);
		 System.out.println("printing the first Matrix: ");
		 m1.print(m1);
		 
		 System.out.println("============================ ");
		 
		 Matrix m2=new Matrix(row2,col2);
		 m2.setNumbers(array2);
		 System.out.println("printing the second Matrix: ");
		 m2.print(m2);
		 
		 System.out.println("============================ ");
		 Matrix m3=new Matrix(row1,col1);
		 // returns the calling matrix if addition fails 
		 m3= m1.Add(m2);
		 System.out.println("printing the Matrix after addition: ");
		 m3.print(m3);
		 
		 System.out.println("============================ ");
		 
		 Matrix m4=new Matrix(row1,col1);
		 m4=m1.transpose(m1);
		 System.out.println("printing the first Matrix after transpose: ");
		 m4.print(m4);
		 
		 System.out.println("============================ ");
		 
		 Matrix m5=new Matrix(row1,col1);
		 m5=m2.transpose(m2);
		 System.out.println("printing the second Matrix after transpose: ");
		 m5.print(m5);
		 
		 System.out.println("============================ ");
		 
		 IdentityMatrix I1=new IdentityMatrix(row1, col1);
		 I1.setNumbers(array1);
		 I1.print(I1);
		 
		 System.out.println("============================ ");
		/*
		 if(I1.setNumbers(array1) == true) // identity matrix
		 {
			 I1.transpose(I1);             // then use the transpose of the child class
			 I1.print(I1);
		 }
		 else                                 // not identity matrix
		 {
			 Matrix m6=new Matrix(row1,col1);  // use transpose of the parent
			 m6= m1.transpose(m1);
			 m6.print(m6);
		 }
		 */
		 System.out.println("============================ ");
		 
		 IdentityMatrix I2=new IdentityMatrix(row2, col2);
		 I2.setNumbers(array2);
		 I2.print(I2);
		 
		 
         System.out.println("============================ ");
		 
		// IdentityMatrix I3=new IdentityMatrix(row1, col1);
         
        // System.out.println("printing the first Identity Matrix after transpose: ");
		 I1.transpose(I1);
		 I1.print(I1);
		 
		 
	}

}
