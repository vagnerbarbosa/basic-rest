package riodopeixe.rest.dataset;

import java.sql.Date;
import java.util.List;
import riodopeixe.rest.model.Sales;

/**
 *
 * @author vagner
 */
public interface SalesDataSet {
    
    public List<Sales> listSales(Date initialDate, Date finalDate) throws Exception;
    
}
