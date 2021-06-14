package traffic_simulation.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;
import traffic_simulation.environment.Vehicle;

@SarlSpecification("0.11")
@SarlElementType(15)
@SuppressWarnings("all")
public class Perceptions extends Event {
  public ArrayList<Vehicle> p_value;
  
  public double[] state;
  
  public Point2D loc;
  
  public double dim;
  
  public double distwithLight;
  
  public boolean isRedView;
  
  public Perceptions(final ArrayList<Vehicle> p, final double vtm, final double vt, final double am, final Point2D co, final double d, final boolean isRed, final double distLight) {
    this.p_value = p;
    this.state = new double[] { vtm, vt, am };
    this.loc = co;
    this.dim = d;
    this.isRedView = isRed;
    this.distwithLight = distLight;
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
    Perceptions other = (Perceptions) obj;
    if (Double.doubleToLongBits(other.dim) != Double.doubleToLongBits(this.dim))
      return false;
    if (Double.doubleToLongBits(other.distwithLight) != Double.doubleToLongBits(this.distwithLight))
      return false;
    if (other.isRedView != this.isRedView)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Double.hashCode(this.dim);
    result = prime * result + Double.hashCode(this.distwithLight);
    result = prime * result + Boolean.hashCode(this.isRedView);
    return result;
  }
  
  /**
   * Returns a String representation of the Perceptions event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("p_value", this.p_value);
    builder.add("state", this.state);
    builder.add("loc", this.loc);
    builder.add("dim", this.dim);
    builder.add("distwithLight", this.distwithLight);
    builder.add("isRedView", this.isRedView);
  }
  
  @SyntheticMember
  private static final long serialVersionUID = 1468982264L;
}
