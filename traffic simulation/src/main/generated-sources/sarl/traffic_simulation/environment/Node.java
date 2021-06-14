package traffic_simulation.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Objects;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Node {
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private int id;
  
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private Point2D coord;
  
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private ArrayList<Node> neighboors;
  
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private String statut;
  
  public Node(final int i, final Double x, final Double y) {
    this.id = i;
    Point2D.Double _double = new Point2D.Double();
    this.coord = _double;
    this.coord.setLocation(((x) == null ? 0 : (x).doubleValue()), ((y) == null ? 0 : (y).doubleValue()));
    ArrayList<Node> _arrayList = new ArrayList<Node>();
    this.neighboors = _arrayList;
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
    Node other = (Node) obj;
    if (other.id != this.id)
      return false;
    if (!Objects.equals(this.statut, other.statut))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Integer.hashCode(this.id);
    result = prime * result + Objects.hashCode(this.statut);
    return result;
  }
  
  @Pure
  public int getId() {
    return this.id;
  }
  
  public void setId(final int id) {
    this.id = id;
  }
  
  @Pure
  public Point2D getCoord() {
    return this.coord;
  }
  
  public void setCoord(final Point2D coord) {
    this.coord = coord;
  }
  
  @Pure
  public ArrayList<Node> getNeighboors() {
    return this.neighboors;
  }
  
  public void setNeighboors(final ArrayList<Node> neighboors) {
    this.neighboors = neighboors;
  }
  
  @Pure
  public String getStatut() {
    return this.statut;
  }
  
  public void setStatut(final String statut) {
    this.statut = statut;
  }
}
