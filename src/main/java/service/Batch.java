package service;

import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.batch.operations.JobOperator;
import javax.batch.operations.JobSecurityException;
import javax.batch.operations.JobStartException;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Yongyi Jin
 */
//@Singleton
//@Startup
//public class Batch {
//
//    @PostConstruct
//    void init() {
//        System.out.println("--- BATCH INITILIZED ---");
//        JobOperator jo = BatchRuntime.getJobOperator();
//        System.out.println("--- JOBS FOUND: " + jo.getJobNames() + " ---");
//        Object[] jobs = jo.getJobNames().toArray();
//        Properties props = new Properties();
//        System.out.println("--- STARTING JOB: " + jobs[0].toString() + " ---");
//        long jid = 0;
//        
//        try {
//            jid = jo.start("kwetterJob", props);
//            System.out.println("--- JOB STARTED WITH ID: " + jid + " ---");
//        } catch (JobStartException | JobSecurityException ex) {
//            System.out.println("--- JOB START FAILED ---");
//            System.out.println("--- STACKTRACE ---");
//            ex.printStackTrace();
//            System.out.println("--- END STACKTRACE---");
//        }
//    }
//}
