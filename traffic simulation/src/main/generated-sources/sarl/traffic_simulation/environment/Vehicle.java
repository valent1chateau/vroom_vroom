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

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import javafx.collections.ObservableList;
import javafx.scene.shape.Circle;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.environment.Body;
import traffic_simulation.environment.Edge;
import traffic_simulation.environment.Graph;
import traffic_simulation.environment.Map;
import traffic_simulation.environment.Path;

/**
 * Object on the environment.
 * 
 * @author St&eacute;phane GALLAND &lt;stephane.galland@utbm.fr&gt;
 * @version $Name$ $Revision$ $Date$
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class Vehicle implements Body {
  @Accessors
  private UUID id;
  
  @Accessors
  private Circle c;
  
  @Accessors
  private Point2D coord;
  
  @Accessors
  private Map map;
  
  @Accessors
  private Edge edge;
  
  private double acc;
  
  private double speed;
  
  private double maxSpeed;
  
  private double position;
  
  private double distance;
  
  private double distanceMax;
  
  @Accessors
  private Path path;
  
  public Vehicle() {
    this.id = UUID.randomUUID();
    Path _path = new Path();
    this.path = _path;
    this.position = 0.0;
    this.distance = 0.0;
    this.distanceMax = 0.0;
  }
  
  public UUID getID() {
    return this.id;
  }
  
  public void positionToCoord() {
    ArrayList<Edge> e = this.path.getPathEdges();
    int j = 0;
    boolean stop = false;
    double TotalWeight = 0.0;
    while (((j < e.size()) && (stop == false))) {
      {
        double _weight = e.get(j).getWeight();
        TotalWeight = (TotalWeight + _weight);
        if ((this.position == 0)) {
          stop = true;
        } else {
          if ((this.position <= TotalWeight)) {
            double pos_edge = 0;
            double dist_edge = 0.0;
            if ((j > 0)) {
              for (int i = 0; (i < j); i++) {
                double _weight_1 = e.get(i).getWeight();
                dist_edge = (dist_edge + _weight_1);
              }
            }
            pos_edge = (this.position - dist_edge);
            double _weight_1 = e.get(j).getWeight();
            double percent = (pos_edge / _weight_1);
            ObservableList<Double> poly = e.get(j).getPoints();
            int _size = poly.size();
            int nbP = (_size / 2);
            long _round = Math.round((nbP * percent));
            int a = ((int) _round);
            if ((a != 0)) {
              int ind = ((2 * a) - 2);
              Double x = poly.get(ind);
              Double y = poly.get((ind + 1));
              this.coord.setLocation(((x) == null ? 0 : (x).doubleValue()), ((y) == null ? 0 : (y).doubleValue()));
              this.c.setCenterX(((x) == null ? 0 : (x).doubleValue()));
              this.c.setCenterY(((y) == null ? 0 : (y).doubleValue()));
            }
            int _id_ = e.get(j).getId_();
            int _id__1 = this.edge.getId_();
            if ((_id_ != _id__1)) {
              this.edge.removeBody(this);
              this.edge = e.get(j);
              this.edge.addBody(this);
            }
            stop = true;
          } else {
            if ((this.position > this.distanceMax)) {
              this.edge.removeBody(this);
              stop = true;
            }
          }
        }
        j = (j + 1);
      }
    }
  }
  
  public double move(final double X) {
    double _xblockexpression = (double) 0;
    {
      this.distance = X;
      _xblockexpression = this.position = (this.position + this.distance);
    }
    return _xblockexpression;
  }
  
  public abstract void newPath(final Graph G);
  
  public double D_Max() {
    double _xblockexpression = (double) 0;
    {
      double res = 0.0;
      for (int i = 0; (i < this.path.getPathEdges().size()); i++) {
        double _weight = this.path.getPathEdges().get(i).getWeight();
        res = (res + _weight);
      }
      _xblockexpression = this.distanceMax = res;
    }
    return _xblockexpression;
  }
  
  public void accelerate() {
  }
  
  public void deccelerate() {
  }
  
  public void calculatePerceptions() {
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
    Vehicle other = (Vehicle) obj;
    if (!Objects.equals(this.id, other.id))
      return false;
    if (Double.doubleToLongBits(other.acc) != Double.doubleToLongBits(this.acc))
      return false;
    if (Double.doubleToLongBits(other.speed) != Double.doubleToLongBits(this.speed))
      return false;
    if (Double.doubleToLongBits(other.maxSpeed) != Double.doubleToLongBits(this.maxSpeed))
      return false;
    if (Double.doubleToLongBits(other.position) != Double.doubleToLongBits(this.position))
      return false;
    if (Double.doubleToLongBits(other.distance) != Double.doubleToLongBits(this.distance))
      return false;
    if (Double.doubleToLongBits(other.distanceMax) != Double.doubleToLongBits(this.distanceMax))
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
    result = prime * result + Double.hashCode(this.acc);
    result = prime * result + Double.hashCode(this.speed);
    result = prime * result + Double.hashCode(this.maxSpeed);
    result = prime * result + Double.hashCode(this.position);
    result = prime * result + Double.hashCode(this.distance);
    result = prime * result + Double.hashCode(this.distanceMax);
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
  public Circle getC() {
    return this.c;
  }
  
  public void setC(final Circle c) {
    this.c = c;
  }
  
  @Pure
  public Point2D getCoord() {
    return this.coord;
  }
  
  public void setCoord(final Point2D coord) {
    this.coord = coord;
  }
  
  @Pure
  public Map getMap() {
    return this.map;
  }
  
  public void setMap(final Map map) {
    this.map = map;
  }
  
  @Pure
  public Edge getEdge() {
    return this.edge;
  }
  
  public void setEdge(final Edge edge) {
    this.edge = edge;
  }
  
  @Pure
  public Path getPath() {
    return this.path;
  }
  
  public void setPath(final Path path) {
    this.path = path;
  }
}
