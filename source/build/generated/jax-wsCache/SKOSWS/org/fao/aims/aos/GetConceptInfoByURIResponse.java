
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
 *         &lt;element name="getConceptInfoByURIReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "getConceptInfoByURIReturn"
})
@XmlRootElement(name = "getConceptInfoByURIResponse")
public class GetConceptInfoByURIResponse {

    @XmlElement(required = true)
    protected String getConceptInfoByURIReturn;

    /**
     * Gets the value of the getConceptInfoByURIReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetConceptInfoByURIReturn() {
        return getConceptInfoByURIReturn;
    }

    /**
     * Sets the value of the getConceptInfoByURIReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetConceptInfoByURIReturn(String value) {
        this.getConceptInfoByURIReturn = value;
    }

}
