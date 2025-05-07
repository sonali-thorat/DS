import java.util.*;



public class TokenRing{

	public static void main(String[] args){
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("Enter Number Of Nodes You Want In The Ring : ");
			int n = sc.nextInt();
			
			System.out.println("Ring Formed Is As Below: ");
			for(int i=0; i<n; i++){
				System.out.print(i + " ");
			}
			
			System.out.println("0");
			
			
			int choice = 0;
			int token = 0;
			
			do{
				System.out.print("Enter Sender : ");
				int sender = sc.nextInt();
				
				System.out.print("Enter Receiver : ");
				int receiver = sc.nextInt();
				
				System.out.print("Enter Data To Send : ");
				int data = sc.nextInt();
				
				
				System.out.print("Token Passing : ");
				
				for(int i=token; i<sender; i++){
					System.out.print(" " + i + "->");
				}
				
				if(receiver == sender + 1){
					System.out.println("Sender: " + sender + " Sending The Data: " + data);
					System.out.println("Receiver: " + receiver + " Received The Data: " + data);
				}
				else{
					System.out.println(" " + sender);
					System.out.println("Sender:" + sender + " Sending Data: " + data);
					
					for(int i=sender; i!=receiver; i = (i+1)%n){
						System.out.println("Data: " + data + " Forwarded By: " + i);
					}
					
					System.out.println("Receiver: " + receiver + " Received The Data: " + data);
				}

				
				
				token = sender;
				
				System.out.print("Do You Want To Send Data Again? If YES Enter 1, If NO Enter 0: ");
				choice = sc.nextInt();
			
			}while(choice == 1);
		}		
		
	}
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    


// The Token Ring Algorithm is a distributed algorithm used to achieve mutual exclusion in a distributed system. It allows processes in a distributed system to access shared resources in a mutually exclusive manner, ensuring that only one process can access the resource at any given time. The basic idea behind the Token Ring Algorithm is to pass a token (or permission) among processes in a logical ring, allowing the process holding the token to access the critical section.

// Here's a detailed explanation of the Token Ring Algorithm:

// 1. **Ring Formation**: In a distributed system with \( n \) processes, the processes are logically arranged in a ring topology. Each process is aware of its neighbors in the ring.

// 2. **Token Passing**: A token is passed among processes in the ring. Initially, the token is possessed by a designated process or is in a neutral state. The process holding the token has the permission to access the critical section.

// 3. **Requesting Access**: When a process wants to access the critical section, it sends a request message to its neighbor in the ring.

// 4. **Token Handling**:
//    - If the process receiving the request message does not currently possess the token, it forwards the request to its neighbor.
//    - If the process receiving the request message possesses the token, it:
//      - Releases the token to the requesting process.
//      - The requesting process enters the critical section to perform its task.
//      - After completing its task, the requesting process releases the token back into the ring, allowing other processes to request access to the critical section.

// 5. **Passing the Token**: After releasing the token, the process that previously held the token forwards it to its next neighbor in the ring, maintaining the circulation of the token within the ring.

// 6. **Handling Failures**: The Token Ring Algorithm should include mechanisms to handle failures such as process crashes or network partitions. In case of a failure, the token may need to be reinitialized or redistributed among the remaining processes in the ring.

// The Token Ring Algorithm ensures mutual exclusion by ensuring that only the process holding the token can access the critical section at any given time. Additionally, it provides fairness by allowing each process in the ring to have an equal opportunity to access the critical section.

// However, it's essential to consider potential challenges such as token loss, network delays, and process failures when implementing the Token Ring Algorithm in a distributed system. Proper fault tolerance and error handling mechanisms should be incorporated to ensure the reliability and robustness of the algorithm.