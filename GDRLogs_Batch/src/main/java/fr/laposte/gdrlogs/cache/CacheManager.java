package fr.laposte.gdrlogs.cache;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import fr.laposte.gdrlogs.persistance.model.SysOutLog;


@Named
@Data
@NoArgsConstructor
public class CacheManager {
	
	private Cache<Date, SysOutLog> cacheSysOutLog = CacheBuilder.newBuilder().maximumSize(10000).expireAfterAccess(120, TimeUnit.SECONDS).build();

}
