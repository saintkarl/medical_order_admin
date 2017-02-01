
package com.karlchu.rest.security.xml;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.karlchu.medical.order.xml.manifest package.
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Manifest_QNAME = new QName("http://www.larchu.com/schema/rest/security", "rest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.karlchu.medical.order.xml.manifest
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link XRest }
     * 
     */
    public XRest createXRest() {
        return new XRest();
    }

    /**
     * Create an instance of {@link XInterceptUrls }
     * 
     */
    public XInterceptUrls createXInterceptUrls() {
        return new XInterceptUrls();
    }

    /**
     * Create an instance of {@link XInterceptUrls.XInterceptUrl }
     * 
     */
    public XInterceptUrls.XInterceptUrl createXInterceptUrl() {
        return new XInterceptUrls.XInterceptUrl();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XRest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "rest")
    public JAXBElement<XRest> createXRest(XRest value) {
        return new JAXBElement<>(_Manifest_QNAME, XRest.class, null, value);
    }

}
