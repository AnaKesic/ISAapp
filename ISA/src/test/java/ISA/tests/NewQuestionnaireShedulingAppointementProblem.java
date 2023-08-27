package ISA.tests;

import ISA.Model.DTO.AnswerComplaintDTO;
import ISA.Model.DTO.QuestionDTO;
import ISA.Model.Questionnaire;
import ISA.Service.ComplaintService;
import ISA.Service.QuestionnaireService;
import ISA.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
public class NewQuestionnaireShedulingAppointementProblem {
    @Autowired
    private QuestionnaireService questionnaireService;
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void testPessimisticLockingScenario() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(2);


        QuestionDTO dto= new QuestionDTO();
        dto.answers= new ArrayList<>();
        for(int i=0;i<39;i++){
               dto.answers.add(true);
        }
        dto.email="andy123@gmail.com";



        Future<?> future1 = executor.submit(() -> {
            System.out.println("Thread 1 Started");
            try {
                questionnaireService.submitQuestionnaire(dto.email,dto.answers);
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
                userService.IsDonorExceptable(dto.email);
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
