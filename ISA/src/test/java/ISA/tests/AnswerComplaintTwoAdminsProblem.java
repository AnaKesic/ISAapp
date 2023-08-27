package ISA.tests;

import ISA.Model.DTO.AnswerComplaintDTO;
import ISA.Service.ComplaintService;
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
public class AnswerComplaintTwoAdminsProblem {



        @Autowired
        private ComplaintService complaintService;

        @Test
        @Transactional
        public void testPessimisticLockingScenario() throws Exception {
            ExecutorService executor = Executors.newFixedThreadPool(2);


            AnswerComplaintDTO dto1= new AnswerComplaintDTO();
            dto1.adminEmail="admin@mail.com";
            dto1.answer="Odgovor1";
            dto1.complaintId=1l;



            Future<?> future1 = executor.submit(() -> {
                System.out.println("Thread 1 Started");
                try {
                    complaintService.Answer(dto1);
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
                    complaintService.Answer(dto1);
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

