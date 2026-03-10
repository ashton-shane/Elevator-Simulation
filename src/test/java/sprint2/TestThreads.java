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

    @BeforeEach
    public void setup_config(){
        safetyDepositBoxService = SafetyDepositBoxService.getUniqueInstance();
        safetyDepositBoxService.setTotalNumberOfSafetyDepositBoxes(5);
    }

    @Test
    void noThreadKeptWaiting_whenTestTwoThreadsNeitherWaits() throws InterruptedException {
        AtomicInteger waitCount = new AtomicInteger(0);
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Thread t = new Thread(() -> {
                SafetyDepositBox box = safetyDepositBoxService.getReleasedSafetyDepositBox();

                if (box == null) {
                    waitCount.incrementAndGet();
                    return;
                }

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    box.setAllotted(false);
                }
            });
            threads.add(t);
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();
        assertEquals(0, waitCount.get());
    }

    @Test
    void oneThreadKeptWaiting_whenTestThreeThreadsOneWaits() throws InterruptedException {
        AtomicInteger waitCount = new AtomicInteger(0);
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(() -> {
                SafetyDepositBox box = safetyDepositBoxService.getReleasedSafetyDepositBox();

                if (box == null) {
                    waitCount.incrementAndGet();
                    return;
                }

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    box.setAllotted(false);
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
