
package vg.sales.weather.datasource;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import vg.integrator.component.model.Pessoa;

public class PessoaDataSetImpl implements PessoaDataSet {

    private Connection connection;

    public PessoaDataSetImpl() throws ConnectionException, SQLException {
        this.connection = ConnectionFactory.getIntance().getConnection();
    }

    @Override
    public Pessoa getPessoa(String cnpj__cpf) throws SQLException {
        PreparedStatement pessoaStatament = connection.prepareStatement("SELECT pessoa.idcnpj_cpf, pessoa.cnpj_cpf, pessoa.idtiposexo, pessoa.nome, pessoa.nomefantasia FROM glb.pessoa pessoa WHERE pessoa.cnpj_cpf = ?");
        pessoaStatament.setString(1, cnpj__cpf);
        Pessoa pessoa = new Pessoa();
        if (pessoaStatament.execute()) {
            while(pessoaStatament.getResultSet().next()){
                pessoa.setIdcnpj__cpf(BigInteger.valueOf(pessoaStatament.getResultSet().getLong("idcnpj_cpf")));
                pessoa.setCnpj__cpf(pessoaStatament.getResultSet().getString("cnpj_cpf"));
                pessoa.setIdtiposexo(pessoaStatament.getResultSet().getInt("idtiposexo"));
                pessoa.setNome(pessoaStatament.getResultSet().getString("nome"));
                pessoa.setNomefantasia(pessoaStatament.getResultSet().getString("nomefantasia"));
            }
             return pessoa;
        } else {
            throw new SQLException("Não houve resultado válido!");
        }       
    }

    @Override
    public List<Pessoa> listarPessoas() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
