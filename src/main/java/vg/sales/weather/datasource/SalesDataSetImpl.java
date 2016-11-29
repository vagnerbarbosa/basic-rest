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
        PreparedStatement salesStatament = connection.prepareStatement("SELECT \n" +
"   f.idfilial,\n" +
"   f.numerofilial,\n" +
"   f.fantasia,\n" +
"-- PRODUTO\n" +
"   COALESCE(v_pro.totalpresente,0) AS pro_totalpresente,\n" +
"   COALESCE(v_pro.totalitem,0) AS pro_totalitem,\n" +
"   COALESCE(d_pro.totalitem,0) AS pro_devolucao,\n" +
"   COALESCE(v_pro.totalitem,0) - COALESCE(d_pro.totalitem,0) AS p_liquido,\n" +
"-- GARANTIA   \n" +
"   COALESCE(v_sg.totalpresente,0) AS sg_totalpresente,\n" +
"   COALESCE(v_sg.totalitem,0) AS sg_totalitem,      \n" +
"   COALESCE(d_sg.totalitem,0) AS sg_devolucao,\n" +
"   COALESCE(v_sg.totalitem,0) - COALESCE(d_sg.totalitem,0) AS sg_liquido,\n" +
"-- RECARGA\n" +
"   COALESCE(v_rc.totalpresente,0) AS rc_totalpresente,\n" +
"   COALESCE(v_rc.totalitem,0) AS rc_totalitem,      \n" +
"   COALESCE(d_rc.totalitem,0) AS rc_devolucao,\n" +
"   COALESCE(v_rc.totalitem,0) - COALESCE(d_rc.totalitem,0) AS rc_liquido,\n" +
"-- PRESTAMISTA   \n" +
"   COALESCE(v_pre.totalpresente,0) AS pre_totalpresente,\n" +
"   COALESCE(v_pre.totalitem,0) AS pre_totalitem,      \n" +
"   COALESCE(d_pre.totalitem,0) AS pre_devolucao,\n" +
"   COALESCE(v_pre.totalitem,0) - COALESCE(d_pre.totalitem,0) AS pre_liquido, \n" +
"-- ENERGISA       \n" +
"   COALESCE(v_ener.totalpresente,0) AS ener_totalpresente,\n" +
"   COALESCE(v_ener.totalitem,0) AS ener_totalitem,      \n" +
"   COALESCE(d_ener.totalitem,0) AS ener_devolucao,\n" +
"   COALESCE(v_ener.totalitem,0) - COALESCE(d_ener.totalitem,0) AS ener_liquido, \n" +
"-- TOTAL LIQUIDO VENDIDO  \n" +
"   (COALESCE(v_pro.totalitem,0) - COALESCE(d_pro.totalitem,0)) + (COALESCE(v_sg.totalitem,0) - COALESCE(d_sg.totalitem,0)) +\n" +
"   (COALESCE(v_rc.totalitem,0) - COALESCE(d_rc.totalitem,0)) + (COALESCE(v_pre.totalitem,0) - COALESCE(d_pre.totalitem,0)) + \n" +
"   (COALESCE(v_ener.totalitem,0) - COALESCE(d_ener.totalitem,0)) AS total_liquido,\n" +
"-- TOTAL LIQUIDO TOTALPRESENTE     \n" +
"   (COALESCE(v_pro.totalpresente,0) - COALESCE(d_pro.totalpresente,0)) + (COALESCE(v_sg.totalpresente,0) - COALESCE(d_sg.totalpresente,0)) +\n" +
"   (COALESCE(v_rc.totalpresente,0) - COALESCE(d_rc.totalpresente,0)) + (COALESCE(v_pre.totalpresente,0) - COALESCE(d_pre.totalpresente,0)) + \n" +
"   (COALESCE(v_ener.totalpresente,0) - COALESCE(d_ener.totalpresente,0)) AS total_liquido_presente   \n" +
"   \n" +
"  FROM glb.filial f LEFT JOIN \n" +
"\n" +
"                              (SELECT \n" +
"                                ib.idfilial,\n" +
"                                ROUND(SUM(pmi.quantidade * (ib.totalpresente / ib.quantidade)), 2) AS totalpresente,\n" +
"                                ROUND(SUM(pmi.quantidade * (ib.totalitem / ib.quantidade)), 2) AS totalitem   \n" +
"\n" +
"                               FROM rst.pedidovendamovimentoitem pmi INNER JOIN rst.itembase ib ON pmi.idfilial = ib.idfilial AND\n" +
"                                                                                                   pmi.iditembase = ib.iditembase\n" +
"                                                                     LEFT JOIN glb.produtograde pg ON pg.idproduto = ib.idproduto AND\n" +
"                                                                                                      pg.idgradex = ib.idgradex AND\n" +
"                                                                                                      pg.idgradey = ib.idgradey\n" +
"                               WHERE pmi.idfilial IN (10001,10003,10004,10005,10007,10008,10009,10010,10011,10012,10013,10014,10015,10016,10017,10018,10019,10020,10021,10024) AND\n" +
"                                     pmi.datamovimento BETWEEN ? AND ? AND                                     \n" +
"                                     pmi.idsituacaopedidovenda = 3 AND\n" +
"                                     pg.idtipoproduto <> 9 -- KIT\n" +
"									\n" +
"                               GROUP BY  ib.idfilial\n" +
"                               ORDER BY ib.idfilial) AS v_pro   ON v_pro.idfilial = f.idfilial\n" +
"                               \n" +
"                               \n" +
"                    LEFT JOIN\n" +
"                    \n" +
"                              (SELECT \n" +
"                                ib.idfilial,\n" +
"                                ROUND(SUM(pmi.quantidade * (ib.totalpresente / ib.quantidade)), 2) AS totalpresente,\n" +
"                                ROUND(SUM(pmi.quantidade * (ib.totalitem / ib.quantidade)), 2) AS totalitem   \n" +
"\n" +
"                               FROM rst.pedidovendamovimentoitem pmi INNER JOIN rst.itembase ib ON pmi.idfilial = ib.idfilial AND\n" +
"                                                                                                   pmi.iditembase = ib.iditembase\n" +
"                                                                     LEFT JOIN glb.produtograde pg ON pg.idproduto = ib.idproduto AND\n" +
"                                                                                                      pg.idgradex = ib.idgradex AND\n" +
"                                                                                                      pg.idgradey = ib.idgradey\n" +
"                               WHERE pmi.idfilial IN (10001,10003,10004,10005,10007,10008,10009,10010,10011,10012,10013,10014,10015,10016,10017,10018,10019,10020,10021,10024) AND\n" +
"                                     pmi.datamovimento BETWEEN ? AND ? AND                                     \n" +
"                                     pmi.idsituacaopedidovenda = 5 AND\n" +
"                                     pg.idtipoproduto <> 9 -- KIT\n" +
"									\n" +
"                               GROUP BY  ib.idfilial\n" +
"                               ORDER BY ib.idfilial) AS d_pro   ON d_pro.idfilial = f.idfilial           \n" +
"                               \n" +
"                    LEFT JOIN \n" +
"                    \n" +
"                              (SELECT \n" +
"                                 its.idfilial,\n" +
"                                 ROUND(SUM(its.totalpresente), 2) AS totalpresente,\n" +
"                                 ROUND(SUM(its.totalitem), 2) AS totalitem\n" +
"              \n" +
"                                 FROM  rst.itemservico its INNER JOIN glb.filial f ON its.idfilial   = f.idfilial\n" +
"                                                           INNER JOIN glb.servico se ON se.idservico = its.idservico\n" +
"                                                           INNER JOIN glb.gruposervico gs ON gs.idgruposervico = se.idgruposervico\n" +
"                                                    \n" +
"                               WHERE its.idfilial IN (10001,10003,10004,10005,10007,10008,10009,10010,10011,10012,10013,10014,10015,10016,10017,10018,10019,10020,10021,10024) AND\n" +
"                                     its.datamovimento BETWEEN ? AND ? AND\n" +
"                                     gs.idtiposervico = 2 AND -- Garantia Extendida\n" +
"                                     its.idoperacaoservico = 202010 -- Venda\n" +
"									 \n" +
"                               GROUP BY its.idfilial) AS v_sg   ON v_sg.idfilial = f.idfilial                      \n" +
"                               \n" +
"                    LEFT JOIN\n" +
"                    \n" +
"                                                            (SELECT \n" +
"                                 its.idfilial,\n" +
"                                 ROUND(SUM(its.totalpresente), 2) AS totalpresente,\n" +
"                                 ROUND(SUM(its.totalitem), 2) AS totalitem\n" +
"              \n" +
"                                 FROM  rst.itemservico its INNER JOIN glb.filial f ON its.idfilial   = f.idfilial\n" +
"                                                           INNER JOIN glb.servico se ON se.idservico = its.idservico\n" +
"                                                           INNER JOIN glb.gruposervico gs ON gs.idgruposervico = se.idgruposervico\n" +
"                                                    \n" +
"                               WHERE its.idfilial IN (10001,10003,10004,10005,10007,10008,10009,10010,10011,10012,10013,10014,10015,10016,10017,10018,10019,10020,10021,10024) AND\n" +
"                                     its.datamovimento BETWEEN ? AND ? AND\n" +
"                                     gs.idtiposervico = 2 AND -- Garantia Extendida\n" +
"                                     its.idoperacaoservico = 201020 -- Cancelamento\n" +
"									 \n" +
"                               GROUP BY its.idfilial) AS d_sg   ON d_sg.idfilial = f.idfilial                                  \n" +
"                               \n" +
"                               \n" +
"                    LEFT JOIN \n" +
"                    \n" +
"                              (SELECT \n" +
"                                 its.idfilial,\n" +
"                                 ROUND(SUM(its.totalpresente), 2) AS totalpresente,\n" +
"                                 ROUND(SUM(its.totalitem), 2) AS totalitem\n" +
"              \n" +
"                                 FROM  rst.itemservico its INNER JOIN glb.filial f ON its.idfilial   = f.idfilial\n" +
"                                                           INNER JOIN glb.servico se ON se.idservico = its.idservico\n" +
"                                                           INNER JOIN glb.gruposervico gs ON gs.idgruposervico = se.idgruposervico\n" +
"                                                    \n" +
"                               WHERE its.idfilial IN (10001,10003,10004,10005,10007,10008,10009,10010,10011,10012,10013,10014,10015,10016,10017,10018,10019,10020,10021,10024) AND\n" +
"                                     its.datamovimento BETWEEN ? AND ? AND\n" +
"                                     gs.idtiposervico IN (11,12,13) AND -- Recarga\n" +
"                                     its.idoperacaoservico = 202010 -- Venda\n" +
"									 \n" +
"                               GROUP BY its.idfilial) AS v_rc   ON v_rc.idfilial = f.idfilial                      \n" +
"                               \n" +
"                    LEFT JOIN\n" +
"                    \n" +
"                                                            (SELECT \n" +
"                                 its.idfilial,\n" +
"                                 ROUND(SUM(its.totalpresente), 2) AS totalpresente,\n" +
"                                 ROUND(SUM(its.totalitem), 2) AS totalitem\n" +
"              \n" +
"                                 FROM  rst.itemservico its INNER JOIN glb.filial f ON its.idfilial   = f.idfilial\n" +
"                                                           INNER JOIN glb.servico se ON se.idservico = its.idservico\n" +
"                                                           INNER JOIN glb.gruposervico gs ON gs.idgruposervico = se.idgruposervico\n" +
"                                                    \n" +
"                               WHERE its.idfilial IN (10001,10003,10004,10005,10007,10008,10009,10010,10011,10012,10013,10014,10015,10016,10017,10018,10019,10020,10021,10024) AND\n" +
"                                     its.datamovimento BETWEEN ? AND ? AND\n" +
"                                     gs.idtiposervico IN (11,12,13) AND -- Recarga\n" +
"                                     its.idoperacaoservico = 201020 -- Cancelamento\n" +
"									 \n" +
"                               GROUP BY its.idfilial) AS d_rc   ON d_rc.idfilial = f.idfilial\n" +
"                               \n" +
"                               \n" +
"                    LEFT JOIN \n" +
"                    \n" +
"                              (SELECT \n" +
"                                 its.idfilial,\n" +
"                                 ROUND(SUM(its.totalpresente), 2) AS totalpresente,\n" +
"                                 ROUND(SUM(its.totalitem), 2) AS totalitem\n" +
"              \n" +
"                                 FROM  rst.itemservico its INNER JOIN glb.filial f ON its.idfilial   = f.idfilial\n" +
"                                                           INNER JOIN glb.servico se ON se.idservico = its.idservico\n" +
"                                                           INNER JOIN glb.gruposervico gs ON gs.idgruposervico = se.idgruposervico\n" +
"                                                    \n" +
"                               WHERE its.idfilial IN (10001,10003,10004,10005,10007,10008,10009,10010,10011,10012,10013,10014,10015,10016,10017,10018,10019,10020,10021,10024) AND\n" +
"                                     its.datamovimento BETWEEN ? AND ? AND\n" +
"                                     gs.idtiposervico = 3 AND -- Prestamista\n" +
"                                     its.idoperacaoservico = 202010 -- Venda\n" +
"									 \n" +
"                               GROUP BY its.idfilial) AS v_pre   ON v_pre.idfilial = f.idfilial                      \n" +
"                               \n" +
"                    LEFT JOIN\n" +
"                    \n" +
"                                                            (SELECT \n" +
"                                 its.idfilial,\n" +
"                                 ROUND(SUM(its.totalpresente), 2) AS totalpresente,\n" +
"                                 ROUND(SUM(its.totalitem), 2) AS totalitem\n" +
"              \n" +
"                                 FROM  rst.itemservico its INNER JOIN glb.filial f ON its.idfilial   = f.idfilial\n" +
"                                                           INNER JOIN glb.servico se ON se.idservico = its.idservico\n" +
"                                                           INNER JOIN glb.gruposervico gs ON gs.idgruposervico = se.idgruposervico\n" +
"                                                    \n" +
"                               WHERE its.idfilial IN (10001,10003,10004,10005,10007,10008,10009,10010,10011,10012,10013,10014,10015,10016,10017,10018,10019,10020,10021,10024) AND\n" +
"                                     its.datamovimento BETWEEN ? AND ? AND\n" +
"                                     gs.idtiposervico = 3 AND -- Prestamista\n" +
"                                     its.idoperacaoservico = 201020 -- Cancelamento\n" +
"									 \n" +
"                               GROUP BY its.idfilial) AS d_pre   ON d_pre.idfilial = f.idfilial\n" +
"                               \n" +
"                               \n" +
"                    LEFT JOIN \n" +
"                    \n" +
"                              (SELECT \n" +
"                                 its.idfilial,\n" +
"                                 ROUND(SUM(its.totalpresente), 2) AS totalpresente,\n" +
"                                 ROUND(SUM(its.totalitem), 2) AS totalitem\n" +
"              \n" +
"                                 FROM  rst.itemservico its INNER JOIN glb.filial f ON its.idfilial   = f.idfilial\n" +
"                                                           INNER JOIN glb.servico se ON se.idservico = its.idservico\n" +
"                                                           INNER JOIN glb.gruposervico gs ON gs.idgruposervico = se.idgruposervico\n" +
"                                                    \n" +
"                               WHERE its.idfilial IN (10001,10003,10004,10005,10007,10008,10009,10010,10011,10012,10013,10014,10015,10016,10017,10018,10019,10020,10021,10024) AND\n" +
"                                     its.datamovimento BETWEEN ? AND ? AND\n" +
"                                     gs.idtiposervico = 16 AND -- Receita Financeira\n" +
"                                     its.idservico = 47 AND -- Contribuição Energisa\n" +
"                                     its.idoperacaoservico = 202010 -- Venda\n" +
"									 \n" +
"                               GROUP BY its.idfilial) AS v_ener   ON v_ener.idfilial = f.idfilial                      \n" +
"                               \n" +
"                    LEFT JOIN\n" +
"                    \n" +
"                                                            (SELECT \n" +
"                                 its.idfilial,\n" +
"                                 ROUND(SUM(its.totalpresente), 2) AS totalpresente,\n" +
"                                 ROUND(SUM(its.totalitem), 2) AS totalitem\n" +
"              \n" +
"                                 FROM  rst.itemservico its INNER JOIN glb.filial f ON its.idfilial   = f.idfilial\n" +
"                                                           INNER JOIN glb.servico se ON se.idservico = its.idservico\n" +
"                                                           INNER JOIN glb.gruposervico gs ON gs.idgruposervico = se.idgruposervico\n" +
"                                                    \n" +
"                               WHERE its.idfilial IN (10001,10003,10004,10005,10007,10008,10009,10010,10011,10012,10013,10014,10015,10016,10017,10018,10019,10020,10021,10024) AND\n" +
"                                     its.datamovimento BETWEEN ? AND ? AND\n" +
"                                     gs.idtiposervico = 16 AND -- Receita Financeira\n" +
"                                     its.idservico = 47 AND -- Contribuição Energisa\n" +
"                                     its.idoperacaoservico = 201020 -- Cancelamento\n" +
"									 \n" +
"                               GROUP BY its.idfilial) AS d_ener   ON d_ener.idfilial = f.idfilial\n" +
"							   WHERE  f.idfilial NOT IN (10900, 10901, 10002, 10006, 10023, 10025, 10022)\n" +
"                               ");
        

/* Setando os valores passados para o webservice de data inicial e final nos campos da query */
        
        salesStatament.setDate(1, initialDate);
        salesStatament.setDate(2, finalDate);
        salesStatament.setDate(3, initialDate);
        salesStatament.setDate(4, finalDate);        
        salesStatament.setDate(5, initialDate);
        salesStatament.setDate(6, finalDate);
        salesStatament.setDate(7, initialDate);
        salesStatament.setDate(8, finalDate);
        salesStatament.setDate(9, initialDate);
        salesStatament.setDate(10, finalDate);        
        salesStatament.setDate(11, initialDate);
        salesStatament.setDate(12, finalDate);        
        salesStatament.setDate(13, initialDate);
        salesStatament.setDate(14, finalDate);        
        salesStatament.setDate(15, initialDate);
        salesStatament.setDate(16, finalDate);        
        salesStatament.setDate(17, initialDate);
        salesStatament.setDate(18, finalDate);        
        salesStatament.setDate(19, initialDate);
        salesStatament.setDate(20, finalDate);        

/* Setando os valores passados para o webservice de data inicial e final nos campos da query */        

        List<Sales> sales = new ArrayList<>();
        
        if (salesStatament.execute()) {
            while(salesStatament.getResultSet().next()){
                Sales sale = new Sales();
                sale.setBranchId(salesStatament.getResultSet().getInt("idfilial"));
                sale.setBranchNumber(salesStatament.getResultSet().getInt("numerofilial"));
                sale.setTrade(salesStatament.getResultSet().getString("fantasia"));

                // Produto
                sale.setProdSaleItens(salesStatament.getResultSet().getInt(1)); 
                sale.setProdSaleTotal(salesStatament.getResultSet().getDouble("pro_totalitem")); //PRODUT BRUTO
                sale.setProdDevolutionItens(salesStatament.getResultSet().getInt(1));
                sale.setProdDevolutionTotal(salesStatament.getResultSet().getDouble("pro_devolucao")); //DEV PRODU
                sale.setProdBalanceItens(salesStatament.getResultSet().getInt(1));
                sale.setProdBalanceTotal(salesStatament.getResultSet().getDouble("p_liquido")); //PROD LIQU
                sale.setProdAverageTicket(salesStatament.getResultSet().getDouble(1));

                // Serviço
                sale.setServSale(salesStatament.getResultSet().getInt(1));
                sale.setServSaleTotal(salesStatament.getResultSet().getDouble("sg_totalitem"));
                sale.setServDevolution(salesStatament.getResultSet().getInt(1));
                sale.setServDevolutionTotal(salesStatament.getResultSet().getDouble("sg_devolucao"));
                sale.setServBalanceItens(salesStatament.getResultSet().getInt(1));
                sale.setServBalanceTotal(salesStatament.getResultSet().getDouble("sg_liquido")); //SERVIÇO LIQ
                sale.setServAverageTicket(salesStatament.getResultSet().getDouble("rc_liquido")); //REG

                sales.add(sale);
            }
            
            return sales;
        } else {
            connection.close();
            throw new SQLException("Não houveram resultados válidos!");
        }

    }
}
