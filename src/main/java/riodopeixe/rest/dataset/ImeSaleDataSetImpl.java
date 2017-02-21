package riodopeixe.rest.dataset;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import riodopeixe.rest.model.ImeiSale;

/**
 *
 * @author vagner
 */
public class ImeSaleDataSetImpl implements ImeiSaleDataSet {
    
    private final EntityManager MANAGER;

    public ImeSaleDataSetImpl() {
        this.MANAGER = Persistence.createEntityManagerFactory("PostgresDS").createEntityManager();
    }    

    @Override
    public ImeiSale imeiSaleCheck(String imei) {
        String jpql = ("SELECT \n" +
                        "fl.numerofilial,\n" +
                        "pv.idpedidovenda,\n" +
                        "pv.databaixa,\n" +
                        "pv.nome AS cliente,\n" +
                        "ns.numeroserie AS imei\n" +
                        "FROM rst.numeroserie ns\n" +
                        "INNER JOIN rst.itembase ib ON (ns.iditembase = ib.iditembase AND ns.idfilial = ib.idfilial)\n" +
                        "INNER JOIN rst.pedidovenda pv ON (pv.idpedidovenda = ib.idpedidovenda AND pv.idfilial = ib.idfilial)\n" +
                        "INNER JOIN glb.filial fl ON (pv.idfilial = fl.idfilial)\n" +
                        "WHERE ns.numeroserie iLIKE :imei");
        ImeiSale imeiSale = (ImeiSale) MANAGER.createNativeQuery(jpql, ImeiSale.class).setParameter("imei", imei).getSingleResult();
        return imeiSale;
    }
    
}
