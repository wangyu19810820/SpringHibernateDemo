package listener;

import org.h2.tools.Server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

/**
 * Created by admin on 2017/6/22.
 */
public class H2DBServerStartListener implements ServletContextListener {

    private Server server;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("H2 database 正在启动");
        try {
            server = Server.createTcpServer().start();
            System.out.println("h2数据库启动成功");
        } catch (SQLException e) {
            System.out.println("启动h2数据库出错：" + e.toString());
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        if (this.server != null) {
            this.server.stop();
            this.server = null;
        }
    }
}
