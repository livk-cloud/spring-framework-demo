package com.livk.job.job;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

/**
 * <p>
 * LivkElasticJob
 * </p>
 *
 * @author livk
 * @date 2021/10/25
 */
@Slf4j
@Component
public class LivkElasticJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("{} {}", shardingContext.getShardingTotalCount(), shardingContext.getShardingItem());
    }
}
