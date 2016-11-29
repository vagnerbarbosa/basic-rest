package vg.sales.weather.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vagner
 */
@XmlRootElement(name = "salesOrder")
public class SalesOrder {

    private Integer branchNumber; //numerofilial
    private Integer idSalesOrder; //idpedidovenda
    private String trade; //fantasia
    private String clientName; //nome
    private Integer branchNumberToInvoice; //numerofilial_fat
    private Date movingDate; //datamovimento
    private String prodDescription; //desc__prod
    private Integer amount; //quantidade
    private String itemSituation; //situacao_item
    private String deliver; //entregar
    private Integer idChargerMap; //idmapacarga
    private String situationChargerMap; //situacaomapacarga
    private Date invoicePredictionDate; //previsaofaturamento
    private String deliverSituation; //situacao__entrega
    private String montage; //montagem
    private Integer idMontageMap; //idmapamontagem
    private Date predictionMontageDate; //previsoamontagem
    private String montageSituation; //situacaomontagem
    private Integer branchReserve; //filialreserva
    private String shopRequestSituation; //situacaopedidoloja

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
    public Integer getBranchNumberToInvoice() {
        return branchNumberToInvoice;
    }

    public void setBranchNumberToInvoice(Integer branchNumberToInvoice) {
        this.branchNumberToInvoice = branchNumberToInvoice;
    }

    @XmlElement
    public Date getMovingDate() {
        return movingDate;
    }

    public void setMovingDate(Date movingDate) {
        this.movingDate = movingDate;
    }

    @XmlElement
    public String getProdDescription() {
        return prodDescription;
    }

    public void setProdDescription(String prodDescription) {
        this.prodDescription = prodDescription;
    }

    @XmlElement
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @XmlElement
    public String getItemSituation() {
        return itemSituation;
    }

    public void setItemSituation(String itemSituation) {
        this.itemSituation = itemSituation;
    }

    @XmlElement
    public String getDeliver() {
        return deliver;
    }

    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }

    @XmlElement
    public Integer getIdChargerMap() {
        return idChargerMap;
    }

    public void setIdChargerMap(Integer idChargerMap) {
        this.idChargerMap = idChargerMap;
    }

    @XmlElement
    public String getSituationChargerMap() {
        return situationChargerMap;
    }

    public void setSituationChargerMap(String situationChargerMap) {
        this.situationChargerMap = situationChargerMap;
    }

    @XmlElement
    public Date getInvoicePredictionDate() {
        return invoicePredictionDate;
    }

    public void setInvoicePredictionDate(Date invoicePredictionDate) {
        this.invoicePredictionDate = invoicePredictionDate;
    }

    @XmlElement
    public String getDeliverSituation() {
        return deliverSituation;
    }

    public void setDeliverSituation(String deliverSituation) {
        this.deliverSituation = deliverSituation;
    }

    @XmlElement
    public String getMontage() {
        return montage;
    }

    public void setMontage(String montage) {
        this.montage = montage;
    }

    @XmlElement
    public Integer getIdMontageMap() {
        return idMontageMap;
    }

    public void setIdMontageMap(Integer idMontageMap) {
        this.idMontageMap = idMontageMap;
    }

    @XmlElement
    public Date getPredictionMontageDate() {
        return predictionMontageDate;
    }

    public void setPredictionMontageDate(Date predictionMontageDate) {
        this.predictionMontageDate = predictionMontageDate;
    }

    @XmlElement
    public String getMontageSituation() {
        return montageSituation;
    }

    public void setMontageSituation(String montageSituation) {
        this.montageSituation = montageSituation;
    }

    @XmlElement
    public Integer getBranchReserve() {
        return branchReserve;
    }

    public void setBranchReserve(Integer branchReserve) {
        this.branchReserve = branchReserve;
    }

    @XmlElement
    public String getShopRequestSituation() {
        return shopRequestSituation;
    }

    public void setShopRequestSituation(String shopRequestSituation) {
        this.shopRequestSituation = shopRequestSituation;
    }

    @Override
    public String toString() {
        return "SalesOrder{" + "branchNumber=" + branchNumber + ", idSalesOrder=" + idSalesOrder + ", trade=" + trade + ", clientName=" + clientName + ", branchNumberToInvoice=" + branchNumberToInvoice + ", movingDate=" + movingDate + ", prodDescription=" + prodDescription + ", amount=" + amount + ", itemSituation=" + itemSituation + ", deliver=" + deliver + ", idChargerMap=" + idChargerMap + ", situationChargerMap=" + situationChargerMap + " invoicePredictionDate=" + invoicePredictionDate + ", deliverSituation=" + deliverSituation + ", montage=" + montage + ", idMontageMap=" + idMontageMap + ", predictionMontageDate=" + predictionMontageDate + ", montageSituation=" + montageSituation + ", branchReserve=" + branchReserve + ", shopRequestSituation=" + shopRequestSituation + '}';
    }

}
