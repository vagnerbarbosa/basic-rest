package riodopeixe.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vagner
 */
@Entity
@XmlRootElement(name = "cellPhone")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRegistration implements Serializable {
  
    @Id
    @Column(name = "idproduto")
    private Integer idProduto;
    @Column(name = "idgradex")
    private Integer color;
    @Column(name = "idgradey")
    private Integer volts;
    @Column(name = "descricao")
    private String description;

    public ProductRegistration() {
    }
    
 
     /**
     *
     * @return idProduto
     */
    @XmlElement
    public Integer getIdProduto() {
        return idProduto;
    }

    /**
     *
     * @param idProduto
     */    
    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    /**
     *
     * @return color
     */    
    @XmlElement
    public Integer getColor() {
        return color;
    }

    /**
     *
     * @param color
     */    
    public void setColor(Integer color) {
        this.color = color;
    }
    
    /**
     *
     * @return volts
     */
    @XmlElement
    public Integer getVolts() {
        return volts;
    }

    /**
     *
     * @param volts
     */    
    public void setVolts(Integer volts) {
        this.volts = volts;
    }

    /**
     *
     * @return description
     */    
    @XmlElement
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */    
    public void setDescription(String description) {
        this.description = description;
    }    
    
}
