package vg.sales.weather.datasource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vg.sales.weather.model.Vendas;

/**
 *
 * @author vagner
 */
public class VendasDataSetImpl implements VendasDataSet {

    private Connection connection;

    public VendasDataSetImpl() {
        
    }

    @Override
    public List<Vendas> listarVendas(Date datainicial, Date datafinal) throws ConnectionException, SQLException {
        this.connection = ConnectionFactory.getIntance().getConnection();
        PreparedStatement vendasStatament = connection.prepareStatement("SELECT\n" +
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
"WHERE filial.idfilial NOT IN (10900, 10901, 10002, 10006, 10023, 10025)\n" +
"ORDER BY filial.numerofilial");
        vendasStatament.setDate(1, datainicial);
        vendasStatament.setDate(2, datafinal);
        vendasStatament.setDate(3, datainicial);
        vendasStatament.setDate(4, datafinal);        
        vendasStatament.setDate(5, datainicial);
        vendasStatament.setDate(6, datafinal);
        vendasStatament.setDate(7, datainicial);
        vendasStatament.setDate(8, datafinal);                
        List<Vendas> vendas = new ArrayList<>();
        
        if (vendasStatament.execute()) {
            while(vendasStatament.getResultSet().next()){
                Vendas venda = new Vendas();
                venda.setIdfilial(vendasStatament.getResultSet().getInt("idfilial"));
                venda.setNumerofilial(vendasStatament.getResultSet().getInt("numerofilial"));
                venda.setFantasia(vendasStatament.getResultSet().getString("fantasia"));

                // Produto
                venda.setProdItensVenda(vendasStatament.getResultSet().getInt("prod_itens_venda"));
                venda.setProdTotalVenda(vendasStatament.getResultSet().getDouble("prod_totalvenda"));
                venda.setProdItensDevolucao(vendasStatament.getResultSet().getInt("prod_itens_devolucao"));
                venda.setProdTotalDevolvido(vendasStatament.getResultSet().getDouble("prod_totaldevolvido"));
                venda.setProdSaldoItens(vendasStatament.getResultSet().getInt("prod_saldo_itens"));
                venda.setProdSaldoTotal(vendasStatament.getResultSet().getDouble("prod_saldo_total"));
                venda.setProdTicketMedio(vendasStatament.getResultSet().getDouble("prod_ticket_medio"));

                // Serviço
                venda.setServVenda(vendasStatament.getResultSet().getInt("serv_venda"));
                venda.setServTotalVenda(vendasStatament.getResultSet().getDouble("serv_totalvenda"));
                venda.setServDevolvido(vendasStatament.getResultSet().getInt("serv_devolvido"));
                venda.setServTotalDevolvido(vendasStatament.getResultSet().getDouble("serv_totaldevolvido"));
                venda.setServSaldoItens(vendasStatament.getResultSet().getInt("serv_saldo_itens"));
                venda.setServSaldoTotal(vendasStatament.getResultSet().getDouble("serv_saldo_total"));
                venda.setServTicketMedio(vendasStatament.getResultSet().getDouble("serv_ticket_medio"));

                vendas.add(venda);
            }
            
            return vendas;
        } else {
            connection.close();
            throw new SQLException("Não houveram resultados válidos!");
        }

    }
}
