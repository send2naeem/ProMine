
package org.fao.aims.aos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ontologyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="conceptURI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lang" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="descriptor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="format" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ontologyName",
    "conceptURI",
    "lang",
    "descriptor",
    "format",
    "status"
})
@XmlRootElement(name = "getTermsByURI")
public class GetTermsByURI {

    @XmlElement(required = true)
    protected String ontologyName;
    @XmlElement(required = true)
    protected String conceptURI;
    @XmlElement(required = true)
    protected String lang;
    @XmlElement(required = true)
    protected String descriptor;
    @XmlElement(required = true)
    protected String format;
    @XmlElement(required = true)
    protected String status;

    /**
     * Gets the value of the ontologyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOntologyName() {
        return ontologyName;
    }

    /**
     * Sets the value of the ontologyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOntologyName(String value) {
        this.ontologyName = value;
    }

    /**
     * Gets the value of the conceptURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConceptURI() {
        return conceptURI;
    }

    /**
     * Sets the value of the conceptURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConceptURI(String value) {
        this.conceptURI = value;
    }

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Gets the value of the descriptor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescriptor() {
        return descriptor;
    }

    /**
     * Sets the value of the descriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescriptor(String value) {
        this.descriptor = value;
    }

    /**
     * Gets the value of the format property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the value of the format property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormat(String value) {
        this.format = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
