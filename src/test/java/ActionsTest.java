import com.common.dao.DaoService;
import com.core.*;
import com.transaction.service.TransactionService;
import configuration.TestConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class ActionsTest extends TestConfiguration {

    @Autowired
    DaoService daoService;

    @Autowired
    TransactionDatabaseService transactionDatabaseService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    Trade bitcoin;

    List list = new ArrayList();

    @Before
    public void before() {
        for (int i = 0; i < 50000; i++) {
            list.add(i);
        }
    }

    @Test
    public void bidActionTest() throws Exception {

    }

    @Test
    public void bidAction_DatabaseTest() throws Exception {

        bitcoin.sync(new OrderVO());

/*        for (int i = 0; i < 100; i++) {
            OrderVO vo = this.getTransReqVO("5", "1000");
            vo.setPrice(Calc.plus("1000", String.valueOf(i * 100)));
            for (int j = 0; j < 1; j++) {
                bitcoin.ask(vo);
            }
        }

        System.out.println(bitcoin);*/
    }

    @Test
    public void askAction_DatabaseTest() throws Exception {
    }

    @Test
    public void btcFromOrderUptoCancel() throws Exception {

        OrderVO vo = getTransReqVO("1", "1000");
        bitcoin.ask(vo);

        String orderId = vo.getOrderId();

        vo = getTransReqVO("0.5", "1000");
        vo.setEmail("lyw0624@gmail.com");
        bitcoin.bid(vo);

        vo.setOrderId(orderId);
        vo.setTranType("ask");
        vo.setEmail("dldyddn0624@gmail.com");
        bitcoin.cancelAsk(vo);

    }

    private OrderVO getTransReqVO(String q, String p) {
        OrderVO vo = new OrderVO();

        String qnt = q;
        String price = p;
        String email = "lyw0624@gmail.com";

        vo.setEmail(email);
        vo.setQuantity(qnt);
        vo.setPrice(price);
        return vo;
    }


}
