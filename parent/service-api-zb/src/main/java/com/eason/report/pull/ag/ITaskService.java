package com.eason.report.pull.ag;

import com.eason.report.pull.ag.exception.ServiceException;

/**
 * @apiDefine task 任务API
 */
public interface ITaskService {

	/**
	 * @apiVersion 1.0.0
	 * @apiGroup task
	 * @apiPermission Android/IOS
	 * @api {GET} /task/resetTrySee  重置试看时间
	 * @apiName resetTrySee
	 *
	 * @apiDescription
	 * > 每天凌晨4点，重置试看</br>
	 * @apiSuccess {String} result 重置成功或者失败
	 *
	 */
	public String resetTrySee() throws ServiceException;



}
