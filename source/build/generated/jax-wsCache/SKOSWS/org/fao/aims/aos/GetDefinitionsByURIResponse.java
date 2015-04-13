
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
 *         &lt;element name="getDefinitionsByURIReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "getDefinitionsByURIReturn"
})
@XmlRootElement(name = "getDefinitionsByURIResponse")
public class GetDefinitionsByURIResponse {

    @XmlElement(required = true)
    protected String getDefinitionsByURIReturn;

    /**
     * Gets the value of the getDefinitionsByURIReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetDefinitionsByURIReturn() {
        return getDefinitionsByURIReturn;
    }

    /**
     * Sets the value of the getDefinitionsByURIReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetDefinitionsByURIReturn(String value) {
        this.getDefinitionsByURIReturn = value;
    }

}
