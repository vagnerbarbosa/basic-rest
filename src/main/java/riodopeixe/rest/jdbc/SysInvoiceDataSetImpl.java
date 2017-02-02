package riodopeixe.rest.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import riodopeixe.rest.model.SysInvoice;

/**
 * Classe de pesistência para objetos do tipo SysInvoice.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 011/01/2017
 *
 * @version 1.0
 */
public class SysInvoiceDataSetImpl implements SysInvoiceDataSet {

    private final Connection connection;
    private SysInvoice sysInvoice;
    private List<SysInvoice> liats;

    public SysInvoiceDataSetImpl() throws ConnectionException {
        this.connection = ConnectionFactory.getIntance().getConnection();
    }

    @Override
    public List<SysInvoice> getSysInvoices(String code) throws SQLException {

        Statement statement = connection.createStatement();
        try (ResultSet rs = statement.executeQuery("SELECT Produtos.[Cód prod] AS codigo_produto, Produtos.[Nome produto] AS nome_produto, Compra_itens.Compra AS codigo_compra, Compras.[Num nota] AS numero_nota, Compras.Nfe_chave AS chave_nota, Compras.[Data compra] AS data_compra, Compras.Nome_fornecedor AS nome_fornecedor FROM Compras INNER JOIN (Produtos INNER JOIN Compra_itens ON Produtos.[Cód prod] = Compra_itens.[Cód prod]) ON Compras.Compra = Compra_itens.Compra WHERE (((Produtos.[Cód prod]) LIKE '" + code + "') AND ((Compras.Cod_fornecedor) NOT LIKE '0442')) ORDER BY Compras.[Data compra] DESC;")) {
            this.liats = new ArrayList();
            while (rs.next()) {
                this.sysInvoice = new SysInvoice();
                sysInvoice.setProductCod(Integer.valueOf(rs.getString(1)));
                sysInvoice.setProductDesc(rs.getString(2));
                sysInvoice.setBuyerCod(Integer.valueOf(rs.getString(3)));
                sysInvoice.setNfNumber(Integer.valueOf(rs.getString(4)));
                sysInvoice.setNfKey(rs.getString(5));
                sysInvoice.setDateEntry(rs.getDate(6));
                sysInvoice.setSupplierName(rs.getString(7));
                liats.add(sysInvoice);
            }
        }
        return liats;

    }

}
