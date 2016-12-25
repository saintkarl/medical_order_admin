package com.karlchu.mo.web.listener;

import com.karlchu.mo.common.Constants;
import com.karlchu.mo.web.context.AppContext;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>StartupListener class used to initialize and database settings
 * and populate any application-wide drop-downs.
 * <p/>
 * <p>Keep in mind that this listener is executed outside of OpenSessionInViewFilter,
 * so if you're using Hibernate you'll have to explicitly initialize all loaded data at the
 * GenericDao or service level to avoid LazyInitializationException. Hibernate.initialize() works
 * well for doing this.
 *
 */
public class StartupListener implements ServletContextListener {
    private static transient final Logger log = Logger.getLogger(StartupListener.class);

    
    @SuppressWarnings("unchecked")
    public void contextInitialized(ServletContextEvent event) {
        log.debug("Initializing context...");

        ServletContext context = event.getServletContext();

        // set fields of Constants class in Application Scope
        context.setAttribute(Constants.class.getSimpleName(), createNameToValueMap());
        
        // Orion starts Servlets before Listeners, so check if the config
        // object already exists
        Map<String, Object> config = (HashMap<String, Object>) context.getAttribute(Constants.CONFIG);

        if (config == null) {
            config = new HashMap<String, Object>();
        }

        if (context.getInitParameter(Constants.CSS_THEME) != null) {
            config.put(Constants.CSS_THEME, context.getInitParameter(Constants.CSS_THEME));
        }

        ApplicationContext ctx =
                WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        AppContext.setApplicationContext(ctx);

        context.setAttribute(Constants.CONFIG, config);

        initializeApplicationSetting(context);
    }

    /**
     *
     * @param context
     */
    private void initializeApplicationSetting(ServletContext context) {
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        Map<String, String> applicationSettingMap = new HashMap<String, String>();
        context.setAttribute("applicationSettingMap", applicationSettingMap);
    }


    /**
     * Shutdown servlet context (currently a no-op method).
     *
     * @param servletContextEvent The servlet context event
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //LogFactory.release(Thread.currentThread().getContextClassLoader());
        //Commented out the above call to avoid warning when SLF4J in classpath.
        //WARN: The method class org.apache.commons.logging.impl.SLF4JLogFactory#release() was invoked.
        //WARN: Please see http://www.slf4j.org/codes.html for an explanation.
    }
    
    /**
     * Puts all public static fields via introspection into the resulting Map.
     * Uses the name of the field as key to reference it's in the Map.
     *
     * @return a Map of field names to field values of
     *         all public static fields of this class
     */
    private static Map createNameToValueMap() {
        Map result = new HashMap();
        Field[] publicFields = Constants.class.getFields();
        for (int i = 0; i < publicFields.length; i++) {
            Field field = publicFields[i];
            String name = field.getName();
            try {
                result.put(name, field.get(null));
            } catch (Exception e) {
                log.fatal(e);
            }
        }

        return result;
    }
}
