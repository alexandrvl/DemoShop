package com.example.demo.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.common.job.ConditionalOnJobEnabled;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;

@Slf4j
@RequiredArgsConstructor
@Component
@ConditionalOnJobEnabled
public class ItemsImportJob {

    @Transactional
    @Scheduled(cron = "${demo.job.schedule}")
    @SchedulerLock(name = "itemsImportJobLock")
    public void importItems() {
        // Dummy job, single instance in cluster. DB level lock is used.
        log.info("Started items import job");
        log.info("Finished items import job");
    }

}
