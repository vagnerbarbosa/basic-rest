package riodopeixe.rest.model;

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
@XmlRootElement(name = "sales")
public class Sales implements Serializable {
    
    @Id
    @Column(name = "idfilial")
    private Integer branchId; //idFilial
    @Column(name = "numerofilial")
    private Integer branchNumber; // numeroFilial
    @Column(name = "fantasia")    
    private String trade; // fantasi

    //Produtos 
    @Column(name = "pro_totalitem")
    private Double prodSaleTotal; //PRODUT BRUTO
    @Column(name = "pro_devolucao")
    private Double prodDevolutionTotal; ////DEV PRODU
    @Column(name = "p_liquido")
    private Double prodBalanceTotal; //PROD LIQU    
 

    //Serviços 
    @Column(name = "sg_totalitem")
    private Double servSaleTotal; //sg_totalitem 
    @Column(name = "sg_devolucao")
    private Double servDevolutionTotal; //sg_devolucao
    @Column(name = "sg_liquido")
    private Double servBalanceTotal; ////SERVIÇO LIQ
    @Column(name = "rc_liquido")
    private Double rechargeTotal; //REG
    
    //Total Líquido
    @Column(name = "total_liquido")
    private Double balanceTotal; //LIQUI_TOTAL      

    /**
     *
     */
    public Sales() {
    }

    /**
     *
     * @return
     */
    @XmlElement
    public Integer getBranchId() {
        return branchId;
    }

    /**
     *
     * @param branchId
     */
    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    /**
     *
     * @return
     */
    @XmlElement
    public Integer getBranchNumber() {
        return branchNumber;
    }

    /**
     *
     * @param branchNumber
     */
    public void setBranchNumber(Integer branchNumber) {
        this.branchNumber = branchNumber;
    }

    /**
     *
     * @return
     */
    @XmlElement
    public String getTrade() {
        return trade;
    }

    /**
     *
     * @param trade
     */
    public void setTrade(String trade) {
        this.trade = trade;
    }

    /**
     *
     * @return
     */
    @XmlElement
    public Double getProdSaleTotal() {
        return prodSaleTotal;
    }

    /**
     *
     * @param prodSaleTotal
     */
    public void setProdSaleTotal(Double prodSaleTotal) {
        this.prodSaleTotal = prodSaleTotal;
    }

    /**
     *
     * @return
     */
    @XmlElement
    public Double getProdDevolutionTotal() {
        return prodDevolutionTotal;
    }

    /**
     *
     * @param prodDevolutionTotal
     */
    public void setProdDevolutionTotal(Double prodDevolutionTotal) {
        this.prodDevolutionTotal = prodDevolutionTotal;
    }

    /**
     *
     * @return
     */
    @XmlElement
    public Double getProdBalanceTotal() {
        return prodBalanceTotal;
    }

    /**
     *
     * @param prodBalanceTotal
     */
    public void setProdBalanceTotal(Double prodBalanceTotal) {
        this.prodBalanceTotal = prodBalanceTotal;
    }


    /**
     *
     * @return
     */
    @XmlElement
    public Double getServSaleTotal() {
        return servSaleTotal;
    }

    /**
     *
     * @param servSaleTotal
     */
    public void setServSaleTotal(Double servSaleTotal) {
        this.servSaleTotal = servSaleTotal;
    }

    /**
     *
     * @return
     */
    @XmlElement
    public Double getServDevolutionTotal() {
        return servDevolutionTotal;
    }

    /**
     *
     * @param servDevolutionTotal
     */
    public void setServDevolutionTotal(Double servDevolutionTotal) {
        this.servDevolutionTotal = servDevolutionTotal;
    }



    /**
     *
     * @return
     */
    @XmlElement
    public Double getServBalanceTotal() {
        return servBalanceTotal;
    }

    /**
     *
     * @param servBalanceTotal
     */
    public void setServBalanceTotal(Double servBalanceTotal) {
        this.servBalanceTotal = servBalanceTotal;
    }

    /**
     *
     * @return
     */
    @XmlElement
    public Double getRechargeTotal() {
        return rechargeTotal;
    }

    /**
     *
     * @param rechargeTotal
     */
    public void setRechargeTotal(Double rechargeTotal) {
        this.rechargeTotal = rechargeTotal;
    }

    @XmlElement
    public Double getBalanceTotal() {
        return balanceTotal;
    }

    public void setBalanceTotal(Double balanceTotal) {
        this.balanceTotal = balanceTotal;
    }

    @Override
    public String toString() {
        return "Sales{" + "branchId=" + branchId + ", branchNumber=" + branchNumber + ", trade=" + trade + ", prodSaleTotal=" + prodSaleTotal + ", prodDevolutionTotal=" + prodDevolutionTotal + ", prodBalanceTotal=" + prodBalanceTotal + ", balanceTotal=" + balanceTotal + ", servSaleTotal=" + servSaleTotal + ", servDevolutionTotal=" + servDevolutionTotal + ", servBalanceTotal=" + servBalanceTotal + ", servAverageTicket=" + rechargeTotal + '}';
    }
    
  
 

}
