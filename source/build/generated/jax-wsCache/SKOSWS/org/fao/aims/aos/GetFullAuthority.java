
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
 *         &lt;element name="ontologyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="searchString" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="searchMode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lang" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "ontologyName",
    "searchString",
    "searchMode",
    "lang",
    "format"
})
@XmlRootElement(name = "getFullAuthority")
public class GetFullAuthority {

    @XmlElement(required = true)
    protected String ontologyName;
    @XmlElement(required = true)
    protected String searchString;
    @XmlElement(required = true)
    protected String searchMode;
    @XmlElement(required = true)
    protected String lang;
    @XmlElement(required = true)
    protected String format;

    /**
     * Gets the value of the ontologyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOntologyName() {
        return ontologyName;
    }

    /**
     * Sets the value of the ontologyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOntologyName(String value) {
        this.ontologyName = value;
    }

    /**
     * Gets the value of the searchString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchString() {
        return searchString;
    }

    /**
     * Sets the value of the searchString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchString(String value) {
        this.searchString = value;
    }

    /**
     * Gets the value of the searchMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchMode() {
        return searchMode;
    }

    /**
     * Sets the value of the searchMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchMode(String value) {
        this.searchMode = value;
    }

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
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
