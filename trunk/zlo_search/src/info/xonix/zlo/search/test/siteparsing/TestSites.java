package info.xonix.zlo.search.test.siteparsing;

import info.xonix.zlo.search.dao.DAOException;
import info.xonix.zlo.search.dao.Site;
import info.xonix.zlo.search.model.ZloMessage;
import junit.framework.Assert;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Author: Vovan
 * Date: 22.03.2008
 * Time: 20:17:05
 */
public class TestSites {

    Site velo;
    Site dev;
    Site zlo;

    @Before
    public void setUp() {
        velo = Site.forName("velo");
        velo.setDB_VIA_CONTAINER(false);

        dev = Site.forName("dev");
        dev.setDB_VIA_CONTAINER(false);

        zlo = Site.forName("zlo");
        zlo.setDB_VIA_CONTAINER(false);
    }

    @Test
    public void testVelo() {
        try {
            int lmn = velo.getLastMessageNumber();
            System.out.println("lmn: " + lmn);

            ZloMessage m = velo.getMessage(19490);

            System.out.println(m);

            Assert.assertEquals(19490, m.getNum());
            Assert.assertEquals("sim", m.getNick());
            Assert.assertTrue(m.isReg());
            Assert.assertEquals("gw.zunet.ru", m.getHost());
            Assert.assertTrue(StringUtils.isNotEmpty(m.getBody()));

            m = velo.getMessage(19580);

            System.out.println(m);

            Assert.assertEquals(19580, m.getNum());
            Assert.assertEquals("bull", m.getNick());
            Assert.assertTrue(!m.isReg());
            Assert.assertEquals("ppp85-140-32-253.pppoe.mtu-net.ru", m.getHost());
            Assert.assertTrue(StringUtils.isEmpty(m.getBody()));

            m = velo.getMessage(18869);

            System.out.println(m);

            Assert.assertTrue(StringUtils.isEmpty(m.getBody()));
            Assert.assertTrue(m.isReg());

            System.out.println(velo.getMessage(33));


        } catch (DAOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDev() {
        try {
            int lmn = dev.getLastMessageNumber();
            System.out.println(lmn);

            ZloMessage m = dev.getMessage(9404);

            Assert.assertEquals(9404, m.getNum());
            Assert.assertEquals("Berk", m.getNick());
            Assert.assertEquals("post.mirantis.ru", m.getHost());
            Assert.assertTrue(m.isReg());
            Assert.assertEquals("Unix/Linux", m.getTopic());
            Assert.assertTrue(StringUtils.isNotEmpty(m.getBody()));

            System.out.println(m);

            m = dev.getMessage(9374);

            Assert.assertEquals(9374, m.getNum());
            Assert.assertEquals("arfix.", m.getNick());
            Assert.assertTrue(StringUtils.isEmpty(m.getBody()));
            Assert.assertTrue(!m.isReg());

            System.out.println(m);

            m = dev.getMessage(10153);

            Assert.assertEquals(10153, m.getNum());
            Assert.assertEquals("�����", m.getNick());
            Assert.assertEquals("ppp91-77-164-91.pppoe.mtu-net.ru", m.getHost());
            Assert.assertTrue(StringUtils.isNotEmpty(m.getBody()));
            Assert.assertTrue(!m.isReg());

            System.out.println(m);

        } catch (DAOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testZlo() {
        try {
            int lmn = zlo.getLastMessageNumber();
            System.out.println(lmn);

            ZloMessage m =  zlo.getMessage(4093778);

            Assert.assertEquals("QDiesel", m.getNick());
            Assert.assertEquals("nokia.7ka.mipt.ru", m.getHost());
            Assert.assertTrue(m.isReg());
            Assert.assertTrue(StringUtils.isNotEmpty(m.getBody()));

            System.out.println(m);

            m =  zlo.getMessage(4093788);

            Assert.assertEquals("Loki", m.getNick());
            Assert.assertEquals("loki.3ka.mipt.ru", m.getHost());
            Assert.assertTrue(m.isReg());
            Assert.assertTrue(StringUtils.isEmpty(m.getBody()));

            System.out.println(m);

            m =  zlo.getMessage(405573);

            Assert.assertEquals("Demoney", m.getNick());
            Assert.assertEquals("morgue.7ka.mipt.ru", m.getHost());
            Assert.assertTrue(!m.isReg());
            Assert.assertTrue(StringUtils.isNotEmpty(m.getBody()));

            System.out.println(m);

        } catch (DAOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}