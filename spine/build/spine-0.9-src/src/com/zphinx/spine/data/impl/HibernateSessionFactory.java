package com.zphinx.spine.data.impl;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.zphinx.spine.Universal;
import com.zphinx.spine.message.Messages;

/**
 * Configures and provides access to Hibernate sessions, tied to the current thread of execution. Follows the Thread Local Session pattern.
 */
class HibernateSessionFactory {

    /**
     * The <code>Log</code> instance for this application.
     */

    private static Logger log = Universal.getLogger(HibernateSessionFactory.class.getName()); //$NON-NLS-1$

    /**
     * Location of hibernate.cfg.xml file. NOTICE: Location should be on the classpath as Hibernate uses #resourceAsStream style lookup for its configuration file. i.e The config file should be located in a Java package - the default location is the default Java package.<br>
     * <br>
     * Examples: <br>
     * <code>CONFIG_FILE_LOCATION = "/hibernate.conf.xml". 
     * CONFIG_FILE_LOCATION = "/com/foo/bar/myhiberstuff.conf.xml".</code>
     */
    private static String CONFIG_FILE_LOCATION = Messages.getString("HibernateSessionFactory.config.file"); //$NON-NLS-1$

    /** Holds a single instance of Session */
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

    /** The single instance of hibernate configuration */
    private static Configuration cfg = null;

    /**
     * The configuration file for this instance
     */
    private static String configFile = CONFIG_FILE_LOCATION;

    /** The single instance of hibernate SessionFactory */
    private static SessionFactory sessionFactory = null;

    /**
     * The single instance of this object
     */
    private static HibernateSessionFactory hsf = null;

    /**
     * An arbitary object used for sysnchronization
     */
    private static Object opSess = new Object();

    /**
     * An arbitary object used for sysnchronization
     */
    private static Object clSess = new Object();

    /**
     * Default constructor.
     */
    private HibernateSessionFactory() {

        super();
        setConfigFile(CONFIG_FILE_LOCATION);
        cfg = new Configuration();
    }

    /**
     * Returns the ThreadLocal Session instance. Lazy initialize the <code>SessionFactory</code> if needed.
     * 
     * @return Session
     * @throws HibernateException
     */
    public Session currentSession() throws HibernateException {
        synchronized (opSess){
            Session session = threadLocal.get();
            if(session == null){
                if(sessionFactory == null){
                    try{
                        cfg.configure(CONFIG_FILE_LOCATION);
                        sessionFactory = cfg.buildSessionFactory();
                    }
                    catch (Exception e){
                        log.debug(Messages.getString("HibernateSessionFactory.error")); //$NON-NLS-1$
                    }
                }
                session = sessionFactory.openSession();
                threadLocal.set(session);
            }

            return session;
        }
    }

    /**
     * Rebuild hibernate session factory
     */
    public void rebuildSessionFactory() {
        try{
            cfg.configure(configFile);
            sessionFactory = cfg.buildSessionFactory();
        }
        catch (Exception e){
            log.debug(Messages.getString("HibernateSessionFactory.error"));

        }
    }

    /**
     * Close the single hibernate session instance.
     * 
     * @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        synchronized (clSess){
            Session session = threadLocal.get();
            threadLocal.set(null);
            if(session != null){
                session.close();
            }
        }
    }

    /**
     * Creates and returns the only instance of this object
     * 
     * @return The only instance of this object
     */
    public static HibernateSessionFactory getInstance() {
        return init();
    }

    /**
     * Creates and returns the only instance of this object
     * 
     * @return The only instance of this object
     */
    private static HibernateSessionFactory init() {
        if(hsf == null){
            hsf = new HibernateSessionFactory();
        }
        return hsf;
    }

    /**
     * return session factory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Sets the configuration file session factory will be rebuilded in the next call
     */
    public void setConfigFile(String configFile) {
        HibernateSessionFactory.configFile = configFile;
        sessionFactory = null;
    }

    /**
     * return hibernate configuration
     */
    public Configuration getConfiguration() {
        return cfg;
    }

}
