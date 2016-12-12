package vg.sales.weather.datasource;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import vg.sales.weather.model.SalesOrder;

/**
 *
 * @author vagner
 */
public class SalesOrderDataSetImpl implements SalesOrderDataSet {

    private EntityManager MANAGER;
    private String jpql;

    @Override
    public List<SalesOrder> listSalesOrder(Integer branchNumber) {
        MANAGER = Persistence.createEntityManagerFactory("sales-weather").createEntityManager();
        MANAGER.getTransaction().begin();
        this.jpql = ("SELECT \n"
                + "   i.idpedidovenda,\n"
                + "   f.numerofilial,\n"
                + "   f.fantasia,\n"
                + "   pv.nome,\n"
                + "   i.datamovimento,\n"
                + "   i.idproduto,\n"
                + "   p.descricao||' '||gx.descricao||' '||gy.descricao AS desc_prod,\n"
                + "   i.quantidade,\n"
                + "   sip.descricao AS situacao_item,\n"
                + "   CASE WHEN i.entregar = 1 THEN 'SIM' ELSE 'NÃO' END AS entregar, \n"
                + "   i.idmapacarga,\n"
                + "   sma.descricao AS situacaomapacarga,\n"
                + "   men.idmapaentrega,\n"
                + "   CASE WHEN i.previsaoentrega IS NULL THEN pv.previsaofaturamento\n"
                + "    ELSE i.previsaoentrega END AS previsaofaturamento,\n"
                + "   sment.descricao AS situacao_entrega,\n"
                + "   CASE WHEN i.montagem = 1 THEN 'SIM' ELSE 'NÃO' END AS montagem,\n"
                + "   mmi.idmapamontagem,\n"
                + "   CASE WHEN i.montagem = 1 THEN i.previsaomontagem ELSE NULL END AS  previsaomontagem,\n"
                + "   smm.descricao AS situacaomontagem,\n"
                + "   COALESCE(fir.numerofilial,f.numerofilial) AS filialreserva,\n"
                + "   spe.descricao AS situacaopedidoloja,\n"
                + "   sment.descricao as idsituacaoentrega,\n"
                + "   smm.descricao as idsituacaomontagem\n"
                + "   \n"
                + "   \n"
                + "\n"
                + "  FROM rst.itembase i INNER JOIN rst.pedidovenda pv ON i.idfilial = pv.idfilial AND\n"
                + "                                                              i.idpedidovenda = pv.idpedidovenda\n"
                + "                      LEFT JOIN rst.mapacarga ma ON ma.idfilial = i.idfilialafaturar AND\n"
                + "                                                    ma.idmapacarga = i.idmapacarga \n"
                + "                                                    \n"
                + "                      \n"
                + "                      LEFT JOIN sis.situacaomapacarga sma ON sma.idsituacaomapacarga = ma.idsituacaomapacarga\n"
                + "                      INNER JOIN glb.pessoa pe ON pe.idcnpj_cpf = pv.idcnpj_cpf                                        \n"
                + "                      LEFT JOIN sis.situacaopedidovenda sip ON sip.idsituacaopedidovenda = i.idsituacaopedidovenda\n"
                + "                      INNER JOIN glb.produto p ON p.idproduto = i.idproduto\n"
                + "                      INNER JOIN glb.gradex gx ON gx.idgradex = i.idgradex\n"
                + "                      INNER JOIN glb.gradey gy ON gy.idgradey = i.idgradey\n"
                + "                      LEFT JOIN glb.localsaldo lc ON lc.idlocalsaldo = i.idlocalsaldo\n"
                + "                      LEFT JOIN rst.itembase iff ON iff.idfilialorigem = i.idfilial AND\n"
                + "                                                    iff.iditembaseorigem = i.iditembase\n"
                + "                      LEFT JOIN rst.nota n ON n.idfilial = CASE WHEN i.idfilial <> i.idfilialafaturar \n"
                + "                                                                  THEN iff.idfilial\n"
                + "                                                           ELSE i.idfilial END\n"
                + "                                           AND n.idregistronota = CASE WHEN i.idfilial <> i.idfilialafaturar \n"
                + "                                                                  THEN iff.idregistronota\n"
                + "                                                           ELSE i.idregistronota END                      \n"
                + "                      LEFT JOIN rst.mapaentreganota men ON men.idfilialnota = n.idfilial AND \n"
                + "                                                           men.idregistronota = n.idregistronota                                     \n"
                + "                      LEFT JOIN sis.situacaomapaentrega sment ON sment.idsituacaoentrega = men.idsituacaoentrega                                  \n"
                + "                      LEFT JOIN rst.mapamontagemitem mmi ON mmi.idfilialitembase = CASE WHEN i.idfilial <> i.idfilialafaturar\n"
                + "                                                                                         THEN iff.idfilial\n"
                + "                                                                                   ELSE i.idfilial END   AND\n"
                + "                                                            mmi.iditembase = CASE WHEN i.idfilial <> i.idfilialafaturar\n"
                + "                                                                                   THEN iff.iditembase\n"
                + "                                                                             ELSE i.iditembase END      \n"
                + "                      LEFT JOIN sis.situacaomapamontagem smm ON smm.idsituacaomontagem = mmi.idsituacaomontagem\n"
                + "                      LEFT JOIN sis.tipomontagem tim ON tim.idtipomontagem = mmi.idtipomontagem                                                            \n"
                + "                      LEFT JOIN glb.filial f ON f.idfilial = i.idfilial\n"
                + "                      LEFT JOIN glb.filial fat ON fat.idfilial = i.idfilialafaturar\n"
                + "                      LEFT JOIN rst.pedidoloja pel ON i.idfilial = pel.idfilialdetalhe AND\n"
                + "                                                     i.idpedidovenda = pel.idpedvendafldetalhe AND\n"
                + "                                                     i.idproduto = pel.idproduto AND\n"
                + "                                                     i.idgradex = pel.idgradex AND\n"
                + "                                                     i.idgradey = pel.idgradey\n"
                + "                      LEFT JOIN sis.situacaopedidoloja spe ON spe.idsituacaopedidoloja = pel.idsituacaopedidoloja\n"
                + "                      LEFT JOIN glb.filial AS fir ON fir.idfilial = pel.idfilial                                \n"
                + "WHERE f.numerofilial IN (:branchNumber) AND sip.idsituacaopedidovenda NOT IN (1, 2, 5, 6, 7, 8, 9) AND (sment.idsituacaoentrega <> 3 AND smm.idsituacaomontagem <> 2)");
        List<SalesOrder> sales = MANAGER.createNativeQuery(jpql, SalesOrder.class).setParameter("branchNumber", branchNumber).getResultList();
        MANAGER.close();
        return sales;
    }

}
