package riodopeixe.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Entity
@XmlRootElement(name = "fornecedor")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Supplier implements Serializable {
    @Id
    private Long id;
    @Column(name = "cnpj", nullable = false)
    private String cnpj;
    @Column(name = "companyName", nullable = false)
    private String companyName;
    @Column(name = "fu", nullable = false)
    private String FU;
    @Column(name = "city", nullable = false)    
    private String city;
    @Column(name = "neighborhood", nullable = false)
    private String neighborhood;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "number", nullable = false)
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

    @XmlElement
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
    @XmlElement
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
    @XmlElement
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
    @XmlElement
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
    @XmlElement
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
    @XmlElement
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
    @XmlElement
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
    @XmlElement
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
    @XmlElement
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
    public String toString() {
        return "Supplier{" + "id=" + id + ", cnpj=" + cnpj + ", companyName=" + companyName + ", FU=" + FU + ", city=" + city + ", neighborhood=" + neighborhood + ", address=" + address + ", number=" + number + ", IE=" + IE + '}';
    }
}
