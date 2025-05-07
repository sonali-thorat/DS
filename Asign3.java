import mpi.*;
public class Asign3 {
   
      public static void main(String[] args) throws Exception {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int n = 8; // total array size
        int[] array = new int[n];

        // Master initializes the array
        if (rank == 0) {
            System.out.println("Master: Initializing array of size " + n);
            for (int i = 0; i < n; i++) {
                array[i] = i + 1; // [1, 2, 3, ..., n]
            }
        }

        int chunk = n / size;
        int[] subArray = new int[chunk];

        // Scatter array chunks to all processes
        MPI.COMM_WORLD.Scatter(array, 0, chunk, MPI.INT, subArray, 0, chunk, MPI.INT, 0);

        // Each process calculates local sum
        int localSum = 0;
        for (int i = 0; i < chunk; i++) {
            localSum += subArray[i];
        }

        System.out.println("Process " + rank + " calculated local sum: " + localSum);

        // Gather all local sums to master
        int[] globalSums = new int[size];
        MPI.COMM_WORLD.Gather(new int[]{localSum}, 0, 1, MPI.INT, globalSums, 0, 1, MPI.INT, 0);

        // Master computes the final sum
        if (rank == 0) {
            int totalSum = 0;
            for (int sum : globalSums) {
                totalSum += sum;
            }
            System.out.println("Master: Final sum is " + totalSum);
        }

        MPI.Finalize();
    }
  }
  
  
 /* parthsetup -  in terminal -export MPJ_HOME =path
                              - export PATH =$MPJ_HOME/bin:$PATH
			     in window -system variable =  MPJ_HOME -path of mpj
                            in path = %MPJ_HOME%\bin
                            cmd -echo %MPJ_HOME%			  
							  
  how to run -
   in window - javac -cp .;C:\Users\cnd21\Downloads\mpj-v0_44\mpj-v0_44\lib\mpj.jar Asign3.java
               mpjrun.bat -np 4 Asign3
   
   in linux -
     javac -cp .:$MPJ_HOME/lib/mpj.jar Asign3.java
	 mpjrun.sh -np 4 Asign3
*/
      
