package riodopeixe.rest.dataset;

import java.util.Date;
import java.util.List;
import riodopeixe.rest.model.Toner;

/**
 *
 * @author vagner
 */
public interface TonerDataSet {
    
    public List<Toner> listarToners();
    public Toner getTonerPorRef(int ref);
    public void pesistirToner(Toner toner);
    public void removerToner(Integer ref);
    public void alterarToner(Toner toner);
    public List<Toner> listarTonersPorDataEntrada(Date dataInicial, Date dataFinal);
    public List<Toner> listarTonersPorDataSaida(Date dataInicial, Date dataFinal);
    
}
