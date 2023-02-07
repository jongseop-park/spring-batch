package com.example.batch.scheduler;

import com.example.batch.config.BatchConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Calendar;

@Slf4j
@RequiredArgsConstructor
@Component
public class BatchScheduler {

    private BatchConfig batchConfig;
    private JobLauncher jobLauncher;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Scheduled(cron = "*/10 * * * * *")
    public void schedule1() {
//        logger.info("schedule1 동작 중 : {}", Calendar.getInstance().getTime());
        //JobParameters parameters = new JobParametersBuilder().addString("requestDate", LocalDateTime.now().toString()).toJobParameters();
        JobParameters parameters = new JobParametersBuilder().toJobParameters();

        System.out.println(batchConfig);
        System.out.println("=================");
        try {
            jobLauncher.run(batchConfig.myJob(), parameters);
        } catch(JobInstanceAlreadyCompleteException | JobExecutionAlreadyRunningException | JobParametersInvalidException | JobRestartException e) {
            log.error(e.getMessage());
        }


    }
}
