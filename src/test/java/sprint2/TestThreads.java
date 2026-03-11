package sprint2;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.SafetyDepositBox;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.SafetyDepositBoxService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestThreads {
    SafetyDepositBoxService safetyDepositBoxService;
    AtomicInteger waitCount;
    List<Thread> threads;

    @BeforeEach
    public void setup_config(){
        safetyDepositBoxService = SafetyDepositBoxService.getUniqueInstance();
        waitCount = new AtomicInteger(0);
        threads = new ArrayList<>();
    }

    @Test
    void noThreadKeptWaiting_whenTestTwoThreadsNeitherWaits() throws InterruptedException {
        // create two threads
        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(() -> {
                long start = System.currentTimeMillis();
                SafetyDepositBox box = safetyDepositBoxService.allocateSafetyDepositBox();
                long elapsed = System.currentTimeMillis() - start;

                // if more than 5seconds, means thread is waiting since boxes will be released after 5secs
                if (elapsed >= 5000) {
                    waitCount.incrementAndGet(); // this thread had to wait
                }

                // release box after 5 secs
                try {
                    Thread.sleep(5000);
                    safetyDepositBoxService.releaseSafetyDepositBox(box);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads.add(t);
        }

        // start threads
        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        assertEquals(0, waitCount.get());
    }

    @Test
    void oneThreadKeptWaiting_whenTestThreeThreadsOneWaits() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(() -> {
                long start = System.currentTimeMillis();
                SafetyDepositBox box = safetyDepositBoxService.allocateSafetyDepositBox();
                long elapsed = System.currentTimeMillis() - start;

                if (elapsed >= 5000) {
                    waitCount.incrementAndGet(); // this thread had to wait
                }

                try {
                    Thread.sleep(5000);
                    safetyDepositBoxService.releaseSafetyDepositBox(box);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            threads.add(t);
        }

        // start threads
        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        assertEquals(1, waitCount.get());
    }
}
