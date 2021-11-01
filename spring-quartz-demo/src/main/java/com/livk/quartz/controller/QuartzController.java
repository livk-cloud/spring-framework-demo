package com.livk.quartz.controller;

import com.livk.quartz.config.LivkQuartzScheduler;
import com.livk.quartz.scheduler.QuartzScheduler;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * QuartzController
 * </p>
 *
 * @author livk
 * @date 2021/10/25
 */
@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class QuartzController {

	private final Scheduler scheduler;

	private final LivkQuartzScheduler livkQuartzScheduler;

	@PostMapping("testTask")
	public void testTask() throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(QuartzScheduler.class).withIdentity("job1", "group1").build();
		jobDetail.getJobDataMap().put("user", "tom1");
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
		CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("job1", "group1")
				.withSchedule(cronScheduleBuilder).build();
		scheduler.scheduleJob(jobDetail, cronTrigger);
	}

	@PostMapping("livkTask")
	public void livkTask() throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(QuartzScheduler.class).withIdentity("job2", "group2").build();
		jobDetail.getJobDataMap().put("user", "tom2");
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
		CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("job2", "group2")
				.withSchedule(cronScheduleBuilder).build();
		livkQuartzScheduler.scheduleJob(jobDetail, cronTrigger);
	}

}
