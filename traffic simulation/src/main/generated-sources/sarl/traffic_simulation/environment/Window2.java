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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.environment.Edge;
import traffic_simulation.environment.Environment;
import traffic_simulation.environment.Map;
import traffic_simulation.environment.classicDriverBody;

/**
 * @author jeome
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Window2 extends Stage {
  private Environment env;
  
  private Map map;
  
  private double length;
  
  private double width;
  
  private Circle cc = new Circle();
  
  private long t;
  
  private double dt = 0.5;
  
  private Group group;
  
  private boolean test = false;
  
  private TreeMap<UUID, classicDriverBody> drivers = new TreeMap<UUID, classicDriverBody>();
  
  public Window2(final Environment e) {
    abstract class __Window2_0 extends AnimationTimer {
      public abstract void handle(final long now);
    }
    
    this.length = 1600;
    this.width = 900;
    Map _map = new Map(this.length, this.width);
    this.map = _map;
    this.env = e;
    this.drivers = this.env.getBodyList();
    this.setTitle("window2");
    ArrayList<Edge> _poly = this.env.getMap().poly();
    Group _group = new Group(((Node[])Conversions.unwrapArray(_poly, Node.class)));
    this.group = _group;
    for (int i = 0; (i < this.env.getMap().getG().getListNodes().size()); i++) {
      {
        Circle c = new Circle();
        c.setCenterX(this.env.getMap().getG().getListNodes().get(i).getCoord().getX());
        c.setCenterY(this.env.getMap().getG().getListNodes().get(i).getCoord().getY());
        c.setRadius((this.width * 0.01));
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
            Circle c = new Circle();
            c.setCenterX(Window2.this.env.getMap().getG().getListNodes().get(i).getCoord().getX());
            c.setCenterY(Window2.this.env.getMap().getG().getListNodes().get(i).getCoord().getY());
            c.setRadius((Window2.this.width * 0.01));
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
    if (Double.doubleToLongBits(other.length) != Double.doubleToLongBits(this.length))
      return false;
    if (Double.doubleToLongBits(other.width) != Double.doubleToLongBits(this.width))
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
    result = prime * result + Double.hashCode(this.length);
    result = prime * result + Double.hashCode(this.width);
    result = prime * result + Long.hashCode(this.t);
    result = prime * result + Double.hashCode(this.dt);
    result = prime * result + Boolean.hashCode(this.test);
    return result;
  }
}
