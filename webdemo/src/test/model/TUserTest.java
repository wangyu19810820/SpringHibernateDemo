package model;

import config.RootConfig;
import dao.TUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import service.TUserService;

import javax.persistence.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = config.RootConfig.class)
//@ContextConfiguration(locations = {"classpath:spring.xml"})
public class TUserTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TUserService tUserService;

    @Test
    public void test() {
        String driver = "org.h2.Driver";
        String url = "jdbc:h2:file:~/sample.db";
        String user = "sa";
        String password = "";
        try {
            org.h2.tools.Server.createTcpServer().start();
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, user, password);
//            boolean b = conn.createStatement().execute("CREATE TABLE `users` (" +
//                    "`username`  varchar(10) ," +
//                    "`password`  varchar(10) ," +
//                    "`enabled`  int " +
//                    ")");
            boolean b1 = conn.createStatement().execute("insert into TUser (id, name) values (null, '1111')");
            System.out.println(b1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDatasource() throws Exception {
        Connection conn = dataSource.getConnection();
        ResultSet rs = conn.createStatement().executeQuery("SELECT count(*) FROM TUser");
        rs.next();
        System.out.println(rs.getInt(1));
    }

    @Test
    @Transactional
    public void testAdd() {
        try {
            TUser user = new TUser();
            user.setName("user11");
            entityManager.persist(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void testQuery() {
        Query query = entityManager.createQuery("from TUser");
        List list = query.getResultList();
        list.forEach(System.out::println);

    }

    @Test
    public void testDaoAdd() {
        TUser tUser = new TUser();
        tUser.setName("user44");
        tUserService.add(tUser);
    }
}
