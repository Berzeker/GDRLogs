package fr.laposte.gdrlogs.writer;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.batch.item.ItemWriter;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.cache.Cache;

import fr.laposte.gdrlogs.cache.CacheManager;
import fr.laposte.gdrlogs.persistance.dao.SysLogDao;
import fr.laposte.gdrlogs.persistance.model.SysOutLog;


public class SystemLogWriter implements ItemWriter<SysOutLog> {

	@Inject
	CacheManager cacheManager;
	
	@Inject
	SysLogDao sysLogDao;
	
	@Transactional
	public void write(List<? extends SysOutLog> items) throws Exception {
		
		if (!items.isEmpty()) {
			
			Set<Date> setDateApparition = new HashSet<Date>();
			//Récupération des dates d'apparition
			for (SysOutLog sysOutLog : items) {
				setDateApparition.add(sysOutLog.getDateApparition());
			}
			
			Cache<Date, SysOutLog> cacheSysOutLog = cacheManager.getCacheSysOutLog();
			Map<Date, SysOutLog> sysOutLogCached = cacheSysOutLog.getAllPresent(setDateApparition);
			
			List<? extends SysOutLog> listeSysOutLog = sysLogDao.save(sysOutLogCached.values());
			sysLogDao.flush();
			
			for (SysOutLog sysOutLog : listeSysOutLog) {
					cacheSysOutLog.put(sysOutLog.getDateApparition(), sysOutLog);
			}
		}
	}
}
