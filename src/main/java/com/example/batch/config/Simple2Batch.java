package com.example.batch.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
@RequiredArgsConstructor
//@EnableBatchProcessing
public class Simple2Batch {


    /* 의존성 주입 방법 3가지
        필드 주입(Field Injection)
        @Autowired
        - 의존 관계를 주입하는 방법
        - 코드가 간결해지지만 외부에서 접근이 불가능한 단점이 존재
        - 테스트 코드(TDD) 작성을 위해 사용을 지양

        세터 주입(Setter Injection)
        @Autowired
        public void setA(A a) {this.a = a;}

        생성자 주입(Constructor Injection)
        private final A a;
        public A
        - 필드에 final을 사용하여 immutable(변경 불가능) 적용 가능

    */
    public Simple2Batch(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    private JobRepository jobRepository;



    @Bean
    public Job myJob(Step myStep1, Step myStep2) {
        System.out.println("this is job");
        return new JobBuilder("myJob", this.jobRepository)
                .start(myStep1)
                .next(myStep2)
                .build();
    }

    @Bean
    public Step myStep1(Tasklet myTasklet1, PlatformTransactionManager transactionManager) {
        System.out.println("this is step1");
        return new StepBuilder("myStep1", this.jobRepository)
                .tasklet(myTasklet1, transactionManager)
                .build();
    }

    @Bean
    public Step myStep2(Tasklet myTasklet2, PlatformTransactionManager transactionManager) {
        System.out.println("this is step2");
        return new StepBuilder("myStep2", this.jobRepository)
                .tasklet(myTasklet2, transactionManager)
                .build();
    }

    @Bean
    public Tasklet myTasklet1() {
        System.out.println(
                """
                        this is myTasklet1
                        """
        );
        // Step에서는 Tasklet을 무한 반복 시킨다. 그래서 RepeatStatus을 null || RepeatStatus.FINISHED로 주어야 한번 실행하고 끝난다.
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println(
                        """
                                myTasklet1
                                ==============================================
                                >> contribution = %s
                                >> chunkContext = %s
                                ==============================================
                                """.formatted(contribution, chunkContext)
                );
                return RepeatStatus.FINISHED;
            }
        };
    }

    @Bean
    public Tasklet myTasklet2() {
        System.out.println("this is taskLet2");
        // Step에서는 Tasklet을 무한 반복 시킨다. 그래서 RepeatStatus을 null || RepeatStatus.FINISHED로 주어야 한번 실행하고 끝난다.
        return (contribution, chunkContext) -> {
            System.out.println("test2");
            System.out.println(
                    """
                            myTasklet2
                            ==============================================
                            >> contribution = %s
                            >> chunkContext = %s
                            ==============================================
                            """.formatted(contribution, chunkContext)
            );
            return RepeatStatus.FINISHED;
        };
    }
    /* STEP
     * Tasklet | ItemReader, ItemProcessor, ItemWriter
     * */
}
