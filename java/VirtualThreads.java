import java.time.Duration;
import java.util.HashSet;

public class VirtualThreads {

    public static void main(String[] args) throws InterruptedException {
	int numTasks = 100_000;
        if (args.length > 0) {
            numTasks = Integer.parseInt(args[0]);
        }

        var threads = new HashSet<>();

        for (int i = 0; i < numTasks; i++) {
            var thread = Thread.startVirtualThread(() -> {
                try {
                    Thread.sleep(Duration.ofSeconds(10));
                } catch (InterruptedException e) {
                    // Handle exception
                }
            });
            threads.add(thread);
        }

        // Wait for all threads to complete
        for (var thread : threads) {
            thread.join();
        }

        System.out.println("All " + numTasks + " fibers complete");
    }
}
