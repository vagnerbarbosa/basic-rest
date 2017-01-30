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

    private static final EntityManager MANAGER;
    private String jpql;
    
    static {
    MANAGER = Persistence.createEntityManagerFactory("PostgresDS").createEntityManager();
    }  
    
    @Override
    public List<SalesOrder> listSalesOrder(Integer branchNumber) {
        this.jpql = ("SELECT DISTINCT\n" +
"pedido.idpedidovenda,\n" +
"pedido.numerofilial,\n" +
"COALESCE(pedido.fantasia,'ERRO') as fantasia,\n" +
"pedido.datainclusao as datamovimento,\n" +
"(current_date - pedido.datainclusao) as dias,\n" +
"COALESCE(pedido.nome,'ERRO') as nome,\n" +
"COALESCE(pedido.cidade,'ERRO') as cidade,\n" +
"COALESCE(pedido.bairro,'ERRO') as bairro,\n" +
"COALESCE(pedido.situacaopedidoloja,'A Faturar') as situacaopedidoloja                 \n" +
"	FROM (SELECT DISTINCT i.idpedidovenda,\n" +
"	f.numerofilial,\n" +
"	f.fantasia,\n" +
"	pv.nome,\n" +
"    pv.datainclusao,\n" +
"    city.cidade,\n" +
"	ende.bairro,\n" +
"	i.datamovimento,\n" +
"    i.idsituacaopedidovenda,\n" +
"	i.idproduto,\n" +
"	p.descricao||' '||gx.descricao||' '||gy.descricao AS desc_prod,\n" +
"	i.quantidade,\n" +
"	sip.descricao AS situacao_item,\n" +
"	CASE WHEN i.entregar = 1 THEN 'SIM' ELSE 'NÃO' END AS entregar, \n" +
"	i.idmapacarga,\n" +
"	sma.descricao AS situacaomapacarga,\n" +
"	men.idmapaentrega,\n" +
"	CASE WHEN i.previsaoentrega IS NULL THEN pv.previsaofaturamento ELSE i.previsaoentrega END AS previsaofaturamento,\n" +
"	('Entregue a ' || (current_date - i.datamovimento) || ' dias') AS situacao_entrega,\n" +
"	CASE WHEN i.montagem = 1 THEN 'SIM' ELSE 'NÃO' END AS montagem,\n" +
"	mmi.idmapamontagem,\n" +
"	CASE WHEN i.montagem = 1 THEN i.previsaomontagem ELSE NULL END AS  previsaomontagem,\n" +
"	smm.descricao AS situacaomontagem,\n" +
"	COALESCE(fir.numerofilial,f.numerofilial) AS filialreserva,\n" +
"	sip.descricao AS situacaopedidoloja,\n" +
"	sment.descricao as idsituacaoentrega,\n" +
"	smm.descricao as idsituacaomontagem FROM rst.itembase i INNER JOIN rst.pedidovenda pv ON (i.idfilial = pv.idfilial AND i.idpedidovenda = pv.idpedidovenda)\n" +
"							LEFT JOIN rst.mapacarga ma ON (ma.idfilial = i.idfilialafaturar AND ma.idmapacarga = i.idmapacarga)\n" +
"                            LEFT JOIN sis.situacaomapacarga sma ON (sma.idsituacaomapacarga = ma.idsituacaomapacarga)\n" +
"                            INNER JOIN glb.pessoa pe ON (pe.idcnpj_cpf = pv.idcnpj_cpf)\n" +
"                            LEFT JOIN sis.situacaopedidovenda sip ON (sip.idsituacaopedidovenda = i.idsituacaopedidovenda)\n" +
"                            INNER JOIN glb.produto p ON (p.idproduto = i.idproduto)\n" +
"                            INNER JOIN glb.gradex gx ON (gx.idgradex = i.idgradex)\n" +
"                            INNER JOIN glb.gradey gy ON (gy.idgradey = i.idgradey)\n" +
"                            LEFT JOIN glb.localsaldo lc ON (lc.idlocalsaldo = i.idlocalsaldo)\n" +
"                            LEFT JOIN rst.itembase iff ON (iff.idfilialorigem = i.idfilial AND iff.iditembaseorigem = i.iditembase)\n" +
"                            LEFT JOIN rst.nota n ON (n.idfilial = CASE WHEN i.idfilial <> i.idfilialafaturar THEN iff.idfilial ELSE i.idfilial END AND n.idregistronota = CASE WHEN i.idfilial <> i.idfilialafaturar THEN iff.idregistronota ELSE i.idregistronota END)                      \n" +
"                            LEFT JOIN rst.mapaentreganota men ON (men.idfilialnota = n.idfilial AND men.idregistronota = n.idregistronota)\n" +
"                            LEFT JOIN sis.situacaomapaentrega sment ON (sment.idsituacaoentrega = men.idsituacaoentrega)\n" +
"                            LEFT JOIN rst.mapamontagemitem mmi ON (mmi.idfilialitembase = CASE WHEN i.idfilial <> i.idfilialafaturar THEN iff.idfilial ELSE i.idfilial END AND mmi.iditembase = CASE WHEN i.idfilial <> i.idfilialafaturar THEN iff.iditembase ELSE i.iditembase END)\n" +
"                            LEFT JOIN sis.situacaomapamontagem smm ON (smm.idsituacaomontagem = mmi.idsituacaomontagem)\n" +
"                            LEFT JOIN sis.tipomontagem tim ON (tim.idtipomontagem = mmi.idtipomontagem)                                                            \n" +
"                            LEFT JOIN glb.filial f ON (f.idfilial = i.idfilial)\n" +
"                            LEFT JOIN glb.filial fat ON (fat.idfilial = i.idfilialafaturar)\n" +
"                            LEFT JOIN rst.pedidoloja pel ON (i.idfilial = pel.idfilialdetalhe AND i.idpedidovenda = pel.idpedvendafldetalhe AND i.idproduto = pel.idproduto AND i.idgradex = pel.idgradex AND i.idgradey = pel.idgradey)\n" +
"							LEFT JOIN sis.situacaopedidoloja spe ON (spe.idsituacaopedidoloja = pel.idsituacaopedidoloja)\n" +
"                            LEFT JOIN glb.filial AS fir ON (fir.idfilial = pel.idfilial)\n" +
"                            INNER JOIN glb.endereco AS ende ON (pv.idcnpj_cpf = ende.idcnpj_cpf)\n" +
"                            INNER JOIN glb.cidade AS city ON (ende.idcidade = city.idcidade)\n" +
"                            \n" +
"							WHERE (f.numerofilial IN (:branchNumber)) AND ( (sip.idsituacaopedidovenda NOT IN (1, 2, 5, 6, 7, 8, 9) AND sment.idsituacaoentrega <> 3 AND smm.idsituacaomontagem <> 2) OR (sip.idsituacaopedidovenda NOT IN (1, 2, 5, 6, 7, 8, 9) AND sment.idsituacaoentrega <> 3 AND smm.idsituacaomontagem is NULL) ) ) AS pedido ORDER BY pedido.datainclusao");
        List<SalesOrder> sales = MANAGER.createNativeQuery(jpql, SalesOrder.class).setParameter("branchNumber", branchNumber).getResultList();
        return sales;
    }

    @Override
    public List<Product> listSalesProducts(Integer branchNumber) {
        this.jpql = ("SELECT\n" +
"prd.iditembase,\n" +
"prd.idpedidovenda,\n" +
"prd.idproduto, \n" +
"prd.desc_prod, \n" +
"prd.quantidade, \n" +
"prd.situacao_item, \n" +
"prd.entregar,  \n" +
"prd.idmapacarga, \n" +
"prd.situacaomapacarga, \n" +
"prd.idmapaentrega, \n" +
"prd.previsaofaturamento, \n" +
"prd.situacao_entrega, \n" +
"prd.montagem, \n" +
"prd.idmapamontagem, \n" +
"prd.previsaomontagem, \n" +
"prd.situacaomontagem, \n" +
"prd.filialreserva, \n" +
"prd.idsituacaoentrega, \n" +
"prd.idsituacaomontagem                     \n" +
"	FROM (SELECT DISTINCT CAST(CAST(i.idfilial AS text)||CAST(i.iditembase AS text) AS numeric(24,0)) as iditembase,\n" +
"    i.idpedidovenda,\n" +
"	f.numerofilial,\n" +
"	f.fantasia,\n" +
"	pv.nome,\n" +
"    city.cidade,\n" +
"	ende.bairro,\n" +
"	i.datamovimento,\n" +
"	i.idproduto,\n" +
"	p.descricao||' '||gx.descricao||' '||gy.descricao AS desc_prod,\n" +
"	i.quantidade,\n" +
"	sip.descricao AS situacao_item,\n" +
"	CASE WHEN i.entregar = 1 THEN 'SIM' ELSE 'NÃO' END AS entregar, \n" +
"	i.idmapacarga,\n" +
"	sma.descricao AS situacaomapacarga,\n" +
"	men.idmapaentrega,\n" +
"	CASE WHEN i.previsaoentrega IS NULL THEN pv.previsaofaturamento ELSE i.previsaoentrega END AS previsaofaturamento,\n" +
"	CASE WHEN i.entregar = 1 AND sment.idsituacaoentrega = 2 THEN 'Entrega Pendente' WHEN i.entregar <> 1 AND sment.idsituacaoentrega IS NULL THEN 'Não Há Entrega' WHEN i.entregar = 1 AND sment.idsituacaoentrega IS NULL THEN 'Entrega Pendente' WHEN i.entregar = 1 AND sment.idsituacaoentrega IN (1) AND sma.idsituacaomapacarga <> 3 THEN 'Saiu Para Entrega' WHEN i.entregar = 1 AND sment.idsituacaoentrega IN (4,5) THEN 'Não Entregue' ELSE ('Entregue a ' || ((current_date - i.datamovimento)+1) || ' dias') END AS situacao_entrega,\n" +
"	CASE WHEN i.montagem = 1 THEN 'SIM' ELSE 'NÃO' END AS montagem,\n" +
"	mmi.idmapamontagem,\n" +
"	CASE WHEN i.montagem = 1 THEN i.previsaomontagem ELSE NULL END AS  previsaomontagem,\n" +
"	CASE WHEN i.montagem = 1 AND mmi.idmapamontagem IS NULL THEN 'Montagem Pendente' WHEN i.montagem = 1 AND mmi.idmapamontagem IS NOT NULL THEN 'Montador em Rota' WHEN smm.idsituacaomontagem = 2 AND mmi.idmapamontagem IS NOT NULL THEN 'Montado' ELSE 'Não Há Montagem' END AS situacaomontagem,\n" +
"	COALESCE(fir.numerofilial,f.numerofilial) AS filialreserva,\n" +
"	spe.descricao AS situacaopedidoloja,\n" +
"	sment.descricao as idsituacaoentrega,\n" +
"	smm.descricao as idsituacaomontagem  FROM rst.itembase i INNER JOIN rst.pedidovenda pv ON (i.idfilial = pv.idfilial AND i.idpedidovenda = pv.idpedidovenda)\n" +
"							LEFT JOIN rst.mapacarga ma ON (ma.idfilial = i.idfilialafaturar AND ma.idmapacarga = i.idmapacarga)\n" +
"                            LEFT JOIN sis.situacaomapacarga sma ON (sma.idsituacaomapacarga = ma.idsituacaomapacarga)\n" +
"                            INNER JOIN glb.pessoa pe ON (pe.idcnpj_cpf = pv.idcnpj_cpf)\n" +
"                            LEFT JOIN sis.situacaopedidovenda sip ON (sip.idsituacaopedidovenda = i.idsituacaopedidovenda)\n" +
"                            INNER JOIN glb.produto p ON (p.idproduto = i.idproduto)\n" +
"                            INNER JOIN glb.gradex gx ON (gx.idgradex = i.idgradex)\n" +
"                            INNER JOIN glb.gradey gy ON (gy.idgradey = i.idgradey)\n" +
"                            LEFT JOIN glb.localsaldo lc ON (lc.idlocalsaldo = i.idlocalsaldo)\n" +
"                            LEFT JOIN rst.itembase iff ON (iff.idfilialorigem = i.idfilial AND iff.iditembaseorigem = i.iditembase)\n" +
"                            LEFT JOIN rst.nota n ON (n.idfilial = CASE WHEN i.idfilial <> i.idfilialafaturar THEN iff.idfilial ELSE i.idfilial END AND n.idregistronota = CASE WHEN i.idfilial <> i.idfilialafaturar THEN iff.idregistronota ELSE i.idregistronota END)                      \n" +
"                            LEFT JOIN rst.mapaentreganota men ON (men.idfilialnota = n.idfilial AND men.idregistronota = n.idregistronota)\n" +
"                            LEFT JOIN sis.situacaomapaentrega sment ON (sment.idsituacaoentrega = men.idsituacaoentrega)\n" +
"                            LEFT JOIN rst.mapamontagemitem mmi ON (mmi.idfilialitembase = CASE WHEN i.idfilial <> i.idfilialafaturar THEN iff.idfilial ELSE i.idfilial END AND mmi.iditembase = CASE WHEN i.idfilial <> i.idfilialafaturar THEN iff.iditembase ELSE i.iditembase END)\n" +
"							LEFT JOIN sis.situacaomapamontagem smm ON (smm.idsituacaomontagem = mmi.idsituacaomontagem)\n" +
"                            LEFT JOIN sis.tipomontagem tim ON (tim.idtipomontagem = mmi.idtipomontagem)                                                            \n" +
"                            LEFT JOIN glb.filial f ON (f.idfilial = i.idfilial)\n" +
"                            LEFT JOIN glb.filial fat ON (fat.idfilial = i.idfilialafaturar)\n" +
"                            LEFT JOIN rst.pedidoloja pel ON (i.idfilial = pel.idfilialdetalhe AND i.idpedidovenda = pel.idpedvendafldetalhe AND i.idproduto = pel.idproduto AND i.idgradex = pel.idgradex AND i.idgradey = pel.idgradey)\n" +
"							LEFT JOIN sis.situacaopedidoloja spe ON (spe.idsituacaopedidoloja = pel.idsituacaopedidoloja)\n" +
"                            LEFT JOIN glb.filial AS fir ON (fir.idfilial = pel.idfilial)\n" +
"                            INNER JOIN glb.endereco AS ende ON (pv.idcnpj_cpf = ende.idcnpj_cpf)\n" +
"                            INNER JOIN glb.cidade AS city ON (ende.idcidade = city.idcidade)\n" +
"                            INNER JOIN glb.produtograde pg ON (pg.idproduto = p.idproduto)\n" +
"                            INNER JOIN sis.tipoproduto AS tp ON (tp.idtipoproduto = pg.idtipoproduto)\n" +
"                            \n" +
"							WHERE ( f.numerofilial IN (:branchNumber) AND sip.idsituacaopedidovenda NOT IN (1, 2, 5, 6, 7, 8, 9) AND tp.idtipoproduto = 5 AND ( (sment.idsituacaoentrega <> 3 AND smm.idsituacaomontagem <> 2) OR (sment.idsituacaoentrega <> 3 AND smm.idsituacaomontagem is NULL) OR (sment.idsituacaoentrega is NULL AND smm.idsituacaomontagem is NULL) ) ) ) AS prd");
        List<Product> sales = MANAGER.createNativeQuery(jpql, Product.class).setParameter("branchNumber", branchNumber).getResultList();
        return sales;
    }

}
