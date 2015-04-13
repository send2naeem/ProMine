
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
 *         &lt;element name="authorityFile" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dateFrom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dateTo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="integerFrom" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="integerTo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="format" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "authorityFile",
    "dateFrom",
    "dateTo",
    "integerFrom",
    "integerTo",
    "format"
})
@XmlRootElement(name = "getRejectedConcepts")
public class GetRejectedConcepts {

    @XmlElement(required = true)
    protected String authorityFile;
    @XmlElement(required = true)
    protected String dateFrom;
    @XmlElement(required = true)
    protected String dateTo;
    protected int integerFrom;
    protected int integerTo;
    @XmlElement(required = true)
    protected String format;

    /**
     * Gets the value of the authorityFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorityFile() {
        return authorityFile;
    }

    /**
     * Sets the value of the authorityFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorityFile(String value) {
        this.authorityFile = value;
    }

    /**
     * Gets the value of the dateFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateFrom() {
        return dateFrom;
    }

    /**
     * Sets the value of the dateFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateFrom(String value) {
        this.dateFrom = value;
    }

    /**
     * Gets the value of the dateTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateTo() {
        return dateTo;
    }

    /**
     * Sets the value of the dateTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateTo(String value) {
        this.dateTo = value;
    }

    /**
     * Gets the value of the integerFrom property.
     * 
     */
    public int getIntegerFrom() {
        return integerFrom;
    }

    /**
     * Sets the value of the integerFrom property.
     * 
     */
    public void setIntegerFrom(int value) {
        this.integerFrom = value;
    }

    /**
     * Gets the value of the integerTo property.
     * 
     */
    public int getIntegerTo() {
        return integerTo;
    }

    /**
     * Sets the value of the integerTo property.
     * 
     */
    public void setIntegerTo(int value) {
        this.integerTo = value;
    }

    /**
     * Gets the value of the format property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormat() {
        return format;
    }

    /**
     * Sets the value of the format property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormat(String value) {
        this.format = value;
    }

}
