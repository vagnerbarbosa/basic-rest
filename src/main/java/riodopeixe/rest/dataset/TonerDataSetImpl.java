package riodopeixe.rest.dataset;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import riodopeixe.rest.model.Toner;

/**
 *
 * @author vagner
 */
public class TonerDataSetImpl implements TonerDataSet {
    
    private final EntityManager MANAGER;
    private final TransactionManager TRANSACTION;

    public TonerDataSetImpl() {
    this.MANAGER = Persistence.createEntityManagerFactory("SisToner").createEntityManager();
    this.TRANSACTION = com.arjuna.ats.jta.TransactionManager.transactionManager();
    }    

    @Override
    public List<Toner> listarToners() {
    List<Toner> toners = null;
        try {        
            TRANSACTION.begin();
            Query query = MANAGER.createQuery("FROM Toner u");
            toners = query.getResultList();
            TRANSACTION.commit();            
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toners;
    }   
    
    @Override
    public Toner getTonerPorRef(int ref) {
    Toner toner = null;    
        try {   
            TRANSACTION.begin();
            MANAGER.flush();
            MANAGER.clear();
            toner = MANAGER.find(Toner.class, ref);
            TRANSACTION.commit();            
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toner;
    }    

    @Override
    public void pesistirToner(Toner toner) {
        try {
            TRANSACTION.begin();
            toner.setDataInclusao(new Date(System.currentTimeMillis()));
            MANAGER.persist(toner);
            MANAGER.flush();
            MANAGER.clear();
            TRANSACTION.commit();            
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removerToner(Integer ref) {
        Toner toner = this.getTonerPorRef(ref);
        try {
            TRANSACTION.begin();
            MANAGER.remove(toner);
            MANAGER.flush();
            MANAGER.clear();        
            TRANSACTION.commit();            
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void alterarToner(Toner toner) {
        try { 
            TRANSACTION.begin();
            MANAGER.merge(toner);
            MANAGER.flush();
            MANAGER.clear();        
            TRANSACTION.commit();            
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<Toner> listarTonersPorDataEntrada(Date dataInicial, Date dataFinal) {
    List<Toner> toners = null;
        try {        
            TRANSACTION.begin();
            Query query = MANAGER.createQuery("SELECT DISTINCT t FROM Toner AS t INNER JOIN t.servicos AS s INNER JOIN s.tiposServico AS ts WHERE s.dataEntrada BETWEEN :datainicial AND :datafinal").setParameter("datainicial", dataInicial, TemporalType.TIMESTAMP).setParameter("datafinal", dataFinal, TemporalType.TIMESTAMP);
            toners = query.getResultList();
            TRANSACTION.commit();            
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toners;
    }     

    @Override
    public List<Toner> listarTonersPorDataSaida(Date dataInicial, Date dataFinal) {
    List<Toner> toners = null;
        try {        
            TRANSACTION.begin();
            Query query = MANAGER.createQuery("SELECT DISTINCT t FROM Toner AS t INNER JOIN t.servicos AS s INNER JOIN s.tiposServico AS ts WHERE s.dataSaida BETWEEN :datainicial AND :datafinal").setParameter("datainicial", dataInicial, TemporalType.TIMESTAMP).setParameter("datafinal", dataFinal, TemporalType.TIMESTAMP);
            toners = query.getResultList();
            TRANSACTION.commit();            
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(TonerDataSetImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toners;

    }
    
}
