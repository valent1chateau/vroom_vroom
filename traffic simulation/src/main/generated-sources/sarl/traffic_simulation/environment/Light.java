package traffic_simulation.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;

@SarlSpecification("0.11")
@SarlElementType(11)
@SuppressWarnings("all")
public interface Light {
  public abstract void turnRed();
  
  public abstract void turnGreen();
  
  public abstract void switchLight(final boolean state);
}
