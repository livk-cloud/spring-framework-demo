package com.livk.job.job;

import com.livk.job.domain.LivkBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.dataflow.job.DataflowJob;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * LivkDataFlowJob 数据流
 * </p>
 *
 * @author livk
 * @date 2021/10/25
 */
@Slf4j
@Component
public class LivkDataFlowJob implements DataflowJob<LivkBean> {

	@Override
	public List<LivkBean> fetchData(ShardingContext shardingContext) {
		var beans = new ArrayList<LivkBean>();
		var random = Math.random();
		log.info("fetchData---{}", random);
		if (random > 0.5) {
			var bean = new LivkBean();
			bean.setId(1);
			bean.setName("Livk");
			beans.add(bean);
		}
		return beans;
	}

	@Override
	public void processData(ShardingContext shardingContext, List<LivkBean> list) {
		log.info("ShardingContext:{}", shardingContext);
		log.info("List<T>:{}", list);
	}

}
