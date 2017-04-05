package riodopeixe.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Classe para os objetos do tipo CellPhone, onde serão contidos os métodos
 * para o mesmo.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 23/02/2017
 *
 * @version 1.0
 */
@Entity
@Table(name="celular")
@XmlRootElement(name = "celular")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
@SequenceGenerator(name = "seq_gen", sequenceName = "celular_seq", initialValue = 1, allocationSize = 1)
public class CellPhone implements Serializable {
    
    @Id
    @Column(name = "idcelular")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_gen")
    private Integer idcelular;    
    @Column(name = "idproduto")
    private Integer idProduto;
    @Column(name = "idgradex")
    private Integer color;
    @Column(name = "idgradey")
    private Integer volts;
    @Column(name = "descricao")
    private String description;
    @CollectionTable(name = "imei_por_celular")
    @ElementCollection   
    private List<String> Imei;  

    public CellPhone() {
    }

    @XmlElement(name = "idcelular")
    public Integer getIdCelular() {
        return idcelular;
    }

    public void setIdCelular(Integer idCelular) {
        this.idcelular = idCelular;
    }
    
    /**
     *
     * @return idProduto
     */
    @XmlElement(name = "idProduto")
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
    @XmlElement(name = "cor")
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
    @XmlElement(name = "voltagem")
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
    @XmlElement(name = "descricao")
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

    /**
     *
     * @return
     */
    @XmlElement(name = "imeis")
    public List<String> getImei() {
        return Imei;
    }

    /**
     *
     * @param Imei
     */
    public void setImei(List Imei) {
        this.Imei = Imei;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.idcelular);
        hash = 79 * hash + Objects.hashCode(this.idProduto);
        hash = 79 * hash + Objects.hashCode(this.color);
        hash = 79 * hash + Objects.hashCode(this.volts);
        hash = 79 * hash + Objects.hashCode(this.description);
        hash = 79 * hash + Objects.hashCode(this.Imei);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CellPhone other = (CellPhone) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.idcelular, other.idcelular)) {
            return false;
        }
        if (!Objects.equals(this.idProduto, other.idProduto)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.volts, other.volts)) {
            return false;
        }
        if (!Objects.equals(this.Imei, other.Imei)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CellPhone{" + "idCelular=" + idcelular + ", idProduto=" + idProduto + ", color=" + color + ", volts=" + volts + ", description=" + description + ", Imei=" + Imei + '}';
    }
    
}
