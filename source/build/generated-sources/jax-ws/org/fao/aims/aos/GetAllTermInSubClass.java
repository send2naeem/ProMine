
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
 *         &lt;element name="clsName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OntolgoyDatabase" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "clsName",
    "ontolgoyDatabase"
})
@XmlRootElement(name = "getAllTermInSubClass")
public class GetAllTermInSubClass {

    @XmlElement(required = true)
    protected String clsName;
    @XmlElement(name = "OntolgoyDatabase", required = true)
    protected String ontolgoyDatabase;

    /**
     * Gets the value of the clsName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClsName() {
        return clsName;
    }

    /**
     * Sets the value of the clsName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClsName(String value) {
        this.clsName = value;
    }

    /**
     * Gets the value of the ontolgoyDatabase property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOntolgoyDatabase() {
        return ontolgoyDatabase;
    }

    /**
     * Sets the value of the ontolgoyDatabase property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOntolgoyDatabase(String value) {
        this.ontolgoyDatabase = value;
    }

}
