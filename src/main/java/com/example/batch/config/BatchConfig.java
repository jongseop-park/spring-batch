package com.example.batch.config;

import com.example.batch.util.UniqueRunIdIncrementer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;
import java.util.UUID;


@Slf4j
@Configuration
@RequiredArgsConstructor // fianl이나 @NotNull 사용 필드에 대한 생성자 자동 생성
//@EnableBatchProcessing
public class BatchConfig {

    private final JobRepository jobRepository;

    private final PlatformTransactionManager platformTransactionManager;

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

    @Bean
    public Job myJob() {
        return new JobBuilder("myJob", jobRepository)
                .start(myStep1())
                .next(myStep2())
//                .next(myStep3())
//                .next(myStep4())
                .incrementer(new UniqueRunIdIncrementer())
                .build();
    }

    @Bean
    public Step myStep1() {
        return new StepBuilder("myStep1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("=================myStep1");
                    return RepeatStatus.FINISHED;
                })
                .transactionManager(platformTransactionManager)
                .build();
    }

    @Bean
    public Step myStep2() {
        return new StepBuilder("myStep2", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("=================myStep2");
                    return RepeatStatus.FINISHED;
                })
                .transactionManager(platformTransactionManager)
                .build();
    }

    @Bean
    public Step myStep3() {
        return new StepBuilder("myStep3", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("=================myStep3");
                    return RepeatStatus.FINISHED;
                })
                .transactionManager(platformTransactionManager)
                .build();
    }

    @Bean
    public Step myStep4() {
        return new StepBuilder("myStep4", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    log.info("=================myStep4");
                    return RepeatStatus.FINISHED;
                })
                .transactionManager(platformTransactionManager)
                .build();
    }

}
