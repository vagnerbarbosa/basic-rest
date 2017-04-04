package riodopeixe.rest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe para os objetos do tipo ImeiSale, onde serão contidos os métodos
 * para o mesmo.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 21/02/2017
 *
 * @version 1.0
 */
@Entity(name = "imei_por_venda")
@XmlRootElement(name = "imeiPorVenda")
public class ImeiSale implements Serializable {
    
    @Column(name = "numeroFilial")
    private Integer numberBranch;
    @Id
    @Column(name = "idPedidoVenda")
    private Integer salesId;
    @Column(name = "dataBaixa")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date emissionDate;
    @Column(name = "cliente")
    private String client;
    @Column(name = "imei")
    private String imei;

    public ImeiSale() {
    }

    @XmlElement(name = "numeroFilial") 
    public Integer getNumberBranch() {
        return numberBranch;
    }

    public void setNumberBranch(Integer numberBranch) {
        this.numberBranch = numberBranch;
    }

    @XmlElement(name = "idPedidoVenda")
    public Integer getSalesId() {
        return salesId;
    }

    public void setSalesId(Integer salesId) {
        this.salesId = salesId;
    }

    @XmlElement(name = "dataBaixa")
    public Date getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(Date emissionDate) {
        this.emissionDate = emissionDate;
    }

    @XmlElement(name = "cliente")
    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    @XmlElement(name = "imei")
    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.numberBranch);
        hash = 97 * hash + Objects.hashCode(this.salesId);
        hash = 97 * hash + Objects.hashCode(this.emissionDate);
        hash = 97 * hash + Objects.hashCode(this.client);
        hash = 97 * hash + Objects.hashCode(this.imei);
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
        final ImeiSale other = (ImeiSale) obj;
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.imei, other.imei)) {
            return false;
        }
        if (!Objects.equals(this.numberBranch, other.numberBranch)) {
            return false;
        }
        if (!Objects.equals(this.salesId, other.salesId)) {
            return false;
        }
        return Objects.equals(this.emissionDate, other.emissionDate);
    }

    @Override
    public String toString() {
        return "ImeiSale{" + "numberBranch=" + numberBranch + ", salesId=" + salesId + ", dateEmission=" + emissionDate + ", client=" + client + ", imei=" + imei + '}';
    }   
}
