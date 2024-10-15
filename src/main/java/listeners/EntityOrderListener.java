package listeners;

import javax.persistence.PrePersist;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class EntityOrderListener
 *
 */
@WebListener
public class EntityOrderListener implements ServletContextListener {

    /**
     * Default constructor.
     */
    public EntityOrderListener() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  {
         // TODO Auto-generated method stub
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  {
         // TODO Auto-generated method stub
    }
    @PrePersist
    public void prePersist(Object entity) {
        // ここで優先順位を設定
    }

}
