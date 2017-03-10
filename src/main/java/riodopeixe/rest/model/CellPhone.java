package riodopeixe.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@XmlRootElement(name = "cellPhone")
@JsonIgnoreProperties(ignoreUnknown = true)
@SequenceGenerator(name = "seq_gen", sequenceName = "celular_seq", initialValue = 1, allocationSize = 1)
public class CellPhone implements Serializable {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_gen")
    private Integer id;    
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
    @ManyToMany(mappedBy = "cellPhone")
    private List<Invoice> invoices;

    public CellPhone() {
    }
    
    /**
     *
     * @return idCelular
     */    
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */      
    public void setId(Integer id) {
        this.id = id;
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

    /**
     *
     * @return
     */
    @XmlElement
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

    
    @XmlTransient
    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }  
    
}
