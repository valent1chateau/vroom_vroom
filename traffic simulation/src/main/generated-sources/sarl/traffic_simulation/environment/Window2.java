package traffic_simulation.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.environment.Edge;
import traffic_simulation.environment.Environment;
import traffic_simulation.environment.Map;
import traffic_simulation.environment.classicDriverBody;
import traffic_simulation.environment.trafficLight;

/**
 * @author jeome
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Window2 extends Stage {
  private Environment env;
  
  private Map map;
  
  private int length;
  
  private int width;
  
  private Circle cc = new Circle();
  
  private long t;
  
  private double dt = 0.5;
  
  private Group group;
  
  private boolean test = false;
  
  private TreeMap<UUID, classicDriverBody> drivers = new TreeMap<UUID, classicDriverBody>();
  
  public Window2(final Environment e, final int h, final int w) {
    abstract class __Window2_0 extends AnimationTimer {
      public abstract void handle(final long now);
    }
    
    this.length = h;
    this.width = w;
    this.env = e;
    this.drivers = this.env.getBodyList();
    this.setTitle("window2");
    ArrayList<Edge> _poly = this.env.getMap().poly();
    Group _group = new Group(((Node[])Conversions.unwrapArray(_poly, Node.class)));
    this.group = _group;
    for (int i = 0; (i < this.env.getMap().getG().getListNodes().size()); i++) {
      {
        traffic_simulation.environment.Node n = this.env.getMap().getG().getListNodes().get(i);
        if (((((((n.getId() != 3) && (n.getId() != 5)) && (n.getId() != 9)) && (n.getId() != 13)) && (n.getId() != 15)) && (n.getId() != 19))) {
          Circle c = new Circle();
          c.setCenterX(n.getCoord().getX());
          c.setCenterY(n.getCoord().getY());
          c.setRadius((this.width * 0.01));
          this.group.getChildren().add(c);
        }
      }
    }
    for (int l = 0; (l < this.env.getTrafficlight_lst().size()); l++) {
      {
        trafficLight tf = this.env.getTrafficlight_lst().get(l);
        Circle c = new Circle();
        c.setCenterX(tf.getCoord().getX());
        c.setCenterY(tf.getCoord().getY());
        c.setRadius((this.width * 0.01));
        boolean _isGreen = tf.isGreen();
        if (_isGreen) {
          c.setFill(Color.GREEN);
        } else {
          c.setFill(Color.RED);
        }
        this.group.getChildren().add(c);
      }
    }
    this.t = 0;
    AnimationTimer boucle = new __Window2_0() {
      public void handle(final long now) {
        Window2.this.drivers = Window2.this.env.getBodyList();
        Window2.this.group.getChildren().clear();
        for (int j = 0; (j < Window2.this.env.getMap().poly().size()); j++) {
          Window2.this.group.getChildren().add(Window2.this.env.getMap().poly().get(j));
        }
        for (int i = 0; (i < Window2.this.env.getMap().getG().getListNodes().size()); i++) {
          {
            traffic_simulation.environment.Node n = Window2.this.env.getMap().getG().getListNodes().get(i);
            if (((((((n.getId() != 3) && (n.getId() != 5)) && (n.getId() != 9)) && (n.getId() != 13)) && (n.getId() != 15)) && (n.getId() != 19))) {
              Circle c = new Circle();
              c.setCenterX(n.getCoord().getX());
              c.setCenterY(n.getCoord().getY());
              c.setRadius((Window2.this.width * 0.01));
              Window2.this.group.getChildren().add(c);
            }
          }
        }
        for (int l = 0; (l < Window2.this.env.getTrafficlight_lst().size()); l++) {
          {
            trafficLight tf = Window2.this.env.getTrafficlight_lst().get(l);
            Circle c = new Circle();
            c.setCenterX(tf.getCoord().getX());
            c.setCenterY(tf.getCoord().getY());
            c.setRadius((Window2.this.width * 0.01));
            boolean _isGreen = tf.isGreen();
            if (_isGreen) {
              c.setFill(Color.GREEN);
            } else {
              c.setFill(Color.RED);
            }
            Window2.this.group.getChildren().add(c);
          }
        }
        Set<java.util.Map.Entry<UUID, classicDriverBody>> _entrySet = Window2.this.drivers.entrySet();
        for (final java.util.Map.Entry<UUID, classicDriverBody> entry : _entrySet) {
          {
            Window2.this.group.getChildren().add(Window2.this.drivers.get(entry.getKey()).getC());
            Window2.this.t = (Window2.this.t + 1);
          }
        }
      }
    };
    boucle.start();
    Scene scene = new Scene(this.group, this.length, this.width);
    this.setScene(scene);
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
    Window2 other = (Window2) obj;
    if (other.length != this.length)
      return false;
    if (other.width != this.width)
      return false;
    if (other.t != this.t)
      return false;
    if (Double.doubleToLongBits(other.dt) != Double.doubleToLongBits(this.dt))
      return false;
    if (other.test != this.test)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Integer.hashCode(this.length);
    result = prime * result + Integer.hashCode(this.width);
    result = prime * result + Long.hashCode(this.t);
    result = prime * result + Double.hashCode(this.dt);
    result = prime * result + Boolean.hashCode(this.test);
    return result;
  }
}
