package com.livk.jdbc.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * <p>
 * LivkPreciseShardingAlgorithm
 * </p>
 *
 * @author livk
 * @date 2021/11/2
 */
@Slf4j
public class LivkPreciseShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> preciseShardingValue) {
        String date = preciseShardingValue.getValue();
        log.info("{}", date);
        for (String tableName : availableTargetNames) {
            if (tableName.endsWith(date)) {
                return tableName;
            }
        }
        throw new IllegalArgumentException();
    }
}
