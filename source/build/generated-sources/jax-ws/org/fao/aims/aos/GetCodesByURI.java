
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
 *         &lt;element name="Ontology" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ConceptURI" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CodeName" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "ontology",
    "conceptURI",
    "codeName"
})
@XmlRootElement(name = "getCodesByURI")
public class GetCodesByURI {

    @XmlElement(name = "Ontology", required = true)
    protected String ontology;
    @XmlElement(name = "ConceptURI", required = true)
    protected String conceptURI;
    @XmlElement(name = "CodeName", required = true)
    protected String codeName;

    /**
     * Gets the value of the ontology property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOntology() {
        return ontology;
    }

    /**
     * Sets the value of the ontology property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOntology(String value) {
        this.ontology = value;
    }

    /**
     * Gets the value of the conceptURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConceptURI() {
        return conceptURI;
    }

    /**
     * Sets the value of the conceptURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConceptURI(String value) {
        this.conceptURI = value;
    }

    /**
     * Gets the value of the codeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeName() {
        return codeName;
    }

    /**
     * Sets the value of the codeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeName(String value) {
        this.codeName = value;
    }

}
