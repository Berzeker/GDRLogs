package fr.laposte.gdrlogs.cache;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import fr.laposte.gdrlogs.persistance.model.SysOutLog;


@Named
public class CacheManager {

	private Cache<Date, SysOutLog> cacheSysOutLog = CacheBuilder.newBuilder().maximumSize(50000).expireAfterAccess(120, TimeUnit.SECONDS).build();

	/**
	 * Constructor
	 */
	public CacheManager() {
	}

	/**
	 * @return the cacheSysOutLog
	 */
	public Cache<Date, SysOutLog> getCacheSysOutLog() {
		return cacheSysOutLog;
	}

	/**
	 * @param cacheSysOutLog the cacheSysOutLog to set
	 */
	public void setCacheSysOutLog(Cache<Date, SysOutLog> cacheSysOutLog) {
		this.cacheSysOutLog = cacheSysOutLog;
	}

}
