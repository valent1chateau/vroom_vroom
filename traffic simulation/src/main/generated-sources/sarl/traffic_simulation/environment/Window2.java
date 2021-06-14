package traffic_simulation.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.Map;
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
import traffic_simulation.environment.classicDriverBody;
import traffic_simulation.environment.trafficLight;

@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Window2 extends Stage {
  private Environment env;
  
  private int length;
  
  private int width;
  
  private Group group;
  
  private TreeMap<UUID, classicDriverBody> drivers = new TreeMap<UUID, classicDriverBody>();
  
  public Window2(final Environment e, final int h, final int w) {
    abstract class __Window2_0 extends AnimationTimer {
      public abstract void handle(final long now);
    }
    
    this.length = h;
    this.width = w;
    this.env = e;
    this.drivers = this.env.getBodyList();
    this.setTitle("simulation");
    ArrayList<Edge> _poly = this.env.getMap().poly();
    Group _group = new Group(((Node[])Conversions.unwrapArray(_poly, Node.class)));
    this.group = _group;
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
    AnimationTimer boucle = new __Window2_0() {
      public void handle(final long now) {
        Window2.this.drivers = Window2.this.env.getBodyList();
        Window2.this.group.getChildren().clear();
        for (int j = 0; (j < Window2.this.env.getMap().poly().size()); j++) {
          Window2.this.group.getChildren().add(Window2.this.env.getMap().poly().get(j));
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
        Set<Map.Entry<UUID, classicDriverBody>> _entrySet = Window2.this.drivers.entrySet();
        for (final Map.Entry<UUID, classicDriverBody> entry : _entrySet) {
          Window2.this.group.getChildren().add(Window2.this.drivers.get(entry.getKey()).getC());
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
    return result;
  }
}
