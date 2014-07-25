package net.adamsmolnik.setup.detection;

import javax.inject.Singleton;
import net.adamsmolnik.setup.ServiceNameResolver;

/**
 * @author ASmolnik
 *
 */
@Singleton
public class DetectionServiceNameResolver implements ServiceNameResolver {

    @Override
    public String getServiceName() {
        return "detection-service";
    }

}
