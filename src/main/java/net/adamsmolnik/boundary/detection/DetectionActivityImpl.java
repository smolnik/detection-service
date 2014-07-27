package net.adamsmolnik.boundary.detection;

import net.adamsmolnik.control.detection.Detector;
import net.adamsmolnik.entity.MediaType;
import net.adamsmolnik.model.detection.DetectionRequest;
import net.adamsmolnik.model.detection.DetectionResponse;

/**
 * @author ASmolnik
 *
 */
public class DetectionActivityImpl implements DetectionActivity {

    private final Detector detector;

    public DetectionActivityImpl(Detector detector) {
        this.detector = detector;
    }

    @Override
    public DetectionResponse detect(DetectionRequest detectionRequest) {
        MediaType mt = detector.detect(detectionRequest.objectKey);
        return new DetectionResponse(mt.getType(), mt.getSubType());
    }

}
