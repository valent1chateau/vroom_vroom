package traffic_simulation.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.environment.Edge;
import traffic_simulation.environment.Map;
import traffic_simulation.environment.Node;
import traffic_simulation.environment.Path;

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
  
  public window() {
    super();
    this.length = 1600;
    this.width = 900;
    Map _map = new Map(this.length, this.width);
    this.map = _map;
    Path p = new Path();
    p.Astar(this.map.getG(), this.map.getG().getListNodes().get(4), this.map.getG().getListNodes().get(6));
    ArrayList<Node> d = p.getPathNodes();
    ArrayList<Edge> e = p.getPathEdges();
    for (int i = 0; (i < d.size()); i++) {
      InputOutput.<Integer>println(Integer.valueOf(d.get(i).getId()));
    }
    for (int j = 0; (j < e.size()); j++) {
      {
        InputOutput.<ArrayList<Edge>>println(e);
        int _id = e.get(j).getNin().getId();
        String _plus = (Integer.valueOf(_id) + " - ");
        int _id_1 = e.get(j).getNout().getId();
        InputOutput.<String>println((_plus + Integer.valueOf(_id_1)));
      }
    }
  }
  
  public void start(final Stage primaryStage) {
    primaryStage.setTitle("window");
    double[] points = { 0.0d, 20.0d, 40.0d, 240.0d, 60.0d, 180.0d, 80.0d, 200.0d, 100.0d, 90.0d };
    Polyline polyline = new Polyline(points);
    ArrayList<Edge> _poly = this.map.poly();
    Group group = new Group(((javafx.scene.Node[])Conversions.unwrapArray(_poly, javafx.scene.Node.class)));
    for (int i = 0; (i < this.map.getG().getListNodes().size()); i++) {
      {
        Circle c = new Circle();
        c.setCenterX(this.map.getG().getListNodes().get(i).getCoord().getX());
        c.setCenterY(this.map.getG().getListNodes().get(i).getCoord().getY());
        c.setRadius((this.width * 0.01));
        group.getChildren().add(c);
      }
    }
    Scene scene = new Scene(group, this.length, this.width);
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
    return result;
  }
}
