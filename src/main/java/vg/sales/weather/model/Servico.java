package vg.sales.weather.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vagner
 */
@Entity
@XmlRootElement(name = "servico")
@SequenceGenerator(name = "seq_gen2", sequenceName = "servico_seq", initialValue = 1, allocationSize = 10)
public class Servico implements Serializable {
    
    @Id
    @Column(name = "id_servico")
    private Integer idServico;
    @Column(name="data_entrada")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEntrada;
    @Column(name="data_saida")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataSaida;
    @OneToMany(mappedBy = "servico", targetEntity = TipoServico.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TipoServico> tiposServico;
    @ManyToOne
    @JoinColumn(name="toner_id")  
    private Toner toner;

    public Servico() {
    }
    
    @XmlElement
    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }
    
    @XmlElement
    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
    
    @XmlElement
    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    @XmlElement
    public List<TipoServico> getTiposServico() {
        return tiposServico;
    }

    public void setTiposServico(List<TipoServico> tiposServico) {
        this.tiposServico = tiposServico;
    }    

    @XmlTransient
    public Toner getToner() {
        return toner;
    }

    public void setToner(Toner toner) {
        this.toner = toner;
    }

    @Override
    public String toString() {
        return "Servico{" + "idServico=" + idServico + ", dataEntrada=" + dataEntrada + ", dataSaida=" + dataSaida + ", tiposServico=" + tiposServico + ", toner=" + toner + '}';
    }
     
}
