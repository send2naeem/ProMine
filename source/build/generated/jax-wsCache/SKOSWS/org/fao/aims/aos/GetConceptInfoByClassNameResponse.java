
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
 *         &lt;element name="getConceptInfoByClassNameReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "getConceptInfoByClassNameReturn"
})
@XmlRootElement(name = "getConceptInfoByClassNameResponse")
public class GetConceptInfoByClassNameResponse {

    @XmlElement(required = true)
    protected String getConceptInfoByClassNameReturn;

    /**
     * Gets the value of the getConceptInfoByClassNameReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetConceptInfoByClassNameReturn() {
        return getConceptInfoByClassNameReturn;
    }

    /**
     * Sets the value of the getConceptInfoByClassNameReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetConceptInfoByClassNameReturn(String value) {
        this.getConceptInfoByClassNameReturn = value;
    }

}
