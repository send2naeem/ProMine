
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
 *         &lt;element name="uriStart" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="numResult" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "uriStart",
    "numResult"
})
@XmlRootElement(name = "getListofURI")
public class GetListofURI {

    @XmlElement(required = true)
    protected String ontologyName;
    @XmlElement(required = true)
    protected String uriStart;
    @XmlElement(required = true)
    protected String numResult;

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
     * Gets the value of the uriStart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUriStart() {
        return uriStart;
    }

    /**
     * Sets the value of the uriStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUriStart(String value) {
        this.uriStart = value;
    }

    /**
     * Gets the value of the numResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumResult() {
        return numResult;
    }

    /**
     * Sets the value of the numResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumResult(String value) {
        this.numResult = value;
    }

}
