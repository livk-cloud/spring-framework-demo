package com.livk.quartz.config;

import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * <p>
 * LivkQuartzScheduler
 * </p>
 *
 * @author livk
 * @date 2021/10/25
 */
@Configuration
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LivkQuartzScheduler {

    private final Scheduler scheduler;

    public void startJob() throws SchedulerException {
        scheduler.start();
    }

    public boolean modifyJob(String name, String group, String time) throws SchedulerException {
        Date date = null;
        TriggerKey triggerKey = new TriggerKey(name, group);
        Trigger trigger = scheduler.getTrigger(triggerKey);
        if (trigger instanceof CronTrigger) {
            CronTrigger cronTrigger = (CronTrigger) trigger;
            String oldTime = cronTrigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(time)) {
                CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
                CronTrigger build = TriggerBuilder.newTrigger().withIdentity(name, group)
                        .withSchedule(cronScheduleBuilder).build();
                date = scheduler.rescheduleJob(triggerKey, build);
            }
        }
        return date != null;
    }

    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }

    public void pauseJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail != null) {
            scheduler.pauseJob(jobKey);
        }
    }

    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }

    public void resumeJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail != null) {
            scheduler.resumeJob(jobKey);
        }
    }

    public void deleteJob(String name, String group) throws SchedulerException {
        JobKey jobKey = new JobKey(name, group);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail != null) {
            scheduler.deleteJob(jobKey);
        }
    }

    public void scheduleJob(JobDetail jobDetail, CronTrigger cronTrigger) throws SchedulerException {
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }
}
