import java.util.*;

class Process {
    int id;
    boolean isActive;
    static int coordinatorId;
    static List<Process> processes = new ArrayList<>();

    Process(int id) {
        this.id = id;
        this.isActive = true;
    }

    void startElection() {
        System.out.println("Process " + id + " starts an election.");
        boolean higherResponded = false;

        for (Process p : processes) {
            if (p.id > this.id && p.isActive) {
                System.out.println("Process " + id + " sends ELECTION to Process " + p.id);
                higherResponded = true;
                p.receiveElection(this);
            }
        }

        if (!higherResponded) {
            // No higher process responded, so this becomes the coordinator
            coordinatorId = this.id;
            System.out.println("Process " + id + " becomes the coordinator.");
            announceCoordinator();
        }
    }

    void receiveElection(Process sender) {
        System.out.println("Process " + id + " received ELECTION from Process " + sender.id);
        System.out.println("Process " + id + " sends OK to Process " + sender.id);
        new Thread(() -> startElection()).start();
    }

    void announceCoordinator() {
        for (Process p : processes) {
            if (p.id != this.id && p.isActive) {
                System.out.println("Process " + id + " announces itself as coordinator to Process " + p.id);
            }
        }
    }
}

public class Bullyalgorithm {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        // Step 1: Take number of processes from user
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        // Step 2: Take process IDs from user
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Process ID for process " + (i + 1) + ": ");
            int id = sc.nextInt();
            Process.processes.add(new Process(id));
        }

        // Step 3: Sort processes by ID to easily identify the highest
        Process.processes.sort(Comparator.comparingInt(p -> p.id));

        // Set the coordinator as the process with highest ID
        Process.coordinatorId = Process.processes.get(n - 1).id;
        System.out.println("Initial Coordinator: Process " + Process.coordinatorId);

        // Simulate failure of coordinator (last process in the sorted list)
        Process.processes.get(n - 1).isActive = false;
        System.out.println("Process " + Process.coordinatorId + " has failed.");

        // Let user select which process starts the election
        System.out.print("Enter the ID of the process to start the election: ");
        int starterId = sc.nextInt();
        Process starter = findProcessById(starterId);

        if (starter != null && starter.isActive) {
            starter.startElection();
        } else {
            System.out.println("Invalid or inactive process.");
        }

        // Simulate coordinator recovery
        Thread.sleep(2000);
        Process recovered = Process.processes.get(n - 1);
        recovered.isActive = true;
        System.out.println("\nProcess " + recovered.id + " is back online.");
        recovered.startElection();

        sc.close();
    }

    static Process findProcessById(int id) {
        for (Process p : Process.processes) {
            if (p.id == id) {
                return p;
            }
        }
        return null;
    }
}
