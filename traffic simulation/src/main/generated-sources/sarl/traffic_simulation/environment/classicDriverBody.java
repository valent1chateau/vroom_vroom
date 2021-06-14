package traffic_simulation.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import traffic_simulation.environment.Graph;
import traffic_simulation.environment.Map;
import traffic_simulation.environment.Node;
import traffic_simulation.environment.Perception;
import traffic_simulation.environment.Vehicle;

@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class classicDriverBody extends Vehicle {
  public classicDriverBody(final Map map, final double acc_max) {
    super();
    this.newPath(map.getG());
    double x = this.getPath().getPathNodes().get(0).getCoord().getX();
    double y = this.getPath().getPathNodes().get(0).getCoord().getY();
    Point2D.Double _double = new Point2D.Double();
    this.setCoord(_double);
    Circle _circle = new Circle();
    this.setC(_circle);
    this.getCoord().setLocation(x, y);
    this.getC().setCenterX(x);
    this.getC().setCenterY(y);
    this.getC().setRadius(2.25);
    this.setDim((2 * 2.25));
    this.getC().setFill(Color.BLUE);
    this.D_Max();
    this.setAccMax(acc_max);
    this.setMaxSpeed(13.8);
    Graph _g = map.getG();
    Perception _perception = new Perception(this, 150.0, _g);
    this.setPerception(_perception);
    this.setEdge(this.getPath().getPathEdges().get(0));
  }
  
  public void newPath(final Graph G) {
    ArrayList<Node> in = new ArrayList<Node>();
    ArrayList<Node> out = new ArrayList<Node>();
    for (int i = 0; (i < G.getListNodes().size()); i = (i + 2)) {
      if (((((i == 0) || (i == 6)) || (i == 10)) || (i == 16))) {
        out.add(G.getListNodes().get(i));
      } else {
        in.add(G.getListNodes().get(i));
      }
    }
    Random rand = new Random();
    int numIN = rand.nextInt(in.size());
    int numOUT = 0;
    int _id = in.get(numIN).getId();
    if ((_id == 2)) {
      numOUT = 3;
    } else {
      int _id_1 = in.get(numIN).getId();
      if ((_id_1 == 12)) {
        numOUT = 1;
      } else {
        int _id_2 = in.get(numIN).getId();
        if ((_id_2 == 4)) {
          int _nextInt = rand.nextInt(2);
          numOUT = (1 + _nextInt);
        } else {
          int _id_3 = in.get(numIN).getId();
          if ((_id_3 == 8)) {
            int _nextInt_1 = rand.nextInt(3);
            numOUT = (1 + _nextInt_1);
            if ((numOUT == 1)) {
              numOUT = 0;
            }
          } else {
            int _id_4 = in.get(numIN).getId();
            if ((_id_4 == 14)) {
              int _nextInt_2 = rand.nextInt(2);
              numOUT = (2 + _nextInt_2);
              if ((numOUT == 2)) {
                numOUT = 0;
              }
            } else {
              int _id_5 = in.get(numIN).getId();
              if ((_id_5 == 18)) {
                numOUT = rand.nextInt(3);
              }
            }
          }
        }
      }
    }
    this.setSpawn(in.get(numIN));
    this.getPath().Astar(G, in.get(numIN), out.get(numOUT));
  }
}
