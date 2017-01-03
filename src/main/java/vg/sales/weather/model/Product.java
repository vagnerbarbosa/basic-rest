/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@XmlRootElement(name = "product")
public class Product implements Serializable {
       
    @Id
    @Column(name = "idproduto")
    private Integer prodId; //idproduto
    @Column(name="filialreserva")
    private Integer branchNumberToInvoice; //filialreserva
    @Column(name="desc_prod")
    private String prodDescription; //desc__prod
    @Column(name="quantidade")
    private Integer amount; //quantidade
    @Column(name="situacao_item")
    private String itemSituation; //situacao_item
    @Column(name="entregar")
    private String deliver; //entregar
    @Column(name="idmapacarga")
    private Integer idChargerMap; //idmapacarga
    @Column(name="situacaomapacarga")
    private String situationChargerMap; //situacaomapacarga
    @Column(name="previsaofaturamento")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date invoicePredictionDate; //previsaofaturamento
    @Column(name="situacao_entrega")
    private String deliverSituation; //situacao__entrega
    @Column(name="montagem")
    private String montage; //montagem
    @Column(name="idmapamontagem")
    private Integer idMontageMap; //idmapamontagem
    @Column(name="previsaomontagem")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date predictionMontageDate; //previsoamontagem
    @Column(name="situacaomontagem")
    private String montageSituation; //situacaomontagem
    @Column(name="idsituacaoentrega")
    private String idDeliverySituation; //idsituacaoentrega
    @Column(name="idsituacaomontagem")
    private String idMontageSituation; //idsituacaomontagem 
    @Column(name="idpedidovenda")
    private Integer idSaleOrder;

    @XmlElement
    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    @XmlElement
    public Integer getBranchNumberToInvoice() {
        return branchNumberToInvoice;
    }

    public void setBranchNumberToInvoice(Integer branchNumberToInvoice) {
        this.branchNumberToInvoice = branchNumberToInvoice;
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
    public String getIdDeliverySituation() {
        return idDeliverySituation;
    }

    public void setIdDeliverySituation(String idDeliverySituation) {
        this.idDeliverySituation = idDeliverySituation;
    }

    @XmlElement
    public String getIdMontageSituation() {
        return idMontageSituation;
    }

    public void setIdMontageSituation(String idMontageSituation) {
        this.idMontageSituation = idMontageSituation;
    }

    @XmlElement
    public Integer getIdSaleOrder() {
        return idSaleOrder;
    }

    public void setIdSaleOrder(Integer idSaleOrder) {
        this.idSaleOrder = idSaleOrder;
    }
     
}
