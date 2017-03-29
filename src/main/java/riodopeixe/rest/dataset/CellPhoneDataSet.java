package riodopeixe.rest.dataset;

import java.util.List;
import riodopeixe.rest.model.CellPhone;
import riodopeixe.rest.model.ProductRegistration;

/**
 *
 * @author vagner
 */
public interface CellPhoneDataSet {
    
    public List<CellPhone> listCellPhones();
    public ProductRegistration getCellPhonebyRef(int cod, int color, int volt);
    public CellPhone getCellPhoneById(int id);
    public void cellPhonePersist(CellPhone cellPhone);
    public void cellPhoneRemove(int cod);
    public void cellPhoneUpdate(CellPhone cellPhone);    
    
}
