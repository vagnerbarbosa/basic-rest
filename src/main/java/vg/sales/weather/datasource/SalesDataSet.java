package vg.sales.weather.datasource;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import vg.sales.weather.model.Sales;

/**
 *
 * @author vagner
 */
public interface SalesDataSet {
    
    public List<Sales> listSales(Date initialDate, Date finalDate) throws ConnectionException, SQLException;
    
}
