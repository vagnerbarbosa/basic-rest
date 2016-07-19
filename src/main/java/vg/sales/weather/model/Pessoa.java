package vg.sales.weather.model;

import java.math.BigInteger;

/**
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 */
public class Pessoa {
    
    private BigInteger idcnpj__cpf;
    private String cnpj__cpf;
    private Integer idtiposexo;
    private String nome;
    private String nomefantasia;

    public Pessoa() {
    }

    public BigInteger getIdcnpj__cpf() {
        return idcnpj__cpf;
    }

    public void setIdcnpj__cpf(BigInteger idcnpj__cpf) {
        this.idcnpj__cpf = idcnpj__cpf;
    }

    public String getCnpj__cpf() {
        return cnpj__cpf;
    }

    public void setCnpj__cpf(String cnpj__cpf) {
        this.cnpj__cpf = cnpj__cpf;
    }

    public Integer getIdtiposexo() {
        return idtiposexo;
    }

    public void setIdtiposexo(Integer idtiposexo) {
        this.idtiposexo = idtiposexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomefantasia() {
        return nomefantasia;
    }

    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "idcnpj__cpf=" + idcnpj__cpf + ", cnpj__cpf=" + cnpj__cpf + ", idtiposexo=" + idtiposexo + ", nome=" + nome + ", nomefantasia=" + nomefantasia + '}';
    }
     
}
