package vg.sales.weather.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vagner
 */
@XmlRootElement(name = "vendas")
public class Vendas {
    
    private Integer idfilial;
    private Integer numerofilial;    
    private String fantasia;
    
    //Produtos
    
    private Integer prodItensVenda;
    private Double prodTotalVenda;
    private Integer prodItensDevolucao;
    private Double prodTotalDevolvido;
    private Integer prodSaldoItens;
    private Double prodSaldoTotal;
    private Double prodTicketMedio;
    
    //Serviços

    private Integer servVenda;
    private Double servTotalVenda;
    private Integer servDevolvido;
    private Double servTotalDevolvido;
    private Integer servSaldoItens;
    private Double servSaldoTotal;
    private Double servTicketMedio;    

    public Vendas() {
    }
    
    @XmlElement
    public Integer getIdfilial() {
        return idfilial;
    }

    public void setIdfilial(Integer idfilial) {
        this.idfilial = idfilial;
    }
    
    @XmlElement
    public Integer getNumerofilial() {
        return numerofilial;
    }

    public void setNumerofilial(Integer numerofilial) {
        this.numerofilial = numerofilial;
    }
    
    @XmlElement
    public String getFantasia() {
        return fantasia;
    }
    
    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }
    
    @XmlElement
    public Integer getProdItensVenda() {
        return prodItensVenda;
    }

    public void setProdItensVenda(Integer prodItensVenda) {
        this.prodItensVenda = prodItensVenda;
    }

    @XmlElement
    public Double getProdTotalVenda() {
        return prodTotalVenda;
    }

    public void setProdTotalVenda(Double prodTotalVenda) {
        this.prodTotalVenda = prodTotalVenda;
    }

    @XmlElement
    public Integer getProdItensDevolucao() {
        return prodItensDevolucao;
    }

    public void setProdItensDevolucao(Integer prodItensDevolucao) {
        this.prodItensDevolucao = prodItensDevolucao;
    }

    @XmlElement
    public Double getProdTotalDevolvido() {
        return prodTotalDevolvido;
    }

    public void setProdTotalDevolvido(Double prodTotalDevolvido) {
        this.prodTotalDevolvido = prodTotalDevolvido;
    }

    @XmlElement
    public Integer getProdSaldoItens() {
        return prodSaldoItens;
    }

    public void setProdSaldoItens(Integer prodSaldoItens) {
        this.prodSaldoItens = prodSaldoItens;
    }

    @XmlElement
    public Double getProdSaldoTotal() {
        return prodSaldoTotal;
    }

    public void setProdSaldoTotal(Double prodSaldoTotal) {
        this.prodSaldoTotal = prodSaldoTotal;
    }

    @XmlElement
    public Double getProdTicketMedio() {
        return prodTicketMedio;
    }

    public void setProdTicketMedio(Double prodTicketMedio) {
        this.prodTicketMedio = prodTicketMedio;
    }

    @XmlElement
    public Integer getServVenda() {
        return servVenda;
    }

    public void setServVenda(Integer servVenda) {
        this.servVenda = servVenda;
    }

    @XmlElement
    public Double getServTotalVenda() {
        return servTotalVenda;
    }

    public void setServTotalVenda(Double servTotalVenda) {
        this.servTotalVenda = servTotalVenda;
    }

    @XmlElement
    public Integer getServDevolvido() {
        return servDevolvido;
    }

    public void setServDevolvido(Integer servDevolvido) {
        this.servDevolvido = servDevolvido;
    }

    @XmlElement
    public Double getServTotalDevolvido() {
        return servTotalDevolvido;
    }

    public void setServTotalDevolvido(Double servTotalDevolvido) {
        this.servTotalDevolvido = servTotalDevolvido;
    }

    @XmlElement
    public Integer getServSaldoItens() {
        return servSaldoItens;
    }

    public void setServSaldoItens(Integer servSaldoItens) {
        this.servSaldoItens = servSaldoItens;
    }

    @XmlElement
    public Double getServSaldoTotal() {
        return servSaldoTotal;
    }

    public void setServSaldoTotal(Double servSaldoTotal) {
        this.servSaldoTotal = servSaldoTotal;
    }
    
    @XmlElement
    public Double getServTicketMedio() {
        return servTicketMedio;
    }

    public void setServTicketMedio(Double servTicketMedio) {
        this.servTicketMedio = servTicketMedio;
    }

    @Override
    public String toString() {
        return "Vendas{" + "idfilial=" + idfilial + ", numerofilial=" + numerofilial + ", fantasia=" + fantasia + ", prodItensVenda=" + prodItensVenda + ", prodTotalVenda=" + prodTotalVenda + ", prodItensDevolucao=" + prodItensDevolucao + ", prodTotalDevolvido=" + prodTotalDevolvido + ", prodSaldoItens=" + prodSaldoItens + ", prodSaldoTotal=" + prodSaldoTotal + ", prodTicketMedio=" + prodTicketMedio + ", servVenda=" + servVenda + ", servTotalVenda=" + servTotalVenda + ", servDevolvido=" + servDevolvido + ", servTotalDevolvido=" + servTotalDevolvido + ", servSaldoItens=" + servSaldoItens + ", servSaldoTotal=" + servSaldoTotal + ", servTicketMedio=" + servTicketMedio + '}' + "\n";
    }
  
}
