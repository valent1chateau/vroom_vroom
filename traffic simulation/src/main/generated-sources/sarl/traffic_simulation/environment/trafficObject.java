package traffic_simulation.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.awt.geom.Point2D;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class trafficObject {
  @Accessors
  private Point2D coord;
  
  public trafficObject(final int xParam, final int yParam) {
    Point2D.Double _double = new Point2D.Double(xParam, yParam);
    this.coord = _double;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }
  
  @Pure
  public Point2D getCoord() {
    return this.coord;
  }
  
  public void setCoord(final Point2D coord) {
    this.coord = coord;
  }
}
