package traffic_simulation.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import javafx.scene.shape.Polyline;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.environment.Node;
import traffic_simulation.environment.Vehicle;
import traffic_simulation.environment.trafficLight;

@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Edge extends Polyline {
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private int id_;
  
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private Node Nin;
  
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private Node Nout;
  
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private int nbVoies;
  
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private double weight;
  
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private ArrayList<Vehicle> bodies;
  
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private boolean haveLight = false;
  
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private trafficLight light;
  
  public Edge(final int i, final Node in_, final Node out_) {
    super();
    this.id_ = i;
    this.Nin = in_;
    this.Nout = out_;
    ArrayList<Vehicle> _arrayList = new ArrayList<Vehicle>();
    this.bodies = _arrayList;
  }
  
  public boolean addBody(final Vehicle v) {
    return this.bodies.add(v);
  }
  
  public boolean removeBody(final Vehicle v) {
    return this.bodies.remove(v);
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
    Edge other = (Edge) obj;
    if (other.id_ != this.id_)
      return false;
    if (other.nbVoies != this.nbVoies)
      return false;
    if (Double.doubleToLongBits(other.weight) != Double.doubleToLongBits(this.weight))
      return false;
    if (other.haveLight != this.haveLight)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Integer.hashCode(this.id_);
    result = prime * result + Integer.hashCode(this.nbVoies);
    result = prime * result + Double.hashCode(this.weight);
    result = prime * result + Boolean.hashCode(this.haveLight);
    return result;
  }
  
  @Pure
  public int getId_() {
    return this.id_;
  }
  
  public void setId_(final int id_) {
    this.id_ = id_;
  }
  
  @Pure
  public Node getNin() {
    return this.Nin;
  }
  
  public void setNin(final Node Nin) {
    this.Nin = Nin;
  }
  
  @Pure
  public Node getNout() {
    return this.Nout;
  }
  
  public void setNout(final Node Nout) {
    this.Nout = Nout;
  }
  
  @Pure
  public int getNbVoies() {
    return this.nbVoies;
  }
  
  public void setNbVoies(final int nbVoies) {
    this.nbVoies = nbVoies;
  }
  
  @Pure
  public double getWeight() {
    return this.weight;
  }
  
  public void setWeight(final double weight) {
    this.weight = weight;
  }
  
  @Pure
  public ArrayList<Vehicle> getBodies() {
    return this.bodies;
  }
  
  public void setBodies(final ArrayList<Vehicle> bodies) {
    this.bodies = bodies;
  }
  
  @Pure
  public boolean isHaveLight() {
    return this.haveLight;
  }
  
  public void setHaveLight(final boolean haveLight) {
    this.haveLight = haveLight;
  }
  
  @Pure
  public trafficLight getLight() {
    return this.light;
  }
  
  public void setLight(final trafficLight light) {
    this.light = light;
  }
}
