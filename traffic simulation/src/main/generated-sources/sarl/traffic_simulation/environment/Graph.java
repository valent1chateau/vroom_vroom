package traffic_simulation.environment;

import com.google.common.base.Objects;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.environment.Edge;
import traffic_simulation.environment.Node;
import traffic_simulation.util.Tools;

@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Graph {
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private int nbNodes;
  
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private ArrayList<Edge> ListEdge;
  
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private ArrayList<Node> ListNodes;
  
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private ArrayList<ArrayList<Node>> Lst_Adj;
  
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private Map<String, Edge> dict_Edges;
  
  private Tools tool = new Tools();
  
  public Graph() {
  }
  
  public Graph(final ArrayList<Node> ListN, final ArrayList<ArrayList<Node>> Lst_A) {
    this.ListNodes = ListN;
    this.Lst_Adj = Lst_A;
    this.nbNodes = this.ListNodes.size();
    ArrayList<Edge> _arrayList = new ArrayList<Edge>();
    this.ListEdge = _arrayList;
    Hashtable<String, Edge> _hashtable = new Hashtable<String, Edge>();
    this.dict_Edges = _hashtable;
  }
  
  public void LstAdj_To_Graph() {
    int idt = 0;
    int nbP = 0;
    double pas = 0;
    nbP = 200;
    idt = 0;
    for (int i = 0; (i < this.Lst_Adj.size()); i++) {
      int _size = this.Lst_Adj.get(i).size();
      if ((_size != 1)) {
        for (int j = 1; (j < this.Lst_Adj.get(i).size()); j++) {
          {
            String key = null;
            Edge E = null;
            Node Nin = this.ListNodes.get(i);
            Node Nout = this.Lst_Adj.get(i).get(j);
            Nin.getNeighboors().add(Nout);
            String _valueOf = String.valueOf(Nin.getId());
            String _valueOf_1 = String.valueOf(Nout.getId());
            key = (_valueOf + _valueOf_1);
            Edge _edge = new Edge(idt, Nin, Nout);
            E = _edge;
            double[] points = new double[(2 * (nbP + 1))];
            int tour = 1;
            double _y = Nin.getCoord().getY();
            double _y_1 = Nout.getCoord().getY();
            if ((_y == _y_1)) {
              double _x = Nout.getCoord().getX();
              double _x_1 = Nin.getCoord().getX();
              pas = ((_x - _x_1) / nbP);
              points[0] = Nin.getCoord().getX();
              points[1] = Nin.getCoord().getY();
              points[(2 * nbP)] = Nout.getCoord().getX();
              points[((2 * nbP) + 1)] = Nout.getCoord().getY();
              for (int t = 2; (t < (2 * nbP)); t = (t + 2)) {
                {
                  double _x_2 = Nin.getCoord().getX();
                  points[t] = (_x_2 + (pas * tour));
                  points[(t + 1)] = Nin.getCoord().getY();
                  tour = (tour + 1);
                }
              }
              double _x_2 = Nout.getCoord().getX();
              double _x_3 = Nin.getCoord().getX();
              double _power = Math.pow((_x_2 - _x_3), 2);
              double _y_2 = Nout.getCoord().getY();
              double _y_3 = Nin.getCoord().getY();
              double _power_1 = Math.pow((_y_2 - _y_3), 2);
              E.setWeight(Math.sqrt((_power + _power_1)));
            } else {
              double _x_4 = Nin.getCoord().getX();
              double _x_5 = Nout.getCoord().getX();
              if ((_x_4 == _x_5)) {
                double _y_4 = Nout.getCoord().getY();
                double _y_5 = Nin.getCoord().getY();
                pas = ((_y_4 - _y_5) / nbP);
                points[0] = Nin.getCoord().getX();
                points[1] = Nin.getCoord().getY();
                points[(2 * nbP)] = Nout.getCoord().getX();
                points[((2 * nbP) + 1)] = Nout.getCoord().getY();
                for (int v = 2; (v < (2 * nbP)); v = (v + 2)) {
                  {
                    points[v] = Nin.getCoord().getX();
                    double _y_6 = Nin.getCoord().getY();
                    points[(v + 1)] = (_y_6 + (pas * tour));
                    tour = (tour + 1);
                  }
                }
                double _x_6 = Nout.getCoord().getX();
                double _x_7 = Nin.getCoord().getX();
                double _power_2 = Math.pow((_x_6 - _x_7), 2);
                double _y_6 = Nout.getCoord().getY();
                double _y_7 = Nin.getCoord().getY();
                double _power_3 = Math.pow((_y_6 - _y_7), 2);
                E.setWeight(Math.sqrt((_power_2 + _power_3)));
              } else {
                tour = 0;
                Point2D PC = null;
                Point2D.Double _double = new Point2D.Double();
                PC = _double;
                String _statut = Nin.getStatut();
                boolean _equals = Objects.equal(_statut, "x");
                if (_equals) {
                  PC.setLocation(Nin.getCoord().getX(), Nout.getCoord().getY());
                } else {
                  PC.setLocation(Nout.getCoord().getX(), Nin.getCoord().getY());
                }
                points = this.bezierQuad(Nin.getCoord(), PC, Nout.getCoord(), nbP);
                double _x_8 = Nin.getCoord().getX();
                double _x_9 = PC.getX();
                double _x_10 = Nout.getCoord().getX();
                double _y_8 = Nin.getCoord().getY();
                double _y_9 = PC.getY();
                double _y_10 = Nout.getCoord().getY();
                E.setWeight(this.tool.weight_curve(
                  new double[] { _x_8, _x_9, _x_10, _y_8, _y_9, _y_10 }, 0.0, 1.0, 10000.0));
              }
            }
            final double[] _converted_points = (double[])points;
            E.getPoints().addAll(((Collection<? extends Double>)Conversions.doWrapArray(_converted_points)));
            this.ListEdge.add(E);
            this.dict_Edges.put(key, E);
            idt = (idt + 1);
          }
        }
      }
    }
  }
  
  public double[] bezierQuad(final Point2D P1, final Point2D PC, final Point2D P2, final int nbPoints) {
    double[] res = null;
    ArrayList<Double> t = null;
    Double d = null;
    Double X = null;
    Double Y = null;
    Point2D p = null;
    res = new double[(2 * (nbPoints + 1))];
    Point2D.Double _double = new Point2D.Double();
    p = _double;
    ArrayList<Double> _arrayList = new ArrayList<Double>();
    t = _arrayList;
    d = Double.valueOf(0.0);
    double pas = (1.0d / nbPoints);
    for (int i = 0; (i <= nbPoints); i++) {
      {
        t.add(d);
        d = Double.valueOf((((d) == null ? 0 : (d).doubleValue()) + pas));
      }
    }
    int tour = 0;
    for (int j = 0; (j < ((2 * nbPoints) + 2)); j = (j + 2)) {
      {
        double _x = P1.getX();
        Double _get = t.get(tour);
        double _power = Math.pow((1.0d - ((_get) == null ? 0 : (_get).doubleValue())), 2.0d);
        double _x_1 = PC.getX();
        Double _get_1 = t.get(tour);
        Double _get_2 = t.get(tour);
        double _x_2 = P2.getX();
        Double _get_3 = t.get(tour);
        double _power_1 = Math.pow(((_get_3) == null ? 0 : (_get_3).doubleValue()), 2.0d);
        X = Double.valueOf((((_x * _power) + (((2.0d * _x_1) * ((_get_1) == null ? 0 : (_get_1).doubleValue())) * (1.0d - ((_get_2) == null ? 0 : (_get_2).doubleValue())))) + 
          (_x_2 * _power_1)));
        double _y = P1.getY();
        Double _get_4 = t.get(tour);
        double _power_2 = Math.pow((1.0d - ((_get_4) == null ? 0 : (_get_4).doubleValue())), 2);
        double _y_1 = PC.getY();
        Double _get_5 = t.get(tour);
        Double _get_6 = t.get(tour);
        double _y_2 = P2.getY();
        Double _get_7 = t.get(tour);
        double _power_3 = Math.pow(((_get_7) == null ? 0 : (_get_7).doubleValue()), 2);
        Y = Double.valueOf((((_y * _power_2) + (((2 * _y_1) * ((_get_5) == null ? 0 : (_get_5).doubleValue())) * (1.0d - ((_get_6) == null ? 0 : (_get_6).doubleValue())))) + 
          (_y_2 * _power_3)));
        res[j] = ((X) == null ? 0 : (X).doubleValue());
        res[(j + 1)] = ((Y) == null ? 0 : (Y).doubleValue());
        tour = (tour + 1);
      }
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
    Graph other = (Graph) obj;
    if (other.nbNodes != this.nbNodes)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Integer.hashCode(this.nbNodes);
    return result;
  }
  
  @Pure
  public int getNbNodes() {
    return this.nbNodes;
  }
  
  public void setNbNodes(final int nbNodes) {
    this.nbNodes = nbNodes;
  }
  
  @Pure
  public ArrayList<Edge> getListEdge() {
    return this.ListEdge;
  }
  
  public void setListEdge(final ArrayList<Edge> ListEdge) {
    this.ListEdge = ListEdge;
  }
  
  @Pure
  public ArrayList<Node> getListNodes() {
    return this.ListNodes;
  }
  
  public void setListNodes(final ArrayList<Node> ListNodes) {
    this.ListNodes = ListNodes;
  }
  
  @Pure
  public ArrayList<ArrayList<Node>> getLst_Adj() {
    return this.Lst_Adj;
  }
  
  public void setLst_Adj(final ArrayList<ArrayList<Node>> Lst_Adj) {
    this.Lst_Adj = Lst_Adj;
  }
  
  @Pure
  public Map<String, Edge> getDict_Edges() {
    return this.dict_Edges;
  }
  
  public void setDict_Edges(final Map<String, Edge> dict_Edges) {
    this.dict_Edges = dict_Edges;
  }
}
