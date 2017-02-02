package vg.sisnota.datasource.jdbc;

import java.sql.SQLException;
import java.util.List;
import vg.sales.weather.model.SysInvoice;

/**
 * Interface SysInvoiceDataSet.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 10/01/2017
 *
 * @version 1.0
 */
public interface SysInvoiceDataSet {
    
    public List<SysInvoice> getSysInvoices(String code) throws SQLException;
    
}
