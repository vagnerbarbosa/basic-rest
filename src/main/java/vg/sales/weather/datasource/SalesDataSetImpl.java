package vg.sales.weather.datasource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vg.sales.weather.model.Sales;

/**
 *
 * @author vagner
 */
public class SalesDataSetImpl implements SalesDataSet {

    private Connection connection;

    public SalesDataSetImpl() {
        
    }

    @Override
    public List<Sales> listSales(Date initialDate, Date finalDate) throws ConnectionException, SQLException {
        this.connection = ConnectionFactory.getIntance().getConnection();
        PreparedStatement salesStatament = connection.prepareStatement("SELECT\n" +
"  \n" +
"  filial.idfilial, \n" +
"  filial.numerofilial,\n" +
"  filial.fantasia,\n" +
"  'PRODUTO' AS parte_1,\n" +
"  COALESCE(ven.itens_venda,0) AS prod_itens_venda,\n" +
"  COALESCE(ven.totalvenda,0) AS prod_totalvenda,\n" +
"  COALESCE(dev.itens_devolucao,0) AS prod_itens_devolucao,\n" +
"  COALESCE(dev.totaldevolvido,0) AS prod_totaldevolvido ,\n" +
"  COALESCE(ven.itens_venda,0) - (COALESCE(dev.itens_devolucao,0) * -1) AS prod_saldo_itens,\n" +
"  COALESCE(ven.totalvenda,0) - (COALESCE(dev.totaldevolvido,0) * -1) AS prod_saldo_total,\n" +
"  ROUND((COALESCE(ven.totalvenda,0) - (COALESCE(dev.totaldevolvido,0) * -1)) / CASE WHEN (COALESCE(ven.itens_venda,0) - (COALESCE(dev.itens_devolucao,0) * -1)) = 0 THEN 1\n" +
"                                                                                  ELSE (COALESCE(ven.itens_venda,0) - (COALESCE(dev.itens_devolucao,0) * -1)) END ,2) AS prod_ticket_medio,\n" +
"  \n" +
"  'SERVIÇO' AS parte_2,\n" +
"  COALESCE(srv.itens_venda,0) AS serv_venda,\n" +
"  COALESCE(srv.totalvenda,0) AS serv_totalvenda,\n" +
"  COALESCE(dsrv.itens_devolucao,0) AS serv_devolvido,\n" +
"  COALESCE(dsrv.totaldevolvido,0) AS serv_totaldevolvido ,\n" +
"  COALESCE(srv.itens_venda,0) - (COALESCE(dsrv.itens_devolucao,0) * -1) AS serv_saldo_itens,\n" +
"  COALESCE(srv.totalvenda,0) - (COALESCE(dsrv.totaldevolvido,0) * -1) AS serv_saldo_total ,\n" +
" \n" +
" ROUND((COALESCE(srv.totalvenda,0) - (COALESCE(dsrv.totaldevolvido,0) * -1)) / CASE WHEN (COALESCE(srv.itens_venda,0) - (COALESCE(dsrv.itens_devolucao,0) * -1)) = 0 THEN 1\n" +
"                                                                                ELSE (COALESCE(srv.itens_venda,0) - (COALESCE(dsrv.itens_devolucao,0) * -1)) END    ,2) AS serv_ticket_medio\n" +
"  \n" +
"  FROM glb.filial filial LEFT JOIN  \n" +
"       \n" +
"  \n" +
"             (\n" +
"              SELECT \n" +
"                ib.idfilial,\n" +
"                f.numerofilial,\n" +
"                f.fantasia,\n" +
"                SUM(pmi.quantidade) AS itens_venda,\n" +
"                ROUND(SUM(pmi.quantidade * (ib.totalpresente / ib.quantidade)), 2) AS totalvenda \n" +
"\n" +
"               FROM rst.pedidovendamovimentoitem pmi INNER JOIN rst.itembase ib ON pmi.idfilial = ib.idfilial AND\n" +
"                                                                                   pmi.iditembase = ib.iditembase\n" +
"                                                     INNER JOIN glb.filial f ON f.idfilial = pmi.idfilial\n" +
"               WHERE pmi.idfilial IN (10001,10003,10004,10005,10007,10008,10009,10010,10011,10012,10013,10014,10015,10016,10017,10018,10019,10020,10021,10024)\n" +
"                 AND pmi.datamovimento BETWEEN ? AND ?                                      \n" +
"                 AND pmi.idsituacaopedidovenda = 3\n" +
"                 \n" +
"              GROUP BY 1,2,3) ven\n" +
"ON filial.idfilial = ven.idfilial \n" +
"     \n" +
"LEFT JOIN \n" +
"\n" +
"              (\n" +
"              SELECT \n" +
"                ib.idfilial,\n" +
"                f.numerofilial,\n" +
"                f.fantasia,\n" +
"                SUM(pmi.quantidade) * -1 AS itens_devolucao,\n" +
"                (ROUND(SUM(pmi.quantidade * (ib.totalpresente/ ib.quantidade)), 2)) * -1 AS totaldevolvido \n" +
"\n" +
"               FROM rst.pedidovendamovimentoitem pmi INNER JOIN rst.itembase ib ON pmi.idfilial = ib.idfilial AND\n" +
"                                                                                   pmi.iditembase = ib.iditembase\n" +
"                                                     INNER JOIN glb.filial f ON f.idfilial = pmi.idfilial\n" +
"               WHERE pmi.idfilial IN (10001,10003,10004,10005,10007,10008,10009,10010,10011,10012,10013,10014,10015,10016,10017,10018,10019,10020,10021,10024)\n" +
"                 AND pmi.datamovimento BETWEEN ? AND ?                                      \n" +
"                 AND pmi.idsituacaopedidovenda = 5\n" +
"                 \n" +
"              GROUP BY 1,2,3) AS dev\n" +
"\n" +
"ON filial.idfilial = dev.idfilial\n" +
"\n" +
"\n" +
"LEFT JOIN  \n" +
"\n" +
"              (SELECT \n" +
"                its.idfilial,\n" +
"                f.numerofilial,\n" +
"                f.fantasia,\n" +
"                count(*) AS itens_venda,\n" +
"                ROUND(SUM(its.totalpresente), 2) AS totalvenda   \n" +
"    \n" +
"               FROM  rst.itemservico its LEFT JOIN glb.filial f ON its.idfilial   = f.idfilial\n" +
"                                          \n" +
"                 WHERE its.idfilial IN (10001,10003,10004,10005,10007,10008,10009,10010,10011,10012,10013,10014,10015,10016,10017,10018,10019,10020,10021,10024)\n" +
"                   AND its.datamovimento BETWEEN ? AND ?\n" +
"                   AND its.idoperacaoservico = 202010 \n" +
"              GROUP BY 1,2,3) AS srv\n" +
"\n" +
"\n" +
"ON filial.idfilial = srv.idfilial\n" +
"\n" +
"\n" +
"LEFT JOIN  \n" +
"\n" +
"              (SELECT \n" +
"                its.idfilial,\n" +
"                f.numerofilial,\n" +
"                f.fantasia,\n" +
"                count(*) * -1 AS itens_devolucao,\n" +
"                ROUND(SUM(its.totalpresente), 2) * -1AS totaldevolvido   \n" +
" \n" +
"               FROM  rst.itemservico its LEFT JOIN glb.filial f ON its.idfilial   = f.idfilial\n" +
"                                          \n" +
"                 WHERE its.idfilial IN (10001,10003,10004,10005,10007,10008,10009,10010,10011,10012,10013,10014,10015,10016,10017,10018,10019,10020,10021,10024)\n" +
"                   AND its.datamovimento BETWEEN ? AND ?\n" +
"                   AND its.idoperacaoservico = 201020 \n" +
"              GROUP BY 1,2,3) AS dsrv              \n" +
"\n" +
"ON filial.idfilial = dsrv.idfilial               \n" +
"WHERE filial.idfilial NOT IN (10900, 10901, 10002, 10006, 10023, 10025, 10022)\n" +
"ORDER BY filial.numerofilial");
        salesStatament.setDate(1, initialDate);
        salesStatament.setDate(2, finalDate);
        salesStatament.setDate(3, initialDate);
        salesStatament.setDate(4, finalDate);        
        salesStatament.setDate(5, initialDate);
        salesStatament.setDate(6, finalDate);
        salesStatament.setDate(7, initialDate);
        salesStatament.setDate(8, finalDate);                
        List<Sales> sales = new ArrayList<>();
        
        if (salesStatament.execute()) {
            while(salesStatament.getResultSet().next()){
                Sales sale = new Sales();
                sale.setBranchId(salesStatament.getResultSet().getInt("idfilial"));
                sale.setBranchNumber(salesStatament.getResultSet().getInt("numerofilial"));
                sale.setTrade(salesStatament.getResultSet().getString("fantasia"));

                // Produto
                sale.setProdSaleItens(salesStatament.getResultSet().getInt("prod_itens_venda"));
                sale.setProdSaleTotal(salesStatament.getResultSet().getDouble("prod_totalvenda"));
                sale.setProdDevolutionItens(salesStatament.getResultSet().getInt("prod_itens_devolucao"));
                sale.setProdDevolutionTotal(salesStatament.getResultSet().getDouble("prod_totaldevolvido"));
                sale.setProdBalanceItens(salesStatament.getResultSet().getInt("prod_saldo_itens"));
                sale.setProdBalanceTotal(salesStatament.getResultSet().getDouble("prod_saldo_total"));
                sale.setProdAverageTicket(salesStatament.getResultSet().getDouble("prod_ticket_medio"));

                // Serviço
                sale.setServSale(salesStatament.getResultSet().getInt("serv_venda"));
                sale.setServSaleTotal(salesStatament.getResultSet().getDouble("serv_totalvenda"));
                sale.setServDevolution(salesStatament.getResultSet().getInt("serv_devolvido"));
                sale.setServDevolutionTotal(salesStatament.getResultSet().getDouble("serv_totaldevolvido"));
                sale.setServBalanceItens(salesStatament.getResultSet().getInt("serv_saldo_itens"));
                sale.setServBalanceTotal(salesStatament.getResultSet().getDouble("serv_saldo_total"));
                sale.setServAverageTicket(salesStatament.getResultSet().getDouble("serv_ticket_medio"));

                sales.add(sale);
            }
            
            return sales;
        } else {
            connection.close();
            throw new SQLException("Não houveram resultados válidos!");
        }

    }
}
