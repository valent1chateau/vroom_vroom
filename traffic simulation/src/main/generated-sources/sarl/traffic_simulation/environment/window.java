package traffic_simulation.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.environment.Edge;
import traffic_simulation.environment.Map;
import traffic_simulation.environment.classicDriverBody;

/**
 * @author jeome
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class window extends Application {
  private Map map;
  
  private double length;
  
  private double width;
  
  private Circle cc = new Circle();
  
  private long t;
  
  private double dt = 0.5;
  
  private Group group;
  
  private ArrayList<classicDriverBody> drivers;
  
  public window() {
    this.length = 800;
    this.width = 600;
    Map _map = new Map(this.length, this.width);
    this.map = _map;
    ArrayList<classicDriverBody> _arrayList = new ArrayList<classicDriverBody>();
    this.drivers = _arrayList;
    this.cc.setCenterX(0);
    this.cc.setCenterY(0);
    this.cc.setRadius((this.width * 0.01));
  }
  
  public void start(final Stage primaryStage) {
    abstract class __window_0 extends AnimationTimer {
      public abstract void handle(final long now);
    }
    
    primaryStage.setTitle("window");
    double[] points = { 0.0d, 20.0d, 40.0d, 240.0d, 60.0d, 180.0d, 80.0d, 200.0d, 100.0d, 90.0d };
    Polyline polyline = new Polyline(points);
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
    AnimationTimer boucle = new __window_0() {
      public void handle(final long now) {
        if ((window.this.t == 40)) {
          classicDriverBody _classicDriverBody = new classicDriverBody(window.this.map);
          window.this.drivers.add(_classicDriverBody);
          int _size = window.this.drivers.size();
          window.this.group.getChildren().add(window.this.drivers.get((_size - 1)).getC());
          window.this.t = 0;
        }
        for (int k = 0; (k < window.this.drivers.size()); k++) {
          window.this.drivers.get(k).accelerateFree(0.5);
        }
        window.this.t = (window.this.t + 1);
      }
    };
    boucle.start();
    Scene scene = new Scene(this.group, this.length, this.width);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  public void show() {
    Application.launch();
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
    window other = (window) obj;
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
