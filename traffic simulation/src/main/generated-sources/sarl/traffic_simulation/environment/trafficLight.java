package traffic_simulation.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.environment.Light;
import traffic_simulation.environment.trafficObject;

@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class trafficLight extends trafficObject implements Light {
  @Accessors
  private boolean isGreen = false;
  
  public trafficLight(final double xParam, final double yParam, final boolean state) {
    super(xParam, yParam);
    this.isGreen = state;
  }
  
  public void turnRed() {
    this.switchLight(false);
  }
  
  public void turnGreen() {
    this.switchLight(true);
  }
  
  public void switchLight(final boolean state) {
    this.isGreen = state;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    trafficLight other = (trafficLight) obj;
    if (other.isGreen != this.isGreen)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Boolean.hashCode(this.isGreen);
    return result;
  }
  
  @Pure
  public boolean isGreen() {
    return this.isGreen;
  }
  
  public void setIsGreen(final boolean isGreen) {
    this.isGreen = isGreen;
  }
}
