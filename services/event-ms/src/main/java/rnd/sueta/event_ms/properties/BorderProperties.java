package rnd.sueta.event_ms.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import rnd.sueta.event_ms.model.entity.Point;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "app.city-coordinates")
public class BorderProperties {
    private Point bottomLeftCorner;
    private Point topLeftCorner;
    private Point bottomRightCorner;
    private Point topRightCorner;
}
