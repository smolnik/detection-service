package net.adamsmolnik.control.detection;

import java.io.IOException;
import java.io.InputStream;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import net.adamsmolnik.entity.Entity;
import net.adamsmolnik.entity.EntityReference;
import net.adamsmolnik.entity.MediaType;
import net.adamsmolnik.exceptions.ServiceException;
import net.adamsmolnik.provider.EntityProvider;
import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;

@Dependent
public class Detector {

    @Inject
    private EntityProvider entityProvider;

    public MediaType detect(String objectKey) {
        EntityReference entityReference = new EntityReference(objectKey);
        Entity entity = entityProvider.getEntity(entityReference);
        try (InputStream is = entity.getInputStream()) {
            DefaultDetector dd = new DefaultDetector();
            org.apache.tika.mime.MediaType mt = dd.detect(TikaInputStream.get(is), new Metadata());
            MediaType mediaType = new MediaType(mt.getType(), mt.getSubtype());
            entityProvider.setNewMetadata(entityReference, "mediaType", mediaType.toString());
            return mediaType;
        } catch (IOException e) {
            throw new ServiceException(e);
        }
    }

}
