package riodopeixe.rest.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;



/**
 *
 * @author vagner
 */
@Entity
@XmlRootElement(name = "tipoServico")
@SequenceGenerator(name = "seq_gen3", sequenceName = "tipo_servico_seq", initialValue = 1, allocationSize = 10)
public class TipoServico implements Serializable {
    
    @Id
    @Column(name = "id_tipo_servico")
    private Integer idTipoServico;
    @Column(name="tipo_servico")
    private String tipoServico;
    @Column(name="realizado")
    private String realizado;
    @ManyToOne
    @JoinColumn(name="servico_id")
    private Servico servico;

    public TipoServico() {
    }
    
    @XmlElement
    public Integer getIdTipoServico() {
        return idTipoServico;
    }

    public void setIdTipoServico(Integer idTipoServico) {
        this.idTipoServico = idTipoServico;
    }

    @XmlElement
    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    @XmlElement
    public String getRealizado() {
        return realizado;
    }

    public void setRealizado(String realizado) {
        this.realizado = realizado;
    }

    @XmlTransient
    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    @Override
    public String toString() {
        return "TipoServico{" + "idTipoServico=" + idTipoServico + ", tipoServico=" + tipoServico + ", realizado=" + realizado + ", servico=" + servico + '}';
    }
}
