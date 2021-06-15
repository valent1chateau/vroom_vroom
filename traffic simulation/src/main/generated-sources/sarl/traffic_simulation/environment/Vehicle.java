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
import traffic_simulation.environment.Node;
import traffic_simulation.environment.Path;
import traffic_simulation.environment.Perception;
import traffic_simulation.util.Tools;

@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class Vehicle implements Body {
  private Tools tool = new Tools();
  
  @Accessors
  private UUID id;
  
  @Accessors
  private Circle c;
  
  @Accessors
  private Point2D coord;
  
  @Accessors
  private double dim;
  
  @Accessors
  private Edge edge;
  
  @Accessors
  private Node spawn;
  
  @Accessors
  private double pos_edge;
  
  @Accessors
  private double accMax;
  
  @Accessors
  private double acc;
  
  @Accessors
  private double speed;
  
  @Accessors
  private double maxSpeed;
  
  @Accessors
  private double position;
  
  private double distance;
  
  @Accessors
  private double distanceMax;
  
  @Accessors
  private Path path;
  
  @Accessors
  private Perception perception;
  
  @Accessors
  private boolean kill = false;
  
  public Vehicle() {
    this.id = UUID.randomUUID();
    Path _path = new Path();
    this.path = _path;
    this.position = 0.0;
    this.pos_edge = 0.0;
    this.distance = 0.0;
    this.distanceMax = 0.0;
    this.speed = 0.0;
    this.acc = 0.0;
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
            double dist_edge = 0.0;
            if ((j > 0)) {
              for (int i = 0; (i < j); i++) {
                double _weight_1 = e.get(i).getWeight();
                dist_edge = (dist_edge + _weight_1);
              }
            }
            this.pos_edge = (this.position - dist_edge);
            double _weight_1 = e.get(j).getWeight();
            double percent = (this.pos_edge / _weight_1);
            ObservableList<Double> poly = e.get(j).getPoints();
            int _size = poly.size();
            int nbP = (_size / 2);
            long _round = Math.round((nbP * percent));
            int at = ((int) _round);
            if ((at != 0)) {
              int ind = ((2 * at) - 2);
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
              this.kill = true;
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
  
  public void accelerate(final double t) {
    if ((this.acc < (-2))) {
      this.acc = (-2);
    }
    if ((this.speed < this.maxSpeed)) {
      double _calc_speed = this.tool.calc_speed(this.acc, t);
      this.speed = (this.speed + _calc_speed);
    }
    if ((this.speed < 0)) {
      this.speed = 0;
    }
    this.move(this.tool.calc_position(this.speed, t));
    this.positionToCoord();
  }
  
  public boolean canSpawn() {
    boolean res = true;
    ArrayList<Vehicle> lst_v = this.edge.getBodies();
    double sx = this.spawn.getCoord().getX();
    double sy = this.spawn.getCoord().getY();
    int s_id = this.spawn.getId();
    lst_v.remove(this);
    boolean _isEmpty = lst_v.isEmpty();
    if ((_isEmpty != true)) {
      int i = 0;
      while (((i < lst_v.size()) && (res == true))) {
        {
          if ((s_id == 8)) {
            double _y = lst_v.get(i).coord.getY();
            if (((sy - (this.dim / 2)) < (_y + (lst_v.get(i).dim / 2)))) {
              res = false;
            }
          } else {
            if ((s_id == 18)) {
              double _y_1 = lst_v.get(i).coord.getY();
              if (((sy + (this.dim / 2)) > (_y_1 - (lst_v.get(i).dim / 2)))) {
                res = false;
              }
            } else {
              if (((s_id == 2) || (s_id == 4))) {
                double _x = lst_v.get(i).coord.getX();
                if (((sx + (this.dim / 2)) > (_x - (lst_v.get(i).dim / 2)))) {
                  res = false;
                }
              } else {
                double _x_1 = lst_v.get(i).coord.getX();
                if (((sx - (this.dim / 2)) < (_x_1 + (lst_v.get(i).dim / 2)))) {
                  res = false;
                }
              }
            }
          }
          i = (i + 1);
        }
      }
    }
    return res;
  }
  
  public boolean initialzeEdgeBodies() {
    return this.edge.addBody(this);
  }
  
  public void calculatePerceptions() {
    this.perception.perceptLight();
    this.perception.percept();
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
    if (Double.doubleToLongBits(other.dim) != Double.doubleToLongBits(this.dim))
      return false;
    if (Double.doubleToLongBits(other.pos_edge) != Double.doubleToLongBits(this.pos_edge))
      return false;
    if (Double.doubleToLongBits(other.accMax) != Double.doubleToLongBits(this.accMax))
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
    if (other.kill != this.kill)
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
    result = prime * result + Double.hashCode(this.dim);
    result = prime * result + Double.hashCode(this.pos_edge);
    result = prime * result + Double.hashCode(this.accMax);
    result = prime * result + Double.hashCode(this.acc);
    result = prime * result + Double.hashCode(this.speed);
    result = prime * result + Double.hashCode(this.maxSpeed);
    result = prime * result + Double.hashCode(this.position);
    result = prime * result + Double.hashCode(this.distance);
    result = prime * result + Double.hashCode(this.distanceMax);
    result = prime * result + Boolean.hashCode(this.kill);
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
  public double getDim() {
    return this.dim;
  }
  
  public void setDim(final double dim) {
    this.dim = dim;
  }
  
  @Pure
  public Edge getEdge() {
    return this.edge;
  }
  
  public void setEdge(final Edge edge) {
    this.edge = edge;
  }
  
  @Pure
  public Node getSpawn() {
    return this.spawn;
  }
  
  public void setSpawn(final Node spawn) {
    this.spawn = spawn;
  }
  
  @Pure
  public double getPos_edge() {
    return this.pos_edge;
  }
  
  public void setPos_edge(final double pos_edge) {
    this.pos_edge = pos_edge;
  }
  
  @Pure
  public double getAccMax() {
    return this.accMax;
  }
  
  public void setAccMax(final double accMax) {
    this.accMax = accMax;
  }
  
  @Pure
  public double getAcc() {
    return this.acc;
  }
  
  public void setAcc(final double acc) {
    this.acc = acc;
  }
  
  @Pure
  public double getSpeed() {
    return this.speed;
  }
  
  public void setSpeed(final double speed) {
    this.speed = speed;
  }
  
  @Pure
  public double getMaxSpeed() {
    return this.maxSpeed;
  }
  
  public void setMaxSpeed(final double maxSpeed) {
    this.maxSpeed = maxSpeed;
  }
  
  @Pure
  public double getPosition() {
    return this.position;
  }
  
  public void setPosition(final double position) {
    this.position = position;
  }
  
  @Pure
  public double getDistanceMax() {
    return this.distanceMax;
  }
  
  public void setDistanceMax(final double distanceMax) {
    this.distanceMax = distanceMax;
  }
  
  @Pure
  public Path getPath() {
    return this.path;
  }
  
  public void setPath(final Path path) {
    this.path = path;
  }
  
  @Pure
  public Perception getPerception() {
    return this.perception;
  }
  
  public void setPerception(final Perception perception) {
    this.perception = perception;
  }
  
  @Pure
  public boolean isKill() {
    return this.kill;
  }
  
  public void setKill(final boolean kill) {
    this.kill = kill;
  }
}
