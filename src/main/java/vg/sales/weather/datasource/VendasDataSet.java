package vg.sales.weather.datasource;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import vg.integrator.component.model.Vendas;

/**
 *
 * @author vagner
 */
public interface VendasDataSet {
    
    public List<Vendas> listarVendas(Date datainicial, Date datafinal) throws SQLException;
    
}
