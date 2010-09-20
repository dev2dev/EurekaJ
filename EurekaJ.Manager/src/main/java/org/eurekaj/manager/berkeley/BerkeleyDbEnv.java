package org.eurekaj.manager.berkeley;

import java.io.File;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.StoreConfig;

public class BerkeleyDbEnv {

	private Environment environment;
	private File dbFile;
	private EntityStore treeMenuStore;
	private EntityStore liveStatisticsStore;
	private EntityStore groupedStatisticsStore;
	private EntityStore alertStore;
	private EntityStore smtpServerStore;
	private EntityStore dashboardStore;
	private EntityStore logStore;
	
	public BerkeleyDbEnv() {
		
		BerkeleyShutdownHook shutdownHook = new BerkeleyShutdownHook();
		shutdownHook.setDbEnv(this);
		Runtime.getRuntime().addShutdownHook(shutdownHook);
		
		String dbAbsPath = System.getProperty("eurekaj.db.absPath");
		dbFile = new File(dbAbsPath);
		if (dbFile == null || !dbFile.exists()) {
			dbFile.mkdir();
		}
		
		setup();
	}
	
	public void setup() {
		EnvironmentConfig environmentconfig = new EnvironmentConfig();
		StoreConfig storeConfig = new StoreConfig(); 
		
		environmentconfig.setReadOnly(false);
		storeConfig.setReadOnly(false);
		
		environmentconfig.setAllowCreate(true);
		storeConfig.setAllowCreate(true);
		environment = new Environment(dbFile, environmentconfig);
		
		treeMenuStore = new EntityStore(environment, "TreeMenuStore", storeConfig);
		liveStatisticsStore = new EntityStore(environment, "LiveStatisticsStore", storeConfig);
		groupedStatisticsStore = new EntityStore(environment, "GroupedStatisticsStore", storeConfig);
		alertStore = new EntityStore(environment, "AlertStore", storeConfig);
		smtpServerStore = new EntityStore(environment, "SmtpServerStore", storeConfig);
		dashboardStore = new EntityStore(environment, "DashboardStore", storeConfig);
		logStore = new EntityStore(environment, "logStore", storeConfig);
	}
	
	public Environment getEnvironment() {
		return environment;
	}
	
	public EntityStore getTreeMenuStore() {
		return treeMenuStore;
	}
	
	public EntityStore getLiveStatisticsStore() {
		return liveStatisticsStore;
	}
	
	public EntityStore getAlertStore() {
		return alertStore;
	}
	
	public EntityStore getSmtpServerStore() {
		return smtpServerStore;
	}
	
	public EntityStore getDashboardStore() {
		return dashboardStore;
	}
	
	public EntityStore getGroupedStatisticsStore() {
		return groupedStatisticsStore;
	}
	
	public EntityStore getLogStore() {
		return logStore;
	}

	public void close() {
		if (treeMenuStore != null) {
			try {
				treeMenuStore.close();
			} catch (DatabaseException dbe) {
				System.err.println("Error closing treeMenuStore" + dbe.toString());
                dbe.printStackTrace();
			}
		}
		
		if (liveStatisticsStore != null) {
			try {
				liveStatisticsStore.close();
			} catch (DatabaseException dbe) {
				System.err.println("Error closing treeMenuStore" + dbe.toString());
                dbe.printStackTrace();
			}
		}
		
		if (groupedStatisticsStore != null) {
			try {
				groupedStatisticsStore.close();
			} catch (DatabaseException dbe) {
				System.err.println("Error closing groupedStatisticsStore" + dbe.toString());
                dbe.printStackTrace();
			}
		}
		
		if (alertStore != null) {
			try {
				alertStore.close();
			} catch (DatabaseException dbe) {
				System.err.println("Error closing alertStore" + dbe.toString());
                dbe.printStackTrace();
			}
		}
		
		if (smtpServerStore != null) {
			try {
				smtpServerStore.close();
			} catch (DatabaseException dbe) {
				System.err.println("Error closing smtpServerStore" + dbe.toString());
                dbe.printStackTrace();
			}
		}
		
		if (dashboardStore != null) {
			try {
				dashboardStore.close();
			} catch (DatabaseException dbe) {
				System.err.println("Error closing dashboardStore" + dbe.toString());
                dbe.printStackTrace();
			}
		}
		
		if (logStore != null) {
			try {
				logStore.close();
			} catch (DatabaseException dbe) {
				System.err.println("Error closing logStore" + dbe.toString());
                dbe.printStackTrace();
			}
		}
		
		if (environment != null) {
            try {
            	environment.close();
            } catch(DatabaseException dbe) {
                System.err.println("Error closing environment" + dbe.toString());
                dbe.printStackTrace();
            }
        }
	}
}