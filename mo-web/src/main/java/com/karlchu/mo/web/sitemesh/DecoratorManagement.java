package com.karlchu.mo.web.sitemesh;

import com.karlchu.mo.web.util.JdbcUtils;
import com.karlchu.mo.web.util.Config;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import java.util.List;

/**
 * Created by hieu on 10/19/2015.
 */
public class DecoratorManagement {
    private static final Logger logger = Logger.getLogger(DecoratorManagement.class);

    private static MyConfigLoader configLoader;
    private static boolean isLoaded;

    public static void loadConfig(String xml, String portalCode) throws ServletException {
       if (configLoader != null) {
           configLoader.loadConfig(xml, portalCode);
       }
    }

    public static void loadXmlFromDb(MyConfigLoader configLoader) throws ServletException {
        if (!isLoaded) {
            isLoaded = true;
            DecoratorManagement.configLoader = configLoader;
            try {
                String tableName = Config.getInstance().getProperty("portal.table.name", "MoPlatform.Theme");
                String codeColName = Config.getInstance().getProperty("portal.table.code.col.name", "Code");
                String decoratorColName = Config.getInstance().getProperty("portal.table.decorator.col.name", "DecoratorXml");
                List<PortalDecorDTO> dtoList = JdbcUtils.query(tableName, new String[]{codeColName + " AS code",
                        decoratorColName + " AS xml"}, PortalDecorDTO.class);
                if (dtoList != null && dtoList.size() > 0) {
                    for (PortalDecorDTO dto : dtoList) {
                        if (StringUtils.isNotBlank(dto.xml)) {
                            configLoader.loadConfig(dto.xml, dto.code);
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new ServletException(e);
            }
        }
    }

    public static class PortalDecorDTO {
        private String xml;
        private String code;

        public String getXml() {
            return xml;
        }

        public void setXml(String xml) {
            this.xml = xml;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
