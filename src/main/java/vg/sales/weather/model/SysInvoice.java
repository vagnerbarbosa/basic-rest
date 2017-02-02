package vg.sales.weather.model;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vagner
 */
@XmlRootElement(name = "nota")
public class SysInvoice implements Serializable {
    
    private Integer productCod;
    private String productDesc;
    private Integer buyerCod;
    private Integer NfNumber;
    private String NfKey;
    private Date dateEntry;
    private String supplierName;

    public SysInvoice() {
    }

    @XmlElement(name = "codigoProduto")
    public Integer getProductCod() {
        return productCod;
    }

    public void setProductCod(Integer productCod) {
        this.productCod = productCod;
    }

    @XmlElement(name = "descricaoProduto")
    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    @XmlElement(name = "codigoCompra")
    public Integer getBuyerCod() {
        return buyerCod;
    }

    public void setBuyerCod(Integer buyerCod) {
        this.buyerCod = buyerCod;
    }

    @XmlElement(name = "numeroNotaFiscal")
    public Integer getNfNumber() {
        return NfNumber;
    }

    public void setNfNumber(Integer NfNumber) {
        this.NfNumber = NfNumber;
    }    

    @XmlElement(name = "chaveNotaFiscal")
    public String getNfKey() {
        return NfKey;
    }

    public void setNfKey(String NfKey) {
        this.NfKey = NfKey;
    }

    @XmlElement(name = "dataEntrada")
    public Date getDateEntry() {
        return dateEntry;
    }

    public void setDateEntry(Date dateEntry) {
        this.dateEntry = dateEntry;
    }

    @XmlElement(name = "nomeFornecedor")
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "SysInvoice{" + "productCod=" + productCod + ", productDesc=" + productDesc + ", buyerCod=" + buyerCod + ", NfNumber=" + NfNumber + ", NfKey=" + NfKey + ", dateEntry=" + dateEntry + ", supplierName=" + supplierName + '}';
    }
    
    
    
}
