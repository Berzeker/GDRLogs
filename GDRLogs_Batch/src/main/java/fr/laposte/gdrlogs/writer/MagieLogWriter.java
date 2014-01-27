package fr.laposte.gdrlogs.writer;

import java.util.List;

import javax.inject.Inject;

import org.springframework.batch.item.ItemWriter;
import org.springframework.transaction.annotation.Transactional;

import fr.laposte.gdrlogs.persistance.dao.MagieLogDao;
import fr.laposte.gdrlogs.persistance.model.MagieLog;


public class MagieLogWriter implements ItemWriter<MagieLog> {

	/**
	 * Accès DAO Log
	 */
	@Inject
	private MagieLogDao magieLogDao;
	
	@Transactional
	public void write(List<? extends MagieLog> items) throws Exception {

		magieLogDao.save(items);
		magieLogDao.flush();
	}

}
