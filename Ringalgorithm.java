import java.util.*;

class RingProcess {
    int id;
    int index;
    boolean isActive;

    RingProcess(int id, int index) {
        this.id = id;
        this.index = index;
        this.isActive = true;
    }

    @Override
    public String toString() {
        return "Process[ID=" + id + ", Index=" + index + ", State=" + (isActive ? "Active" : "Inactive") + "]";
    }
}

public class Ringalgorithm {
    static List<RingProcess> processes = new ArrayList<>();
    static int coordinatorId = -1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 3: Get number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        // Step 3: Create process objects
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Process ID for process " + (i + 1) + ": ");
            int id = sc.nextInt();
            processes.add(new RingProcess(id, i));
        }

        // Step 4: Sort based on process ID
        processes.sort(Comparator.comparingInt(p -> p.id));

        // Assign index again after sort
        for (int i = 0; i < processes.size(); i++) {
            processes.get(i).index = i;
        }

        // Step 5: Set the highest ID process as inactive
        RingProcess last = processes.get(processes.size() - 1);
        last.isActive = false;
        System.out.println("Process with highest ID (" + last.id + ") is set to INACTIVE.");

        // Menu loop
        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Start Election");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the ID of the process initiating the election: ");
                    int initiatorId = sc.nextInt();
                    RingProcess initiator = findProcessById(initiatorId);
                    if (initiator != null && initiator.isActive) {
                        startElection(initiator);
                    } else {
                        System.out.println("Invalid or inactive initiator process.");
                    }
                    break;
                case 2:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    static RingProcess findProcessById(int id) {
        for (RingProcess p : processes) {
            if (p.id == id) return p;
        }
        return null;
    }

    static void startElection(RingProcess initiator) {
        System.out.println("Process " + initiator.id + " is initiating the election...");

        Set<Integer> electionIds = new HashSet<>();
        int currentIndex = initiator.index;

        do {
            currentIndex = (currentIndex + 1) % processes.size();
            RingProcess current = processes.get(currentIndex);
            if (current.isActive) {
                System.out.println("Passing election message to Process " + current.id);
                electionIds.add(current.id);
            }
        } while (currentIndex != initiator.index);

        // Include initiator's ID
        electionIds.add(initiator.id);

        // Determine new coordinator
        int newCoordinatorId = Collections.max(electionIds);
        coordinatorId = newCoordinatorId;
        System.out.println("Election complete. New Coordinator is Process " + coordinatorId);

        // Announce coordinator
        announceCoordinator();
    }

    static void announceCoordinator() {
        System.out.println("Announcing coordinator to all active processes...");
        for (RingProcess p : processes) {
            if (p.isActive) {
                System.out.println("Process " + p.id + " acknowledges new Coordinator: Process " + coordinatorId);
            }
        }
    }
}
