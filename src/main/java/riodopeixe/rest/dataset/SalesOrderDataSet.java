package riodopeixe.rest.dataset;

import java.util.List;
import riodopeixe.rest.model.Product;
import riodopeixe.rest.model.SalesOrder;

/**
 *
 * @author vagner
 */
public interface SalesOrderDataSet {
    
    public List<SalesOrder> listSalesOrder(Integer branchNumber);
    public List<Product> listSalesProducts(Integer branchNumber);
    
}
