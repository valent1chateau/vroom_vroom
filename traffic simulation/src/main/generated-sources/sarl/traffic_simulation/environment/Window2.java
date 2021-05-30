package traffic_simulation.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
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
  
  private ArrayList<classicDriverBody> drivers;
  
  public Window2(final Environment e) {
    abstract class __Window2_0 extends AnimationTimer {
      public abstract void handle(final long now);
    }
    
    this.length = 1600;
    this.width = 900;
    Map _map = new Map(this.length, this.width);
    this.map = _map;
    ArrayList<classicDriverBody> _arrayList = new ArrayList<classicDriverBody>();
    this.drivers = _arrayList;
    this.setTitle("window2");
    ArrayList<Edge> _poly = this.map.poly();
    Group _group = new Group(((Node[])Conversions.unwrapArray(_poly, Node.class)));
    this.group = _group;
    for (int i = 0; (i < this.map.getG().getListNodes().size()); i++) {
      {
        Circle c = new Circle();
        c.setCenterX(this.map.getG().getListNodes().get(i).getCoord().getX());
        c.setCenterY(this.map.getG().getListNodes().get(i).getCoord().getY());
        c.setRadius((this.width * 0.01));
        this.group.getChildren().add(c);
      }
    }
    this.t = 0;
    classicDriverBody _classicDriverBody = new classicDriverBody(this.map);
    this.drivers.add(_classicDriverBody);
    this.group.getChildren().add(this.drivers.get(0).getC());
    AnimationTimer boucle = new __Window2_0() {
      public void handle(final long now) {
        if ((Window2.this.t == 10)) {
          classicDriverBody _classicDriverBody = new classicDriverBody(Window2.this.map);
          Window2.this.drivers.add(_classicDriverBody);
          int _size = Window2.this.drivers.size();
          Window2.this.group.getChildren().add(Window2.this.drivers.get((_size - 1)).getC());
          Window2.this.t = 0;
        }
        for (int k = 0; (k < Window2.this.drivers.size()); k++) {
          Window2.this.drivers.get(k).accelerateFree(0.5);
        }
        Window2.this.t = (Window2.this.t + 1);
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
    return result;
  }
}
