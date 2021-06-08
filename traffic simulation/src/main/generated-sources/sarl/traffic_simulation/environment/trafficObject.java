package traffic_simulation.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.awt.geom.Point2D;
import java.util.Objects;
import java.util.UUID;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class trafficObject {
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private UUID id;
  
  @Accessors
  private Point2D coord;
  
  public trafficObject(final double xParam, final double yParam) {
    Point2D.Double _double = new Point2D.Double(xParam, yParam);
    this.coord = _double;
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
    trafficObject other = (trafficObject) obj;
    if (!Objects.equals(this.id, other.id))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Objects.hashCode(this.id);
    return result;
  }
  
  @Pure
  public UUID getId() {
    return this.id;
  }
  
  public void setId(final UUID id) {
    this.id = id;
  }
  
  @Pure
  public Point2D getCoord() {
    return this.coord;
  }
  
  public void setCoord(final Point2D coord) {
    this.coord = coord;
  }
}
