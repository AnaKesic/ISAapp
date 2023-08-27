package ISA.tests;

import ISA.Model.DTO.SheduleAppointmentDTO;
import ISA.Service.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SheduleAppointmentTwoDonorsProblem {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    @Transactional
    public void testPessimisticLockingScenario() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);


        SheduleAppointmentDTO dto1= new SheduleAppointmentDTO();
        dto1.donorEmail="andykesic123@gmail.com";
        dto1.appointmentId=2l;

        Future<?> future1 = executor.submit(() -> {
            System.out.println("Thread 1 Started");
            try {
                appointmentService.sheduleAppointment(dto1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Thread 1 Finished");
        });

        Future<?> future2 = executor.submit(() -> {
            System.out.println("Thread 2 Started");
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
            }
            assertThrows(PessimisticLockingFailureException.class, () -> {
                appointmentService.sheduleAppointment(dto1);
            });
            System.out.println("Thread 2 Finished");
        });

        try {
            future1.get();
        } catch (Throwable throwable) {
            throw throwable;
        }

        try {
            future2.get();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            executor.shutdown();
        }
    }
}
