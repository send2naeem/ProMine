
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
 *         &lt;element name="getTermsByCodesAndLanguageReturn" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "getTermsByCodesAndLanguageReturn"
})
@XmlRootElement(name = "getTermsByCodesAndLanguageResponse")
public class GetTermsByCodesAndLanguageResponse {

    @XmlElement(required = true)
    protected String getTermsByCodesAndLanguageReturn;

    /**
     * Gets the value of the getTermsByCodesAndLanguageReturn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetTermsByCodesAndLanguageReturn() {
        return getTermsByCodesAndLanguageReturn;
    }

    /**
     * Sets the value of the getTermsByCodesAndLanguageReturn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetTermsByCodesAndLanguageReturn(String value) {
        this.getTermsByCodesAndLanguageReturn = value;
    }

}
