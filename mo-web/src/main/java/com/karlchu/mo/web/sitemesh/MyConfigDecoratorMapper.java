
package com.karlchu.mo.web.sitemesh;

import com.karlchu.mo.common.Constants;
import com.opensymphony.module.sitemesh.Config;
import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.DecoratorMapper;
import com.opensymphony.module.sitemesh.Page;
import com.opensymphony.module.sitemesh.mapper.AbstractDecoratorMapper;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

/**
 * Default implementation of DecoratorMapper. Reads decorators and
 * mappings from the <code>config</code> property (default '/WEB-INF/decorators.xml').
 * @author hieu
 */

public class MyConfigDecoratorMapper extends AbstractDecoratorMapper {
    private final Logger logger = Logger.getLogger(MyConfigDecoratorMapper.class);

    private MyConfigLoader configLoader = null;

    public MyConfigDecoratorMapper() {
        super();
    }

    /** Create new ConfigLoader using '/WEB-INF/decorators.xml' file. */
    public void init(Config config, Properties properties, DecoratorMapper parent) throws InstantiationException {
        super.init(config, properties, parent);
        try {
            String fileName = properties.getProperty("config", "/WEB-INF/decorators.xml");
            configLoader = new MyConfigLoader(fileName, config);

            DecoratorManagement.loadXmlFromDb(configLoader);
        }
        catch (Exception e) {
            throw new InstantiationException(e.toString());
        }

    }

    /** Retrieve {@link Decorator} based on 'pattern' tag. */
    public Decorator getDecorator(HttpServletRequest request, Page page) {
        String thisPath = request.getServletPath();

        // getServletPath() returns null unless the mapping corresponds to a servlet
        if (thisPath == null) {
            String requestURI = request.getRequestURI();
            if (request.getPathInfo() != null) {
                // strip the pathInfo from the requestURI
                thisPath = requestURI.substring(0, requestURI.indexOf(request.getPathInfo()));
            }
            else {
                thisPath = requestURI;
            }
        }
        else if ("".equals(thisPath)) {
            // in servlet 2.4, if a request is mapped to '/*', getServletPath returns null (SIM-130)
            thisPath = request.getPathInfo();
        }
        String portalCode = (String)request.getSession().getAttribute(Constants.ORG_CODE);
        String name = null;
        try {
            name = configLoader.getMappedName(thisPath, portalCode);
        }
        catch (ServletException e) {
            logger.error(e.getMessage(), e);
        }

        Decorator result = getNamedDecorator(request, name);
        return result == null ? super.getDecorator(request, page) : result;
    }

    /** Retrieve Decorator named in 'name' attribute. Checks the role if specified. */
    public Decorator getNamedDecorator(HttpServletRequest request, String name) {
        String portalCode = (String)request.getSession().getAttribute(Constants.ORG_CODE);
        Decorator result = null;
        try {
            result = configLoader.getDecoratorByName(name, portalCode);
        }
        catch (ServletException e) {
            logger.error(e.getMessage(), e);
        }

        if (result == null || (result.getRole() != null && !request.isUserInRole(result.getRole()))) {
            // if the result is null or the user is not in the role
            return super.getNamedDecorator(request, name);
        }
        else {
            return result;
        }
    }

    public MyConfigLoader getConfigLoader() {
        return configLoader;
    }
}
