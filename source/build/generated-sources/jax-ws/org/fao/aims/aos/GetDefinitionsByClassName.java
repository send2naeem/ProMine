
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
 *         &lt;element name="className" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="format" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="outputlang" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "className",
    "format",
    "outputlang"
})
@XmlRootElement(name = "getDefinitionsByClassName")
public class GetDefinitionsByClassName {

    @XmlElement(required = true)
    protected String ontologyName;
    @XmlElement(required = true)
    protected String className;
    @XmlElement(required = true)
    protected String format;
    @XmlElement(required = true)
    protected String outputlang;

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
     * Gets the value of the className property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets the value of the className property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClassName(String value) {
        this.className = value;
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

    /**
     * Gets the value of the outputlang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputlang() {
        return outputlang;
    }

    /**
     * Sets the value of the outputlang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputlang(String value) {
        this.outputlang = value;
    }

}
