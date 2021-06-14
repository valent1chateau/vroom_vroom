package traffic_simulation.environment;

import com.google.common.base.Objects;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.environment.Edge;
import traffic_simulation.environment.Graph;
import traffic_simulation.environment.Node;
import traffic_simulation.environment.Vehicle;
import traffic_simulation.util.Tools;

@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Perception {
  @Accessors
  private Vehicle vehicle;
  
  private double dist_vision;
  
  private Tools tool = new Tools();
  
  private Graph G;
  
  @Accessors
  private ArrayList<Vehicle> res;
  
  private boolean LightIsView;
  
  @Accessors
  private double distWithLight = (-1);
  
  public Perception(final Vehicle v, final double dv, final Graph g) {
    this.vehicle = v;
    this.dist_vision = dv;
    this.G = g;
    ArrayList<Vehicle> _arrayList = new ArrayList<Vehicle>();
    this.res = _arrayList;
    this.LightIsView = false;
  }
  
  @Pure
  public boolean getLightIsView() {
    return this.LightIsView;
  }
  
  public Object perceptLight() {
    Object _xblockexpression = null;
    {
      Edge e = this.vehicle.getEdge();
      Object _xifexpression = null;
      boolean _isHaveLight = e.isHaveLight();
      if (_isHaveLight) {
        Object _xifexpression_1 = null;
        boolean _isGreen = e.getLight().isGreen();
        if ((_isGreen == false)) {
          Object _xblockexpression_1 = null;
          {
            this.distWithLight = this.tool.distance(this.vehicle.getCoord(), e.getLight().getCoord());
            Object _xifexpression_2 = null;
            if ((this.distWithLight < this.dist_vision)) {
              _xifexpression_2 = Boolean.valueOf(this.LightIsView = true);
            } else {
              double _xblockexpression_2 = (double) 0;
              {
                this.LightIsView = false;
                _xblockexpression_2 = this.distWithLight = (-1);
              }
              _xifexpression_2 = Double.valueOf(_xblockexpression_2);
            }
            _xblockexpression_1 = ((Object)_xifexpression_2);
          }
          _xifexpression_1 = ((Object)_xblockexpression_1);
        } else {
          double _xblockexpression_2 = (double) 0;
          {
            this.LightIsView = false;
            _xblockexpression_2 = this.distWithLight = (-1);
          }
          _xifexpression_1 = Double.valueOf(_xblockexpression_2);
        }
        _xifexpression = ((Object)_xifexpression_1);
      } else {
        double _xblockexpression_3 = (double) 0;
        {
          this.LightIsView = false;
          _xblockexpression_3 = this.distWithLight = (-1);
        }
        _xifexpression = Double.valueOf(_xblockexpression_3);
      }
      _xblockexpression = ((Object)_xifexpression);
    }
    return _xblockexpression;
  }
  
  public ArrayList<Vehicle> percept() {
    ArrayList<Vehicle> _arrayList = new ArrayList<Vehicle>();
    this.res = _arrayList;
    for (int i = 0; (i < this.vehicle.getEdge().getBodies().size()); i++) {
      {
        Vehicle v = this.vehicle.getEdge().getBodies().get(i);
        if (((!Objects.equal(v.getId(), this.vehicle.getId())) && (this.isCarBehind(v) != true))) {
          boolean _isView = this.isView(v);
          if (_isView) {
            this.res.add(v);
          }
        }
      }
    }
    double _weight = this.vehicle.getEdge().getWeight();
    double _pos_edge = this.vehicle.getPos_edge();
    double dist_restante = (this.dist_vision - (_weight - _pos_edge));
    if ((dist_restante > 0)) {
      this.dfs_percept(this.vehicle.getEdge().getNout(), dist_restante);
    }
    return this.res;
  }
  
  public void dfs_percept(final Node n, final double dist_restante) {
    for (int i = 0; (i < n.getNeighboors().size()); i++) {
      {
        Node nout = n.getNeighboors().get(i);
        String _valueOf = String.valueOf(n.getId());
        String _valueOf_1 = String.valueOf(nout.getId());
        Edge e = this.G.getDict_Edges().get((_valueOf + _valueOf_1));
        for (int k = 0; (k < e.getBodies().size()); k++) {
          {
            Vehicle vh = e.getBodies().get(k);
            if ((((!Objects.equal(vh.getId(), this.vehicle.getId())) && this.isView(vh)) && (this.isCarBehind(vh) != true))) {
              this.res.add(vh);
            }
          }
        }
        double _weight = e.getWeight();
        double newResDist = (dist_restante - _weight);
        if ((newResDist > 0)) {
          this.dfs_percept(nout, newResDist);
        }
      }
    }
  }
  
  @Pure
  public boolean isCarBehind(final Vehicle v) {
    boolean res = false;
    int _id_ = v.getEdge().getId_();
    int _id__1 = this.vehicle.getEdge().getId_();
    if ((_id_ == _id__1)) {
      double _pos_edge = v.getPos_edge();
      double _pos_edge_1 = this.vehicle.getPos_edge();
      if ((_pos_edge < _pos_edge_1)) {
        res = true;
      }
    }
    return res;
  }
  
  @Pure
  public boolean isView(final Vehicle v) {
    boolean res = true;
    double _distance_vehicle = this.tool.distance_vehicle(this.vehicle, v);
    if ((_distance_vehicle > this.dist_vision)) {
      res = false;
    }
    return res;
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
    Perception other = (Perception) obj;
    if (Double.doubleToLongBits(other.dist_vision) != Double.doubleToLongBits(this.dist_vision))
      return false;
    if (other.LightIsView != this.LightIsView)
      return false;
    if (Double.doubleToLongBits(other.distWithLight) != Double.doubleToLongBits(this.distWithLight))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Double.hashCode(this.dist_vision);
    result = prime * result + Boolean.hashCode(this.LightIsView);
    result = prime * result + Double.hashCode(this.distWithLight);
    return result;
  }
  
  @Pure
  public Vehicle getVehicle() {
    return this.vehicle;
  }
  
  public void setVehicle(final Vehicle vehicle) {
    this.vehicle = vehicle;
  }
  
  @Pure
  public ArrayList<Vehicle> getRes() {
    return this.res;
  }
  
  public void setRes(final ArrayList<Vehicle> res) {
    this.res = res;
  }
  
  @Pure
  public double getDistWithLight() {
    return this.distWithLight;
  }
  
  public void setDistWithLight(final double distWithLight) {
    this.distWithLight = distWithLight;
  }
}
