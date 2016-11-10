package vg.sales.weather.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vagner
 */
@XmlRootElement(name = "sales")
public class Sales {

    private Integer branchId; //idFilial
    private Integer branchNumber; // numeroFilial    
    private String trade; // fantasi

    //Produtos
    private Integer prodSaleItens; //prodItensVenda
    private Double prodSaleTotal; //prodTotalVenda
    private Integer prodDevolutionItens; //prodItensDevolucao
    private Double prodDevolutionTotal; //prodTotalDevolvido
    private Integer prodBalanceItens; //prodSaldoItens
    private Double prodBalanceTotal; //prodSaldoTotal
    private Double prodAverageTicket; //prodTicketMedio

    //Serviços
    private Integer servSale; //servVenda
    private Double servSaleTotal; //servTotalVenda
    private Integer servDevolution; //servDevolvido
    private Double servDevolutionTotal; //servTotalDevolvido
    private Integer servBalanceItens; //servSaldoItens
    private Double servBalanceTotal; //servSaldoTotal
    private Double servAverageTicket; //servTicketMedio   

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
    public Integer getProdSaleItens() {
        return prodSaleItens;
    }

    /**
     *
     * @param prodSaleItens
     */
    public void setProdSaleItens(Integer prodSaleItens) {
        this.prodSaleItens = prodSaleItens;
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
    public Integer getProdDevolutionItens() {
        return prodDevolutionItens;
    }

    /**
     *
     * @param prodDevolutionItens
     */
    public void setProdDevolutionItens(Integer prodDevolutionItens) {
        this.prodDevolutionItens = prodDevolutionItens;
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
    public Integer getProdBalanceItens() {
        return prodBalanceItens;
    }

    /**
     *
     * @param prodBalanceItens
     */
    public void setProdBalanceItens(Integer prodBalanceItens) {
        this.prodBalanceItens = prodBalanceItens;
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
    public Double getProdAverageTicket() {
        return prodAverageTicket;
    }

    /**
     *
     * @param prodAverageTicket
     */
    public void setProdAverageTicket(Double prodAverageTicket) {
        this.prodAverageTicket = prodAverageTicket;
    }

    /**
     *
     * @return
     */
    @XmlElement
    public Integer getServSale() {
        return servSale;
    }

    /**
     *
     * @param servSale
     */
    public void setServSale(Integer servSale) {
        this.servSale = servSale;
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
    public Integer getServDevolution() {
        return servDevolution;
    }

    /**
     *
     * @param servDevolution
     */
    public void setServDevolution(Integer servDevolution) {
        this.servDevolution = servDevolution;
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
    public Integer getServBalanceItens() {
        return servBalanceItens;
    }

    /**
     *
     * @param servBalanceItens
     */
    public void setServBalanceItens(Integer servBalanceItens) {
        this.servBalanceItens = servBalanceItens;
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
    public Double getServAverageTicket() {
        return servAverageTicket;
    }

    /**
     *
     * @param servAverageTicket
     */
    public void setServAverageTicket(Double servAverageTicket) {
        this.servAverageTicket = servAverageTicket;
    }

    @Override
    public String toString() {
        return "Sales{" + "branchId=" + branchId + ", branchNumber=" + branchNumber + ", trade=" + trade + ", prodSaleItens=" + prodSaleItens + ", prodSaleTotal=" + prodSaleTotal + ", prodDevolutionItens=" + prodDevolutionItens + ", prodDevolutionTotal=" + prodDevolutionTotal + ", prodBalanceItens=" + prodBalanceItens + ", prodBalanceTotal=" + prodBalanceTotal + ", prodAverageTicket=" + prodAverageTicket + ", servSale=" + servSale + ", servSaleTotal=" + servSaleTotal + ", servDevolution=" + servDevolution + ", servDevolutionTotal=" + servDevolutionTotal + ", servBalanceItens=" + servBalanceItens + ", servBalanceTotal=" + servBalanceTotal + ", servAverageTicket=" + servAverageTicket + '}';
    }

}
