package net.adamsmolnik.setup.detection;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import net.adamsmolnik.boundary.detection.DetectionActivityImpl;
import net.adamsmolnik.control.detection.Detector;
import net.adamsmolnik.setup.ActivityLauncher;

/**
 * @author ASmolnik
 *
 */
@WebListener("detectionActivitySetup")
public class WebSetup implements ServletContextListener {

    @Inject
    private Detector detector;

    @Inject
    private ActivityLauncher al;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        al.register(new DetectionActivityImpl(detector));
        al.launch();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        al.shutdown();
    }

}
