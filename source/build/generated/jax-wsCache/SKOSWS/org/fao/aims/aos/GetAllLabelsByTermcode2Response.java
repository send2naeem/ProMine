
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
 *         &lt;element name="getAllLabelsByTermcode2Return" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "getAllLabelsByTermcode2Return"
})
@XmlRootElement(name = "getAllLabelsByTermcode2Response")
public class GetAllLabelsByTermcode2Response {

    @XmlElement(required = true)
    protected String getAllLabelsByTermcode2Return;

    /**
     * Gets the value of the getAllLabelsByTermcode2Return property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetAllLabelsByTermcode2Return() {
        return getAllLabelsByTermcode2Return;
    }

    /**
     * Sets the value of the getAllLabelsByTermcode2Return property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetAllLabelsByTermcode2Return(String value) {
        this.getAllLabelsByTermcode2Return = value;
    }

}
