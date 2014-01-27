package org.gdrlogs.web.rest;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.dozer.Mapper;
import org.gdrlogs.web.entity.MagieLogEP;
import org.gdrlogs.web.entity.SysLogEP;

import fr.laposte.gdrlogs.persistance.dao.MagieLogDao;
import fr.laposte.gdrlogs.persistance.dao.SysLogDao;
import fr.laposte.gdrlogs.persistance.model.MagieLog;
import fr.laposte.gdrlogs.persistance.model.SysOutLog;

@Named
@NoArgsConstructor
@Data
@Path("/logs")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class LogService {

	@Inject
	private MagieLogDao magieLogDao;
	
	@Inject
	private SysLogDao sysLogDao;
	
	@Inject
	private Mapper dozerMapper;

	@GET
	@Path("/magielogs")
	public Response getMagieLogs(@QueryParam("date") String dateComptable) {
		
		//Resultat, la liste des log pour la date en parametre
		List<MagieLogEP> listeMagieLogEP = new ArrayList<MagieLogEP>();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 20);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.YEAR, 2012);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		
		Date dateJ = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date dateJ1 = calendar.getTime();
		
		for (Object[] row : magieLogDao.findByDateApparitionAfterGroupByMethode(dateJ, dateJ1)) {
			MagieLogEP magieLogEP = new MagieLogEP();
			MagieLog magieLog = (MagieLog)row[1];
			dozerMapper.map(magieLog, magieLogEP);
			
			if (magieLog.getDateApparition() != null) {
				String dateAppStr = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(magieLog.getDateApparition());
				magieLogEP.setDateApparition(dateAppStr);
			}
			
			magieLogEP.setNbreApparition(((Long)row[0]).toString());
			listeMagieLogEP.add(magieLogEP);
		}
			
		return Response.status(200).entity(listeMagieLogEP).build();
	}
	
	
	@GET
	@Path("/sysoutlogs")
	public Response getSystemOutLogs(@QueryParam("date") String dateComptable) {
		
		//Resultat, la liste des log pour la date en parametre
		List<SysLogEP> listeSysOutLogEP = new ArrayList<SysLogEP>();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 14);
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.YEAR, 2013);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.AM_PM, Calendar.AM);

		
		Date dateJ = calendar.getTime();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		Date dateJ1 = calendar.getTime();
		
		for (Object[] row : sysLogDao.findByDateApparitionAfterGroupByLog(dateJ, dateJ1)) {
			SysLogEP sysLogEP = new SysLogEP();
			SysOutLog sysOutLog = (SysOutLog)row[1];
			dozerMapper.map(sysOutLog, sysLogEP);
			
			if (sysOutLog.getDateApparition() != null) {
				String dateAppStr = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(sysOutLog.getDateApparition());
				sysLogEP.setDateApparition(dateAppStr);
			}
			
			sysLogEP.setNbreApparition(((Long)row[0]).toString());
			listeSysOutLogEP.add(sysLogEP);
		}
			
		return Response.status(200).entity(listeSysOutLogEP).build();
		
	}
	
	
	@GET
	@Path("/test")
	public Response printMessage() {
 
		String result = "Restful example : ";
 
		return Response.status(200).entity(result).build();
 
	}

	
}
