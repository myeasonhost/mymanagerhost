package com.eason.transfer.openapi.core.system.schedule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * 
 * 定时任务Job工厂
 * 
 */
public class SpringJobBeanFactory extends SpringBeanJobFactory {

	static final Log log = LogFactory.getLog(SpringJobBeanFactory.class);
	@Autowired
	private AutowireCapableBeanFactory beanFactory;

	/**
	 * 这里我们覆盖了super的createJobInstance方法，对其创建出来的类再进行autowire。
	 */
	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle)
			throws Exception {
		Object jobInstance = super.createJobInstance(bundle);
		beanFactory.autowireBean(jobInstance);
		return jobInstance;
	}
}