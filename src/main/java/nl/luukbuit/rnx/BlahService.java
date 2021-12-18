package nl.luukbuit.rnx;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class BlahService {

    @Inject
    BlahDao blahDao;

    public List<String> haalIetsOp() {
        return blahDao.haalIetsOp();
    }

    public void setBlahDao(BlahDao blahDao) {
        this.blahDao = blahDao;
    }
}
