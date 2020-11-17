package com.sinosoft.surrender.common.lock.bo.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.common.exception.LockFailedException;
import com.sinosoft.surrender.common.lock.bo.LockBO;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.db.dao.YdLockDAO;
import com.sinosoft.surrender.db.model.YdLock;
import com.sinosoft.surrender.db.model.YdLockKey;

@Component
public class LockBOImpl implements LockBO {
	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private YdLockDAO ydLockDAO;

	@Override
	public void lock(String lockType, String lockKey, String lcoker) {
		Long startTime = System.currentTimeMillis();
		YdLock ldLock = new YdLock();
		ldLock.setLockey(lockKey);
		ldLock.setLocktype(lockType);
		ldLock.setLocktime(DateUtil.getNowDateStr("yyyy-MM-dd HH:mm:ss"));
		ldLock.setLocker(lcoker);
		int i = 0;
		while (i++ < 5) {
			try {
				ydLockDAO.insert(ldLock);
				break;
			} catch (Exception e) {
				log.error(e);
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				log.error(e);
			}
		}

		if (i == 6) {
			throw new LockFailedException("加锁失败,请检查原因!");
		}
		log.info("加锁" + lockKey + "耗时：" + (System.currentTimeMillis() - startTime));
	}

	@Override
	public void unLock(String lockType, String lockKey) {
		Long startTime = System.currentTimeMillis();
		YdLockKey ldLockKey = new YdLockKey();
		ldLockKey.setLockey(lockKey);
		ldLockKey.setLocktype(lockType);
		
		int i = 0;
		while (i++ < 5) {
			try {
				ydLockDAO.deleteByPrimaryKey(ldLockKey);
				break;
			} catch (Exception e) {
				log.error(e);
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				log.error(e);
			}
		}

		if (i == 6) {
			throw new LockFailedException("解锁锁失败,请检查原因!");
		}
		
		log.info("解锁" + lockKey + "耗时：" + (System.currentTimeMillis() - startTime));
	}

}
