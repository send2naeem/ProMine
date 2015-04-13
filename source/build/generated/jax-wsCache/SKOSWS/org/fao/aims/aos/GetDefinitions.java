
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
 *         &lt;element name="termcode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="languagecode" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "termcode",
    "languagecode"
})
@XmlRootElement(name = "getDefinitions")
public class GetDefinitions {

    protected int termcode;
    @XmlElement(required = true)
    protected String languagecode;

    /**
     * Gets the value of the termcode property.
     * 
     */
    public int getTermcode() {
        return termcode;
    }

    /**
     * Sets the value of the termcode property.
     * 
     */
    public void setTermcode(int value) {
        this.termcode = value;
    }

    /**
     * Gets the value of the languagecode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguagecode() {
        return languagecode;
    }

    /**
     * Sets the value of the languagecode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguagecode(String value) {
        this.languagecode = value;
    }

}
