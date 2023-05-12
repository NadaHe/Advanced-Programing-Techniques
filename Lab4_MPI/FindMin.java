import java.util.*;
import mpi.*;
import java.io.*;
import java.io.IOException;
import java.io.FileReader;

public class FindMin {

	public static void main(String[] args)
			throws IOException, FileNotFoundException, ClassNotFoundException, MPIException {

		// initialize the environment and set the rank and size
		MPI.Init(args);
		int rank = MPI.COMM_WORLD.Rank();
		int size = MPI.COMM_WORLD.Size();

		int root = 0;

		int[] arrsize = new int[1];
		int[] inputarray = null;
		int[] min = new int[1];

		if (rank == root) {
			// take the input from a file
			List<Integer> listOfIntegers = new ArrayList<Integer>();

			Scanner sc = new Scanner(new FileReader("input.txt")).useDelimiter(",\\s*");
			while (sc.hasNext()) {
				Integer str = Integer.parseInt(sc.next());
				arrsize[0]++;
				listOfIntegers.add(str);
			}

			inputarray = new int[arrsize[0]];
			inputarray = listOfIntegers.stream().mapToInt(Integer::intValue).toArray();
		}
		MPI.COMM_WORLD.Bcast(arrsize, 0, 1, MPI.INT, root);
		if (rank != 0) {
			inputarray = new int[arrsize[0]];
		}

		// set the size of each chunk
		int chunksize = arrsize[0] / size;
		int[] Dummyarray = new int[chunksize];
		MPI.COMM_WORLD.Scatter(inputarray, 0, chunksize, MPI.INT, Dummyarray, 0, chunksize, MPI.INT, root);

		int[] local_min = new int[1];

		local_min[0] = Integer.MAX_VALUE;

		for (int i = 0; i < chunksize; i++) {
			if (Dummyarray[i] < local_min[0]) {
				local_min[0] = Dummyarray[i];
			}
		}
		System.out.println("local minimum: " + local_min[0] + " the rank is " + rank);

		// to calc the global min
		// MPI.COMM_WORLD.Reduce(localSum, 0, globalSum, 0, localSum.length, MPI.OBJECT,
		// MPI.PROD, 0);

		MPI.COMM_WORLD.Reduce(local_min, 0, min, 0, 1, MPI.INT, MPI.MIN, root);

		if (rank == root) {
			System.out.println("Final minimum: " + min[0]);
		}

		MPI.Finalize();
	}

}