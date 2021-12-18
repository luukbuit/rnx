package nl.luukbuit.rnx;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IntegrationTest {

    @Mock
    BlahDao blahDaoMock;

    @Mock
    BlahSender blahSenderMock;

    BlahService blahService = new BlahService();

    SuperBlahService superBlahService = new SuperBlahService();

    @Captor
    ArgumentCaptor<List<String>> listOfStringsArgumentCaptor;

    @BeforeEach
    void beforeEach() {
        initMocks();
        initDependencies();
    }

    private void initMocks() {
        MockitoAnnotations.openMocks(this); // alternatief is een class annotation: @ExtendWith(MockitoExtension.class
        when(blahDaoMock.haalIetsOp()).thenReturn(Lists.newArrayList("test123", "test456"));
    }

    private void initDependencies() {
        // geen @InjectMocks gebruiken, wordt afgeraden door het core team van mockito (https://github.com/mockito/mockito/issues/1518)
        // weld-junit? https://github.com/weld/weld-junit/blob/master/junit5/README.md (kun je ook interceptors etc. meenemen, is wel iets complexer)
        blahService.setBlahDao(blahDaoMock);
        superBlahService.setBlahService(blahService);
        superBlahService.setBlahSender(blahSenderMock);
    }

    @Test
    void blah() {
        superBlahService.doeDitOfDat();
        verify(blahSenderMock).send(listOfStringsArgumentCaptor.capture());
        List<String> sendedList = listOfStringsArgumentCaptor.getValue();
        assertThat(sendedList).hasSize(2);
        assertThat(sendedList).anyMatch(el -> el.equals("test123"));
    }

}
