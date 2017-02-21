package riodopeixe.rest.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vagner
 */
@Entity
@XmlRootElement(name = "toner")
@SequenceGenerator(name = "seq_gen", sequenceName = "toner_seq", initialValue = 1, allocationSize = 10)
public class Toner implements Serializable {
    
    private static final long serialVersionUID = 2009100211L;
    @Id
    @Column(name = "id_toner")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="seq_gen")
    private Integer ref;
    @Column(name="descricao")
    private String descricao;
    @Column(name="filial")
    private String filial;
    @Column(name="modelo")
    private String modelo;
    @Column(name="marca")
    private String marca;
    @Column(name="data_inclusao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataInclusao;
    @Column(name="situacao")
    private String situacao;
    @OneToMany(mappedBy = "toner", targetEntity = Servico.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Servico> servicos;

    public Toner() {
    }
    
    @XmlElement
    public Integer getRef() {
        return ref;
    }

    public void setRef(Integer ref) {
        this.ref = ref;
    }

    @XmlElement
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlElement
    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    @XmlElement
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @XmlElement
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @XmlElement
    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    @XmlElement
    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @XmlElement
    public List<Servico> getServicos() {
        return servicos;
    }

    public void setServicos(List<Servico> servicos) {
        this.servicos = servicos;
    }
    
    private String getDateTime() {
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Date date = new Date();
	return dateFormat.format(date);
    }    

    @Override
    public String toString() {
        return "Toner{" + "ref=" + ref + ", descricao=" + descricao + ", filial=" + filial + ", modelo=" + modelo + ", marca=" + marca + ", dataInclusao=" + dataInclusao + ", situacao=" + situacao + ", servicos=" + servicos + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.ref);
        hash = 79 * hash + Objects.hashCode(this.descricao);
        hash = 79 * hash + Objects.hashCode(this.filial);
        hash = 79 * hash + Objects.hashCode(this.modelo);
        hash = 79 * hash + Objects.hashCode(this.marca);
        hash = 79 * hash + Objects.hashCode(this.dataInclusao);
        hash = 79 * hash + Objects.hashCode(this.situacao);
        hash = 79 * hash + Objects.hashCode(this.servicos);
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
        final Toner other = (Toner) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.filial, other.filial)) {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        if (!Objects.equals(this.situacao, other.situacao)) {
            return false;
        }
        if (!Objects.equals(this.ref, other.ref)) {
            return false;
        }
        if (!Objects.equals(this.dataInclusao, other.dataInclusao)) {
            return false;
        }
        return Objects.equals(this.servicos, other.servicos);
    }    
   
}
