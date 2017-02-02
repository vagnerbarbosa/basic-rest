package vg.sales.weather.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe para os objetos do tipo Supplier, onde serão contidos os métodos
 * para o mesmo.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 03/06/2016
 *
 * @version 1.0
 */
@Entity
@Table(name="fornecedor")
@XmlRootElement(name = "fornecedor")
@SequenceGenerator(name = "seq_gen2", sequenceName = "fornecedor_seq", initialValue = 1, allocationSize = 20)
public class Supplier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen2")
    private Long id;
    @Column(name = "cnpj", nullable = false)
    private String cnpj;
    @Column(name = "razao_social", nullable = false)
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
    private Integer number;
    @Column(name = "ie", nullable = false)
    private Long IE;

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
    public Supplier(String cnpj, String companyName, String FU, String city, String neighborhood, String address, Integer number, Long IE) {
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
    public Long getIE() {
        return IE;
    }

    /**
     *
     * @param IE
     */
    public void setIE(Long IE) {
        this.IE = IE;
    }

    @Override
    public String toString() {
        return "Supplier{" + "id=" + id + ", cnpj=" + cnpj + ", companyName=" + companyName + ", FU=" + FU + ", city=" + city + ", neighborhood=" + neighborhood + ", address=" + address + ", number=" + number + ", IE=" + IE + '}';
    }
}
