package riodopeixe.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
@SequenceGenerator(name = "seq_gen", sequenceName = "nota_seq", initialValue = 548, allocationSize = 1)
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    private Integer id;
    @Column(name = "numero", nullable = false, unique = true) 
    private Integer number;
    @Temporal(TemporalType.DATE)
    @Column(name = "dataemissao", nullable = false)
    private Date issuanceDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "dataentrada", nullable = false)
    private Date dateEntry; 
    @XmlElement(name = "celular")
    @ElementCollection 
    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY, targetEntity = CellPhone.class)
    @JoinTable(name="notafiscal_celular", joinColumns={@JoinColumn(name="invoices_id", referencedColumnName="id")}, inverseJoinColumns={@JoinColumn(name="cellphone_id", referencedColumnName="idcelular")})
    public List<CellPhone> cellPhones;    
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
    @XmlElement(name = "idNota")
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
    @XmlElement(name = "numero")
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
    @XmlElement(name = "dataEmissao")
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
    @XmlElement(name = "dataEntrada")
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

//    /**
//     *
//     * @return
//     */
//    @XmlElement(name = "celular")
//    public List<CellPhone> getCellPhones() {
//        return cellPhones;
//    }
//
//    /**
//     *
//     * @param cellPhones
//     */
//    public void setCellPhones(List cellPhones) {
//        this.cellPhones = cellPhones;
//    }

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
        return "Invoice{" + "id=" + id + ", number=" + number + ", issuanceDate=" + issuanceDate + ", dateEntry=" + dateEntry + ", cellPhones=" + cellPhones + ", supplier=" + supplier + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.number);
        hash = 73 * hash + Objects.hashCode(this.issuanceDate);
        hash = 73 * hash + Objects.hashCode(this.dateEntry);
        hash = 73 * hash + Objects.hashCode(this.cellPhones);
        hash = 73 * hash + Objects.hashCode(this.supplier);
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
        final Invoice other = (Invoice) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        if (!Objects.equals(this.issuanceDate, other.issuanceDate)) {
            return false;
        }
        if (!Objects.equals(this.dateEntry, other.dateEntry)) {
            return false;
        }
        if (!Objects.equals(this.cellPhones, other.cellPhones)) {
            return false;
        }
        if (!Objects.equals(this.supplier, other.supplier)) {
            return false;
        }
        return true;
    }    
}
