package vg.sales.weather.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vagner
 */
@Entity
@XmlRootElement(name = "salesOrder")
public class SalesOrder implements Serializable {

    @Id
    @Column(name = "idpedidovenda")
    private Integer idSalesOrder; //idpedidovenda
    @Column(name = "numerofilial")
    private Integer branchNumber; //numerofilial
    @Column(name = "fantasia")
    private String trade; //fantasia
    @Column(name = "nome")
    private String clientName; //nome    
    @Column(name = "datamovimento")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date movingDate; //datamovimento  
    @Column(name = "situacaopedidoloja")
    private String shopRequestSituation; //situacaopedidoloja 
    @Column(name = "cidade")
    private String city;
    @Column(name = "bairro")
    private String neighborhood;
    @Column(name = "dias")
    private Integer days;

   
    public SalesOrder() {
    }

    @XmlElement
    public Integer getBranchNumber() {
        return branchNumber;
    }

    public void setBranchNumber(Integer branchNumber) {
        this.branchNumber = branchNumber;
    }

    @XmlElement
    public Integer getIdSalesOrder() {
        return idSalesOrder;
    }

    public void setIdSalesOrder(Integer idSalesOrder) {
        this.idSalesOrder = idSalesOrder;
    }

    @XmlElement
    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    @XmlElement
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @XmlElement
    public Date getMovingDate() {
        return movingDate;
    }

    public void setMovingDate(Date movingDate) {
        this.movingDate = movingDate;
    }

    @XmlElement
    public String getShopRequestSituation() {
        return shopRequestSituation;
    }

    public void setShopRequestSituation(String shopRequestSituation) {
        this.shopRequestSituation = shopRequestSituation;
    }

    @XmlElement
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlElement
    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    @XmlElement
    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
     
}