
package vg.sales.weather.datasource;

import java.sql.SQLException;
import java.util.List;
import vg.integrator.component.model.Pessoa;

public interface PessoaDataSet {

    public Pessoa getPessoa(String cnpj__cpf) throws SQLException;

    public List<Pessoa> listarPessoas() throws SQLException;
}
