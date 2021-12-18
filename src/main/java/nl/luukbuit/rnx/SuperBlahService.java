package nl.luukbuit.rnx;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class SuperBlahService {

    @Inject
    BlahService blahService;

    @Inject
    BlahSender blahSender;

    public void doeDitOfDat() {
        blahSender.send(blahService.haalIetsOp());
    }

    public void setBlahService(BlahService blahService) {
        this.blahService = blahService;
    }

    public void setBlahSender(BlahSender blahSender) {
        this.blahSender = blahSender;
    }
}
