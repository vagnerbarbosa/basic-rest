package vg.sales.weather.datasource;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import vg.sales.weather.model.SalesOrder;
import vg.sales.weather.model.Product;

/**
 *
 * @author vagner
 */
public class SalesOrderDataSetImpl implements SalesOrderDataSet {

    private static EntityManager MANAGER;
    private String jpql;
    
    static {
    MANAGER = Persistence.createEntityManagerFactory("PostgresDS").createEntityManager();
    }  
    
    @Override
    public List<SalesOrder> listSalesOrder(Integer branchNumber) {
        this.jpql = ("SELECT DISTINCT\n" +
"\n" +
"COALESCE(pedido.idpedidovenda,0) as idpedidovenda,\n" +
"COALESCE(pedido.numerofilial,0) as numerofilial,\n" +
"COALESCE(pedido.fantasia,'ERRO') as fantasia,\n" +
"pedido.datamovimento,\n" +
"COALESCE(pedido.nome,'ERRO') as nome,\n" +
"COALESCE(pedido.situacaopedidoloja,'A Faturar') as situacaopedidoloja\n" +
"                \n" +
"FROM (SELECT 	i.idpedidovenda,\n" +
"f.numerofilial,\n" +
"f.fantasia,\n" +
"pv.nome,\n" +
"i.datamovimento,\n" +
"i.idproduto,\n" +
"p.descricao||' '||gx.descricao||' '||gy.descricao AS desc_prod,\n" +
"i.quantidade,\n" +
"sip.descricao AS situacao_item,\n" +
"CASE WHEN i.entregar = 1 THEN 'SIM' ELSE 'NÃO' END AS entregar, \n" +
"i.idmapacarga,\n" +
"sma.descricao AS situacaomapacarga,\n" +
"men.idmapaentrega,\n" +
"CASE WHEN i.previsaoentrega IS NULL THEN pv.previsaofaturamento ELSE i.previsaoentrega END AS previsaofaturamento,\n" +
"sment.descricao AS situacao_entrega,\n" +
"CASE WHEN i.montagem = 1 THEN 'SIM' ELSE 'NÃO' END AS montagem,\n" +
"mmi.idmapamontagem,\n" +
"CASE WHEN i.montagem = 1 THEN i.previsaomontagem ELSE NULL END AS  previsaomontagem,\n" +
"smm.descricao AS situacaomontagem,\n" +
"COALESCE(fir.numerofilial,f.numerofilial) AS filialreserva,\n" +
"spe.descricao AS situacaopedidoloja,\n" +
"REPLACE( CASE WHEN pel.idsituacaopedidoloja = 4 AND COALESCE(pel.idmapacargaloja,0) = 0 \n" +
"                   THEN '-1' \n" +
"            ELSE CAST(pel.idmapacargaloja as TEXT) END, '-1', 'Faturado Sem Mapa') AS idmapacargaloja,\n" +
"sment.descricao as idsituacaoentrega,\n" +
"smm.descricao as idsituacaomontagem  FROM rst.itembase i INNER JOIN rst.pedidovenda pv ON (i.idfilial = pv.idfilial AND i.idpedidovenda = pv.idpedidovenda)\n" +
"						LEFT JOIN rst.mapacarga ma ON (ma.idfilial = i.idfilialafaturar AND ma.idmapacarga = i.idmapacarga)\n" +
"LEFT JOIN sis.situacaomapacarga sma ON (sma.idsituacaomapacarga = ma.idsituacaomapacarga)\n" +
"INNER JOIN glb.pessoa pe ON (pe.idcnpj_cpf = pv.idcnpj_cpf)\n" +
"LEFT JOIN sis.situacaopedidovenda sip ON (sip.idsituacaopedidovenda = i.idsituacaopedidovenda)\n" +
"INNER JOIN glb.produto p ON (p.idproduto = i.idproduto)\n" +
"INNER JOIN glb.gradex gx ON (gx.idgradex = i.idgradex)\n" +
"INNER JOIN glb.gradey gy ON (gy.idgradey = i.idgradey)\n" +
"LEFT JOIN glb.localsaldo lc ON (lc.idlocalsaldo = i.idlocalsaldo)\n" +
"LEFT JOIN rst.itembase iff ON (iff.idfilialorigem = i.idfilial AND iff.iditembaseorigem = i.iditembase)\n" +
"LEFT JOIN rst.nota n ON (n.idfilial = CASE WHEN i.idfilial <> i.idfilialafaturar THEN iff.idfilial ELSE i.idfilial END AND n.idregistronota = CASE WHEN i.idfilial <> i.idfilialafaturar THEN iff.idregistronota ELSE i.idregistronota END)                      \n" +
"LEFT JOIN rst.mapaentreganota men ON (men.idfilialnota = n.idfilial AND men.idregistronota = n.idregistronota)\n" +
"LEFT JOIN sis.situacaomapaentrega sment ON (sment.idsituacaoentrega = men.idsituacaoentrega)\n" +
"LEFT JOIN rst.mapamontagemitem mmi ON (mmi.idfilialitembase = CASE WHEN i.idfilial <> i.idfilialafaturar THEN iff.idfilial ELSE i.idfilial END AND mmi.iditembase = CASE WHEN i.idfilial <> i.idfilialafaturar THEN iff.iditembase ELSE i.iditembase END)\n" +
"						LEFT JOIN sis.situacaomapamontagem smm ON (smm.idsituacaomontagem = mmi.idsituacaomontagem)\n" +
"LEFT JOIN sis.tipomontagem tim ON (tim.idtipomontagem = mmi.idtipomontagem)                                                            \n" +
"LEFT JOIN glb.filial f ON (f.idfilial = i.idfilial)\n" +
"LEFT JOIN glb.filial fat ON (fat.idfilial = i.idfilialafaturar)\n" +
"LEFT JOIN rst.pedidoloja pel ON (i.idfilial = pel.idfilialdetalhe AND i.idpedidovenda = pel.idpedvendafldetalhe AND i.idproduto = pel.idproduto AND i.idgradex = pel.idgradex AND i.idgradey = pel.idgradey)\n" +
"						LEFT JOIN sis.situacaopedidoloja spe ON (spe.idsituacaopedidoloja = pel.idsituacaopedidoloja)\n" +
"LEFT JOIN glb.filial AS fir ON (fir.idfilial = pel.idfilial)\n" +
"						WHERE f.numerofilial IN (:branchNumber)  ) AS pedido\n" +
"                        WHERE (pedido.entregar = 'SIM') AND ((pedido.situacaomapacarga <> 'Faturado') OR (pedido.situacaomapacarga IS NULL)) AND (pedido.situacao_item NOT IN ('Excluido', 'Cancelado'))");
        List<SalesOrder> sales = MANAGER.createNativeQuery(jpql, SalesOrder.class).setParameter("branchNumber", branchNumber).getResultList();
        return sales;
    }

    @Override
    public List<Product> listSalesProducts(Integer branchNumber) {
        this.jpql = ("SELECT\n" +
"COALESCE(prd.idpedidovenda,0) as idpedidovenda,\n" +
"COALESCE(prd.idproduto,0) as idproduto,\n" +
"COALESCE(prd.desc_prod,'ERRO') as desc_prod,\n" +
"COALESCE(prd.quantidade,0) as quantidade,\n" +
"COALESCE(prd.situacao_item,'') as situacao_item,\n" +
"COALESCE(prd.entregar,'NÃO') as entregar, \n" +
"COALESCE(prd.idmapacarga,0) as idmapacarga,\n" +
"COALESCE(prd.situacaomapacarga,'A Faturar') as situacaomapacarga,\n" +
"COALESCE(prd.idmapaentrega,0) as idmapaentrega,\n" +
"prd.previsaofaturamento,\n" +
"COALESCE(prd.situacao_entrega, '') as situacao_entrega,\n" +
"COALESCE(prd.montagem,'') as montagem,\n" +
"COALESCE(prd.idmapamontagem,0) as idmapamontagem,\n" +
"prd.previsaomontagem,\n" +
"CASE WHEN prd.montagem LIKE 'NÃO' THEN COALESCE(prd.situacaomontagem,'Não se Aplica') ELSE COALESCE(prd.situacaomontagem,'A Executar') END AS situacaomontagem,\n" +
"COALESCE(prd.filialreserva,prd.numerofilial) as filialreserva,\n" +
"COALESCE(prd.idsituacaoentrega,'0') as idsituacaoentrega,\n" +
"COALESCE(prd.idsituacaomontagem,'0') as idsituacaomontagem,\n" +
"prd.idmapacargaloja \n" +
"                \n" +
"FROM (SELECT 	i.idpedidovenda,\n" +
"f.numerofilial,\n" +
"f.fantasia,\n" +
"pv.nome,\n" +
"i.datamovimento,\n" +
"i.idproduto,\n" +
"p.descricao||' '||gx.descricao||' '||gy.descricao AS desc_prod,\n" +
"i.quantidade,\n" +
"sip.descricao AS situacao_item,\n" +
"CASE WHEN i.entregar = 1 THEN 'SIM' ELSE 'NÃO' END AS entregar, \n" +
"i.idmapacarga,\n" +
"sma.descricao AS situacaomapacarga,\n" +
"men.idmapaentrega,\n" +
"CASE WHEN i.previsaoentrega IS NULL THEN pv.previsaofaturamento ELSE i.previsaoentrega END AS previsaofaturamento,\n" +
"sment.descricao AS situacao_entrega,\n" +
"CASE WHEN i.montagem = 1 THEN 'SIM' ELSE 'NÃO' END AS montagem,\n" +
"mmi.idmapamontagem,\n" +
"CASE WHEN i.montagem = 1 THEN i.previsaomontagem ELSE NULL END AS  previsaomontagem,\n" +
"smm.descricao AS situacaomontagem,\n" +
"COALESCE(fir.numerofilial,f.numerofilial) AS filialreserva,\n" +
"spe.descricao AS situacaopedidoloja,\n" +
"REPLACE( CASE WHEN pel.idsituacaopedidoloja = 4 AND COALESCE(pel.idmapacargaloja,0) = 0 \n" +
"                   THEN '-1' \n" +
"            ELSE CAST(pel.idmapacargaloja as TEXT) END, '-1', 'Faturado Sem Mapa') AS idmapacargaloja,\n" +
"sment.descricao as idsituacaoentrega,\n" +
"smm.descricao as idsituacaomontagem  FROM rst.itembase i INNER JOIN rst.pedidovenda pv ON (i.idfilial = pv.idfilial AND i.idpedidovenda = pv.idpedidovenda)\n" +
"						LEFT JOIN rst.mapacarga ma ON (ma.idfilial = i.idfilialafaturar AND ma.idmapacarga = i.idmapacarga)\n" +
"LEFT JOIN sis.situacaomapacarga sma ON (sma.idsituacaomapacarga = ma.idsituacaomapacarga)\n" +
"INNER JOIN glb.pessoa pe ON (pe.idcnpj_cpf = pv.idcnpj_cpf)\n" +
"LEFT JOIN sis.situacaopedidovenda sip ON (sip.idsituacaopedidovenda = i.idsituacaopedidovenda)\n" +
"INNER JOIN glb.produto p ON (p.idproduto = i.idproduto)\n" +
"INNER JOIN glb.gradex gx ON (gx.idgradex = i.idgradex)\n" +
"INNER JOIN glb.gradey gy ON (gy.idgradey = i.idgradey)\n" +
"LEFT JOIN glb.localsaldo lc ON (lc.idlocalsaldo = i.idlocalsaldo)\n" +
"LEFT JOIN rst.itembase iff ON (iff.idfilialorigem = i.idfilial AND iff.iditembaseorigem = i.iditembase)\n" +
"LEFT JOIN rst.nota n ON (n.idfilial = CASE WHEN i.idfilial <> i.idfilialafaturar THEN iff.idfilial ELSE i.idfilial END AND n.idregistronota = CASE WHEN i.idfilial <> i.idfilialafaturar THEN iff.idregistronota ELSE i.idregistronota END)                      \n" +
"LEFT JOIN rst.mapaentreganota men ON (men.idfilialnota = n.idfilial AND men.idregistronota = n.idregistronota)\n" +
"LEFT JOIN sis.situacaomapaentrega sment ON (sment.idsituacaoentrega = men.idsituacaoentrega)\n" +
"LEFT JOIN rst.mapamontagemitem mmi ON (mmi.idfilialitembase = CASE WHEN i.idfilial <> i.idfilialafaturar THEN iff.idfilial ELSE i.idfilial END AND mmi.iditembase = CASE WHEN i.idfilial <> i.idfilialafaturar THEN iff.iditembase ELSE i.iditembase END)\n" +
"						LEFT JOIN sis.situacaomapamontagem smm ON (smm.idsituacaomontagem = mmi.idsituacaomontagem)\n" +
"LEFT JOIN sis.tipomontagem tim ON (tim.idtipomontagem = mmi.idtipomontagem)                                                            \n" +
"LEFT JOIN glb.filial f ON (f.idfilial = i.idfilial)\n" +
"LEFT JOIN glb.filial fat ON (fat.idfilial = i.idfilialafaturar)\n" +
"LEFT JOIN rst.pedidoloja pel ON (i.idfilial = pel.idfilialdetalhe AND i.idpedidovenda = pel.idpedvendafldetalhe AND i.idproduto = pel.idproduto AND i.idgradex = pel.idgradex AND i.idgradey = pel.idgradey)\n" +
"						LEFT JOIN sis.situacaopedidoloja spe ON (spe.idsituacaopedidoloja = pel.idsituacaopedidoloja)\n" +
"LEFT JOIN glb.filial AS fir ON (fir.idfilial = pel.idfilial)\n" +
"						WHERE f.numerofilial IN (:branchNumber)) AS prd\n" +
"                        WHERE (prd.entregar = 'SIM') AND ((prd.situacaomapacarga <> 'Faturado') OR (prd.situacaomapacarga IS NULL)) AND (prd.situacao_item NOT IN ('Excluido', 'Cancelado'))");
        List<Product> sales = MANAGER.createNativeQuery(jpql, Product.class).setParameter("branchNumber", branchNumber).getResultList();
        return sales;
    }

    

}
