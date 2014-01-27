package fr.laposte.gdrlogs;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

	public static void main(String[] args) {
		
		String[] springConfig = {
				"spring/config/Batch-Context.xml"
		};
		

		
		//"spring/jobs/SystemLog-Job.xml"
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("logsJob");
		//Job job2 = (Job) context.getBean("systemLogJob");
		
		try {

			System.out.println("Start : " + (new Date()));
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());
			System.out.println("Fin : " + (new Date()));
			
//			JobExecution execution2 = jobLauncher.run(job2, new JobParameters());
//			System.out.println("Exit Status : " + execution2.getStatus());
	 
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
		System.out.println("Done");
	}

}
