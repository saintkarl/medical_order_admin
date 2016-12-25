/*
 * Title:        ConfigLoader
 * Description:
 *
 * This software is published under the terms of the OpenSymphony Software
 * License version 1.1, of which a copy has been included with this
 * distribution in the LICENSE.txt file.
 */

package com.karlchu.mo.web.sitemesh;

import com.opensymphony.module.sitemesh.Config;
import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.mapper.DefaultDecorator;
import com.opensymphony.module.sitemesh.mapper.PathMapper;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The ConfigLoader reads a configuration from DB that contains Decorator definitions
 * (name, url, init-params) and path-mappings (pattern, name).
 *
 * <p>These can then be accessed by the getDecoratorByName() methods and getMappedName()
 * methods respectively.</p>
 * <p>This class is used by ConfigDecoratorMapper, and uses PathMapper for pattern matching.</p>
 *
 * @author hieu
 */
public class MyConfigLoader {
    private final String DEFAULT_DECOR_ID = "_def_";

    /**
     * State visible across threads stored in a single container so that we
     * can efficiently atomically access it with the guarantee that we wont see
     * a partially loaded configuration in the face of one thread reloading the
     * configuration while others are trying to read it.
     */
    private static class State {
        /**
         * Timestamp of the last time we checked for an update to the
         * configuration file used to rate limit the frequency at which we check
         * for efficiency.
         */
        long lastModificationCheck = System.currentTimeMillis();

        /**
         * Timestamp of the modification time of the configuration file when we
         * generated the state.
         */
        long lastModified;

        /**
         * Whether a thread is currently checking if the configuration file has
         * been modified and potentially reloading it and therefore others
         * shouldn't attempt the same till it's done.
         */
        boolean checking = false;

        Map<String, Decorator> decorators = new HashMap();
        Map<String, PathMapper> pathMapperMap = new HashMap();
    }

    /**
     * Mark volatile so that the installation of new versions is guaranteed to
     * be visible across threads.
     */
    private volatile State state;

    private File configFile = null;
    private String configFileName = null;

    private Config config = null;

    public MyConfigLoader() {
    }

    /**
     * Create new ConfigLoader using supplied File.
     */
    public MyConfigLoader(File configFile) throws ServletException {
        this.configFile = configFile;
        this.configFileName = configFile.getName();
        state = loadConfig();
    }

    /**
     * Create new ConfigLoader using supplied filename and config.
     */
    public MyConfigLoader(String configFileName, Config config) throws ServletException {
        this.config = config;
        this.configFileName = configFileName;
        if (config.getServletContext().getRealPath(configFileName) != null) {
            this.configFile = new File(config.getServletContext().getRealPath(configFileName));
        }
        state = loadConfig();
    }

    /**
     * Retrieve Decorator based on name specified in configuration file.
     * @param decorId can be null
     */
    public Decorator getDecoratorByName(String name, String decorId) throws ServletException {
        if (StringUtils.isBlank(decorId) || !state.decorators.containsKey(decorId + "_" + name)) {
            return state.decorators.get(DEFAULT_DECOR_ID + name);
        } else {
            return state.decorators.get(decorId + "_" + name);
        }
    }

    /** Get name of Decorator mapped to given path. *
     * @param decorId can be null
     */
    public String getMappedName(String path, String decorId) throws ServletException {
        if (StringUtils.isBlank(decorId) || !state.pathMapperMap.containsKey(decorId + "_")) {
            return state.pathMapperMap.get(DEFAULT_DECOR_ID).get(path);
        } else {
            return state.pathMapperMap.get(decorId + "_").get(path);
        }
    }

    /**
     * Load configuration from file.
     */
    private State loadConfig() throws ServletException {
        // The new state which we build up and atomically replace the old state
        // with atomically to avoid other threads seeing partial modifications.
        State newState = new State();
        try {
            // Build a document from the file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document;
            if (configFile != null && configFile.canRead()) {
                // Keep time we read the file to check if the file was modified
                newState.lastModified = configFile.lastModified();
                document = builder.parse(configFile);
            } else {
                document = builder.parse(config.getServletContext().getResourceAsStream(configFileName));
            }

            // Parse the configuration document
            parseConfig(newState, document, DEFAULT_DECOR_ID);

            return newState;
        }
        catch (ParserConfigurationException e) {
            throw new ServletException("Could not get XML parser", e);
        }
        catch (IOException e) {
            throw new ServletException("Could not read the config file: " + configFileName, e);
        }
        catch (SAXException e) {
            throw new ServletException("Could not parse the config file: " + configFileName, e);
        }
        catch (IllegalArgumentException e) {
            throw new ServletException("Could not find the config file: " + configFileName, e);
        }
    }

    public void loadConfig(String xml, String decorId) throws ServletException {
        if (StringUtils.isBlank(xml) || StringUtils.isBlank(decorId)) {
            return;
        }
        try {
            if (state == null) {
                state = new State();
            }
            // Build a document from the file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            InputSource is = new InputSource(new StringReader(xml));
            Document document = builder.parse(is);
            state.lastModified = new Date().getTime();

            // Parse the configuration document
            parseConfig(state, document, decorId + "_");

        }
        catch (ParserConfigurationException e) {
            throw new ServletException("Could not get XML parser", e);
        }
        catch (IOException e) {
            throw new ServletException("Could not read the config file: " + configFileName, e);
        }
        catch (SAXException e) {
            throw new ServletException("Could not parse the config file: " + configFileName, e);
        }
        catch (IllegalArgumentException e) {
            throw new ServletException("Could not find the config file: " + configFileName, e);
        }
    }

    private void parseConfig(State newState, Document document, String decorId) {
        Element root = document.getDocumentElement();

        // get the default directory for the decorators
        String defaultDir = getAttribute(root, "defaultdir");
        if (defaultDir == null) defaultDir = getAttribute(root, "defaultDir");

        // Get decorators
        NodeList decoratorNodes = root.getElementsByTagName("decorator");
        Element decoratorElement;

        for (int i = 0; i < decoratorNodes.getLength(); i++) {
            String name, page, uriPath = null, role = null;

            // get the current decorator element
            decoratorElement = (Element) decoratorNodes.item(i);

            if (getAttribute(decoratorElement, "name") != null) {
                // The new format is used
                name = getAttribute(decoratorElement, "name");
                page = getAttribute(decoratorElement, "page");
                uriPath = getAttribute(decoratorElement, "webapp");
                role = getAttribute(decoratorElement, "role");

                // Append the defaultDir
                if (defaultDir != null && page != null && page.length() > 0 && !page.startsWith("/")) {
                    if (page.charAt(0) == '/') page = defaultDir + page;
                    else page = defaultDir + '/' + page;
                }

                // The uriPath must begin with a slash
                if (uriPath != null && uriPath.length() > 0) {
                    if (uriPath.charAt(0) != '/') uriPath = '/' + uriPath;
                }

                // Get all <pattern>...</pattern> and <url-pattern>...</url-pattern> nodes and add a mapping
                populatePathMapper(newState, decoratorElement.getElementsByTagName("pattern"), role, name, decorId);
                populatePathMapper(newState, decoratorElement.getElementsByTagName("url-pattern"), role, name, decorId);
            } else {
                // NOTE: Deprecated format
                name = getContainedText(decoratorNodes.item(i), "decorator-name");
                page = getContainedText(decoratorNodes.item(i), "resource");
                // We have this here because the use of jsp-file is deprecated, but we still want
                // it to work.
                if (page == null) page = getContainedText(decoratorNodes.item(i), "jsp-file");
            }

            Map params = new HashMap();

            NodeList paramNodes = decoratorElement.getElementsByTagName("init-param");
            for (int ii = 0; ii < paramNodes.getLength(); ii++) {
                String paramName = getContainedText(paramNodes.item(ii), "param-name");
                String paramValue = getContainedText(paramNodes.item(ii), "param-value");
                params.put(paramName, paramValue);
            }
            storeDecorator(newState, new DefaultDecorator(decorId + name, page, uriPath, role, params));
        }

        // Get (deprecated format) decorator-mappings
        NodeList mappingNodes = root.getElementsByTagName("decorator-mapping");
        for (int i = 0; i < mappingNodes.getLength(); i++) {
            Element n = (Element) mappingNodes.item(i);
            String name = getContainedText(mappingNodes.item(i), "decorator-name");

            // Get all <url-pattern>...</url-pattern> nodes and add a mapping
            populatePathMapper(newState, n.getElementsByTagName("url-pattern"), null, name, decorId);
        }
    }

    private void populatePathMapper(State newState, NodeList patternNodes, String role, String name, String decorId) {
        for (int j = 0; j < patternNodes.getLength(); j++) {
            Element p = (Element) patternNodes.item(j);
            Text patternText = (Text) p.getFirstChild();
            if (patternText != null) {
                String pattern = patternText.getData().trim();
                if (pattern != null) {
                    if (newState.pathMapperMap.get(decorId) == null) {
                        newState.pathMapperMap.put(decorId, new PathMapper());
                    }
                    if (role != null) {
                        // concatenate name and role to allow more
                        // than one decorator per role
                        newState.pathMapperMap.get(decorId).put(name + role, pattern);
                    } else {
                        newState.pathMapperMap.get(decorId).put(name, pattern);
                    }
                }
            }
        }
    }

    private static String getAttribute(Element element, String name) {
        if (element != null && element.getAttribute(name) != null && element.getAttribute(name).trim() != "") {
            return element.getAttribute(name).trim();
        } else {
            return null;
        }
    }

    private static String getContainedText(Node parent, String childTagName) {
        try {
            Node tag = ((Element) parent).getElementsByTagName(childTagName).item(0);
            return ((Text) tag.getFirstChild()).getData();
        }
        catch (Exception e) {
            return null;
        }
    }

    private void storeDecorator(State newState, Decorator d) {
        if (d.getRole() != null) {
            newState.decorators.put(d.getName() + d.getRole(), d);
        } else {
            newState.decorators.put(d.getName(), d);
        }
    }

}