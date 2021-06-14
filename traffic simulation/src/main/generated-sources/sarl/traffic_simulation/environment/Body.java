package traffic_simulation.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import java.util.UUID;

@FunctionalInterface
@SarlSpecification("0.11")
@SarlElementType(11)
@SuppressWarnings("all")
public interface Body {
  public abstract UUID getID();
}
