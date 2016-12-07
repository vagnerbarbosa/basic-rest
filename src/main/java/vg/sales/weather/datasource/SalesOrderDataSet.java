package vg.sales.weather.datasource;

import java.util.List;
import vg.sales.weather.model.SalesOrder;

/**
 *
 * @author vagner
 */
public interface SalesOrderDataSet {
    
    public List<SalesOrder> listSalesOrder(Integer branchNumber);
    
}
