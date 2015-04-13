
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
 *         &lt;element name="simpleSearchByMode2Return" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "simpleSearchByMode2Return"
})
@XmlRootElement(name = "simpleSearchByMode2Response")
public class SimpleSearchByMode2Response {

    @XmlElement(required = true)
    protected String simpleSearchByMode2Return;

    /**
     * Gets the value of the simpleSearchByMode2Return property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimpleSearchByMode2Return() {
        return simpleSearchByMode2Return;
    }

    /**
     * Sets the value of the simpleSearchByMode2Return property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimpleSearchByMode2Return(String value) {
        this.simpleSearchByMode2Return = value;
    }

}
