package com.karlchu.mo.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created with IntelliJ IDEA.
 * User: HauKute
 * Date: 9/3/15
 * Time: 4:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class XMLUtil {
    private transient static final Log log = LogFactory.getLog(XMLUtil.class);
    public static String transform(File xslFile, String inputXmlText) throws TransformerConfigurationException, TransformerException {
        TransformerFactory tFactory = TransformerFactory.newInstance();

        // Use the TransformerFactory to instantiate a Transformer that will work with
        // the stylesheet you specify. This method call also processes the stylesheet
        // into a compiled Templates object.
        Transformer transformer = tFactory.newTransformer(new StreamSource(xslFile));
        StringWriter writer = new StringWriter();

        transformer.transform(new StreamSource(new StringReader(inputXmlText)), new StreamResult(writer));
        return writer.toString();
    }

    public static String transform(String xslFile, String inputXmlText) throws TransformerConfigurationException, TransformerException {
        return transform(new File(xslFile), inputXmlText);
    }

    public static String transform(Transformer transformer, String inputXmlText) throws TransformerException {
        //log.debug("Input XML===\r\n" + inputXmlText);
        StringWriter writer = new StringWriter();
        transformer.transform(new StreamSource(new StringReader(inputXmlText)), new StreamResult(writer));
        //log.debug("Output XML===\r\n" + writer.toString());
        return writer.toString();
    }
    /**
     * Create new Transformer from xslt file for transformation xml content.
     *
     * @param xslFile input xsl file.
     * @return transformation result text.
     *
     * @throws javax.xml.transform.TransformerConfigurationException
     */
    public static Transformer newTransformer(File xslFile) throws TransformerConfigurationException {
        log.debug(xslFile.getPath() + "|" + xslFile.getName());
        TransformerFactory tFactory = TransformerFactory.newInstance();

        // Use the TransformerFactory to instantiate a Transformer that will work with
        // the stylesheet you specify. This method call also processes the stylesheet
        // into a compiled Templates object.
        return tFactory.newTransformer(new StreamSource(xslFile));
    }

    /**
     * Create new Transformer from xslt inputstream for transformation xml content.
     *
     * @param inputStream xsl file input stream.
     * @return transformation result text.
     *
     * @throws javax.xml.transform.TransformerConfigurationException
     */
    public static Transformer newTransformer(InputStream inputStream) throws TransformerConfigurationException {
        TransformerFactory tFactory = TransformerFactory.newInstance();

        // Use the TransformerFactory to instantiate a Transformer that will work with
        // the stylesheet you specify. This method call also processes the stylesheet
        // into a compiled Templates object.
        return tFactory.newTransformer(new StreamSource(inputStream));
    }
}
