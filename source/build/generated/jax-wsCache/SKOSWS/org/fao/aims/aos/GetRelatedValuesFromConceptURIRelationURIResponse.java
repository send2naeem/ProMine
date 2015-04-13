
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
 *         &lt;element name="getRelatedValuesFromConceptURIRelationURIReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "getRelatedValuesFromConceptURIRelationURIReturn"
})
@XmlRootElement(name = "getRelatedValuesFromConceptURIRelationURIResponse")
public class GetRelatedValuesFromConceptURIRelationURIResponse {

    @XmlElement(required = true)
    protected String getRelatedValuesFromConceptURIRelationURIReturn;

    /**
     * Gets the value of the getRelatedValuesFromConceptURIRelationURIReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetRelatedValuesFromConceptURIRelationURIReturn() {
        return getRelatedValuesFromConceptURIRelationURIReturn;
    }

    /**
     * Sets the value of the getRelatedValuesFromConceptURIRelationURIReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetRelatedValuesFromConceptURIRelationURIReturn(String value) {
        this.getRelatedValuesFromConceptURIRelationURIReturn = value;
    }

}
