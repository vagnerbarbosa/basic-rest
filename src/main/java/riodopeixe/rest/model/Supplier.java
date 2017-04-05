package riodopeixe.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe para os objetos do tipo Supplier, onde serão contidos os métodos
 * para o mesmo.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 25/02/2017
 *
 * @version 1.2
 */
@Entity(name = "fornecedor")
@Table(name="fornecedor")
@XmlRootElement(name = "fornecedor")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer", "handler"})
public class Supplier implements Serializable {
    @Id
    private Long id;
    @Column(name = "cnpj", nullable = false)
    private String cnpj;
    @Column(name = "nomeFornecedor", nullable = false)
    private String companyName;
    @Column(name = "uf", nullable = false)
    private String FU;
    @Column(name = "cidade", nullable = false)    
    private String city;
    @Column(name = "bairro", nullable = false)
    private String neighborhood;
    @Column(name = "endereco", nullable = false)
    private String address;
    @Column(name = "numero", nullable = false)
    private String number;
    @Column(name = "IE", nullable = false)
    private String IE;

    /**
     *
     */
    public Supplier() {
    }

    /**
     *
     * @param cnpj
     * @param companyName
     * @param FU
     * @param city
     * @param neighborhood
     * @param address
     * @param number
     * @param IE
     */
    public Supplier(String cnpj, String companyName, String FU, String city, String neighborhood, String address, String number, String IE) {
        this.cnpj = cnpj;
        this.companyName = companyName;
        this.FU = FU;
        this.city = city;
        this.neighborhood = neighborhood;
        this.address = address;
        this.number = number;
        this.IE = IE;
    }

    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
           
    /**
     *
     * @return
     */
    @XmlElement(name = "cnpj")
    public String getCnpj() {        
        return this.cnpj;
                
    }

    /**
     *
     * @param cnpj
     */
    public void setCnpj(String cnpj) {        
        this.cnpj = cnpj;
    }
    
    /**
     *
     * @return
     */
    @XmlElement(name = "nomeFornecedor")
    public String getCompanyName() {
        return companyName;
    }

    /**
     *
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    /**
     *
     * @return
     */
    @XmlElement(name = "UF")
    public String getFU() {
        return FU;
    }

    /**
     *
     * @param FU
     */
    public void setFU(String FU) {
        this.FU = FU;
    }
    
    /**
     *
     * @return
     */
    @XmlElement(name = "cidade")
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "bairro")
    public String getNeighborhood() {
        return neighborhood;
    }

    /**
     *
     * @param neighborhood
     */
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "endereco")
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "numero")
    public String getNumber() {
        return number;
    }

    /**
     *
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     *
     * @return
     */
    @XmlElement(name = "IE")
    public String getIE() {
        return IE;
    }

    /**
     *
     * @param IE
     */
    public void setIE(String IE) {
        this.IE = IE;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.cnpj);
        hash = 37 * hash + Objects.hashCode(this.companyName);
        hash = 37 * hash + Objects.hashCode(this.FU);
        hash = 37 * hash + Objects.hashCode(this.city);
        hash = 37 * hash + Objects.hashCode(this.neighborhood);
        hash = 37 * hash + Objects.hashCode(this.address);
        hash = 37 * hash + Objects.hashCode(this.number);
        hash = 37 * hash + Objects.hashCode(this.IE);
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
        final Supplier other = (Supplier) obj;
        if (!Objects.equals(this.cnpj, other.cnpj)) {
            return false;
        }
        if (!Objects.equals(this.companyName, other.companyName)) {
            return false;
        }
        if (!Objects.equals(this.FU, other.FU)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.neighborhood, other.neighborhood)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        if (!Objects.equals(this.IE, other.IE)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Supplier{" + "id=" + id + ", cnpj=" + cnpj + ", companyName=" + companyName + ", FU=" + FU + ", city=" + city + ", neighborhood=" + neighborhood + ", address=" + address + ", number=" + number + ", IE=" + IE + '}';
    }
    

 
}
