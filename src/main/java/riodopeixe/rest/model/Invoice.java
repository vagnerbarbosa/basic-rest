package riodopeixe.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe para os objetos do tipo Invoice, onde serão contidos os métodos
 * para o mesmo.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 24/02/2017
 *
 * @version 2.0
 */
@Entity
@Table(name="notafiscal")
@XmlRootElement(name = "notafiscal")
@JsonIgnoreProperties(ignoreUnknown = true)
@SequenceGenerator(name = "seq_gen", sequenceName = "nota_seq", initialValue = 548, allocationSize = 1)
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    private Integer id;
    @Column(name = "numero", nullable = false, unique = true) 
    private Integer number;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone="BRT")
    @Column(name = "dataemissao", nullable = false)
    private Date issuanceDate;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy", timezone="BRT")
    @Column(name = "dataentrada", nullable = false)
    private Date dateEntry;    
    @ManyToMany
    private List<CellPhone> cellPhone;    
    @JoinColumn(name = "id_fornecedor", referencedColumnName = "id")
    @ManyToOne
    private Supplier supplier;

    /**
     *
     */
    public Invoice() {
    }  

    /**
     *
     * @return id
     */
    @XmlElement
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
     * @return
     */
    @XmlElement
    public Integer getNumber() {
        return number;
    }

    /**
     *
     * @param number
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     *
     * @return
     */
    @XmlElement
    public Date getIssuanceDate() {
        return issuanceDate;
    }

    /**
     *
     * @param issuanceDate
     */
    public void setIssuanceDate(Date issuanceDate) {
        this.issuanceDate = issuanceDate;
    }

    /**
     *
     * @return
     */
    @XmlElement
    public Date getDateEntry() {
        return dateEntry;
    }

    /**
     *
     * @param dateEntry
     */
    public void setDateEntry(Date dateEntry) {
        this.dateEntry = dateEntry;
    }

    /**
     *
     * @return
     */
    @XmlElement
    public List<CellPhone> getCellPhone() {
        return cellPhone;
    }

    /**
     *
     * @param cellPhone
     */
    public void setCellPhone(List cellPhone) {
        this.cellPhone = cellPhone;
    }

    /**
     *
     * @return
     */
    @XmlElement()
    public Supplier getCnpjFornecedor() {
        return supplier;
    }

    /**
     *
     * @param fornecedor
     */
    public void setCnpjFornecedor(Supplier fornecedor) {
        this.supplier = fornecedor;
    }

    @Override
    public String toString() {
        return "Invoice{" + "id=" + id + ", number=" + number + ", issuanceDate=" + issuanceDate + ", dateEntry=" + dateEntry + ", cellPhone=" + cellPhone + ", supplier=" + supplier + '}';
    }
}
