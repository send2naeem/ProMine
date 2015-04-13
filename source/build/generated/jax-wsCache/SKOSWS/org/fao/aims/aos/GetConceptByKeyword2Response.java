
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
 *         &lt;element name="getConceptByKeyword2Return" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "getConceptByKeyword2Return"
})
@XmlRootElement(name = "getConceptByKeyword2Response")
public class GetConceptByKeyword2Response {

    @XmlElement(required = true)
    protected String getConceptByKeyword2Return;

    /**
     * Gets the value of the getConceptByKeyword2Return property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetConceptByKeyword2Return() {
        return getConceptByKeyword2Return;
    }

    /**
     * Sets the value of the getConceptByKeyword2Return property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetConceptByKeyword2Return(String value) {
        this.getConceptByKeyword2Return = value;
    }

}
