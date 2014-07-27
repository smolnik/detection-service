package net.adamsmolnik.boundary.detection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import net.adamsmolnik.control.detection.Detector;
import net.adamsmolnik.model.detection.DetectionRequest;
import net.adamsmolnik.model.detection.DetectionResponse;

/**
 * @author ASmolnik
 *
 */
@Path("/ds")
@RequestScoped
public class DetectionService {

    @Inject
    private Detector detector;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("detect")
    public String detect(@FormParam("objectKey") String objectKey) {
        return detector.detect(objectKey).toString();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("detect")
    public DetectionResponse detect(DetectionRequest detectionRequest) {
        net.adamsmolnik.entity.MediaType mt = detector.detect(detectionRequest.objectKey);
        return new DetectionResponse(mt.getType(), mt.getSubType());
    }

}
