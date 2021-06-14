package traffic_simulation.environment;

import com.google.common.base.Objects;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.environment.Edge;
import traffic_simulation.environment.Graph;
import traffic_simulation.environment.Node;

@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Path {
  private ArrayList<Node> openList;
  
  private ArrayList<Node> closeList;
  
  private Map<Integer, double[]> ghf;
  
  private Map<Integer, Integer> pred;
  
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private ArrayList<Node> pathNodes;
  
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private ArrayList<Edge> pathEdges;
  
  public Path() {
    ArrayList<Node> _arrayList = new ArrayList<Node>();
    this.openList = _arrayList;
    ArrayList<Node> _arrayList_1 = new ArrayList<Node>();
    this.closeList = _arrayList_1;
    Hashtable<Integer, double[]> _hashtable = new Hashtable<Integer, double[]>();
    this.ghf = _hashtable;
    Hashtable<Integer, Integer> _hashtable_1 = new Hashtable<Integer, Integer>();
    this.pred = _hashtable_1;
    ArrayList<Node> _arrayList_2 = new ArrayList<Node>();
    this.pathNodes = _arrayList_2;
    ArrayList<Edge> _arrayList_3 = new ArrayList<Edge>();
    this.pathEdges = _arrayList_3;
  }
  
  public void Astar(final Graph G, final Node nStart, final Node nEnd) {
    boolean stop = false;
    double[] ghfstart = { 0.0, 0.0, 0.0 };
    this.ghf.put(Integer.valueOf(nStart.getId()), ghfstart);
    this.pred.put(Integer.valueOf(nStart.getId()), Integer.valueOf((-1)));
    this.openList.add(nStart);
    while (((!this.openList.isEmpty()) && (stop == false))) {
      {
        Node n = this.bestNodeinTheList(this.openList);
        boolean _equals = Objects.equal(n, nEnd);
        if (_equals) {
          stop = true;
        }
        for (int i = 0; (i < n.getNeighboors().size()); i++) {
          {
            Node v = n.getNeighboors().get(i);
            double v_g = this.cost_g(G, n, v);
            double v_h = this.heuristique(v, nEnd);
            double v_f = this.f(v_g, v_h);
            if (((!this.openList.contains(v)) && (!this.closeList.contains(v)))) {
              double[] ghf_v = { v_g, v_h, v_f };
              Integer pred_v = Integer.valueOf(n.getId());
              this.ghf.put(Integer.valueOf(v.getId()), ghf_v);
              this.pred.put(Integer.valueOf(v.getId()), pred_v);
              this.openList.add(v);
            } else {
              double _get = this.ghf.get(Integer.valueOf(v.getId()))[2];
              if ((v_f < _get)) {
                this.ghf.get(Integer.valueOf(v.getId()))[0] = v_g;
                this.ghf.get(Integer.valueOf(v.getId()))[1] = v_h;
                this.ghf.get(Integer.valueOf(v.getId()))[2] = v_f;
                boolean _contains = this.closeList.contains(v);
                if (_contains) {
                  this.closeList.remove(v);
                  this.openList.add(v);
                }
              }
            }
          }
        }
        this.openList.remove(n);
        this.closeList.add(n);
      }
    }
    this.ResNode(G, nStart, nEnd);
    this.resEdge(G);
  }
  
  public void ResNode(final Graph G, final Node nStart, final Node nEnd) {
    int idStart = nStart.getId();
    ArrayList<Node> invRes = new ArrayList<Node>();
    invRes.add(nEnd);
    int p = nEnd.getId();
    while ((p != idStart)) {
      {
        p = ((this.pred.get(Integer.valueOf(G.getListNodes().get(p).getId()))) == null ? 0 : (this.pred.get(Integer.valueOf(G.getListNodes().get(p).getId()))).intValue());
        invRes.add(G.getListNodes().get(p));
      }
    }
    for (int i = 0; (i < invRes.size()); i++) {
      int _size = invRes.size();
      this.pathNodes.add(invRes.get(((_size - 1) - i)));
    }
  }
  
  public void resEdge(final Graph G) {
    for (int i = 0; (i < (this.pathNodes.size() - 1)); i++) {
      {
        String _valueOf = String.valueOf(this.pathNodes.get(i).getId());
        String _valueOf_1 = String.valueOf(this.pathNodes.get((i + 1)).getId());
        String key = (_valueOf + _valueOf_1);
        this.pathEdges.add(G.getDict_Edges().get(key));
      }
    }
  }
  
  public Node bestNodeinTheList(final ArrayList<Node> L) {
    Node best = L.get(0);
    for (int i = 1; (i < L.size()); i++) {
      boolean _isBest = this.isBest(best, L.get(i));
      if ((_isBest == false)) {
        best = L.get(i);
      }
    }
    return best;
  }
  
  @Pure
  public boolean isBest(final Node n1, final Node n2) {
    boolean res = false;
    double[] ghf1 = this.ghf.get(Integer.valueOf(n1.getId()));
    double[] ghf2 = this.ghf.get(Integer.valueOf(n2.getId()));
    double _get = ghf1[2];
    double _get_1 = ghf2[2];
    if ((_get < _get_1)) {
      res = true;
    }
    return res;
  }
  
  @Pure
  public double cost_g(final Graph G, final Node Nin, final Node Nout) {
    double res = this.ghf.get(Integer.valueOf(Nin.getId()))[0];
    String _valueOf = String.valueOf(Nin.getId());
    String _valueOf_1 = String.valueOf(Nout.getId());
    String keyEdge = (_valueOf + _valueOf_1);
    Edge e = G.getDict_Edges().get(keyEdge);
    double _weight = e.getWeight();
    res = (res + _weight);
    return res;
  }
  
  @Pure
  public double heuristique(final Node n, final Node nEnd) {
    double res = 0;
    double _x = nEnd.getCoord().getX();
    double _x_1 = n.getCoord().getX();
    double _power = Math.pow((_x - _x_1), 2);
    double _y = nEnd.getCoord().getY();
    double _y_1 = n.getCoord().getY();
    double _power_1 = Math.pow((_y - _y_1), 2);
    res = Math.sqrt((_power + _power_1));
    return res;
  }
  
  @Pure
  public double f(final double g, final double h) {
    return (g + h);
  }
  
  public void show() {
    InputOutput.<Integer>println(Integer.valueOf(this.pathNodes.size()));
    InputOutput.<Integer>println(Integer.valueOf(this.pathEdges.size()));
    for (int i = 0; (i < this.pathEdges.size()); i++) {
      int _id = this.pathEdges.get(i).getNin().getId();
      String _plus = (Integer.valueOf(_id) + " - ");
      int _id_1 = this.pathEdges.get(i).getNout().getId();
      InputOutput.<String>println((_plus + Integer.valueOf(_id_1)));
    }
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }
  
  @Pure
  public ArrayList<Node> getPathNodes() {
    return this.pathNodes;
  }
  
  public void setPathNodes(final ArrayList<Node> pathNodes) {
    this.pathNodes = pathNodes;
  }
  
  @Pure
  public ArrayList<Edge> getPathEdges() {
    return this.pathEdges;
  }
  
  public void setPathEdges(final ArrayList<Edge> pathEdges) {
    this.pathEdges = pathEdges;
  }
}
