/**
 * $Id$
 * 
 * Copyright (c) 2011-17 Stephane GALLAND <stephane.galland@utbm.fr>.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * This program is free software; you can redistribute it and/or modify
 */
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

/**
 * Defined a perception unit.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
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
  
  public Perception(final Vehicle v, final double dv, final Graph g) {
    this.vehicle = v;
    this.dist_vision = dv;
    this.G = g;
    ArrayList<Vehicle> _arrayList = new ArrayList<Vehicle>();
    this.res = _arrayList;
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
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Double.hashCode(this.dist_vision);
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
}
