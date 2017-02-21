package riodopeixe.rest.dataset;

import riodopeixe.rest.model.ImeiSale;

/**
 * Interface ImeiSaleDataSet.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 21/02/2017
 *
 * @version 1.0
 */
public interface ImeiSaleDataSet {
    
    /**
     *
     * @param imei
     * @return
     */
    public ImeiSale imeiSaleCheck(String imei);    
    
}
