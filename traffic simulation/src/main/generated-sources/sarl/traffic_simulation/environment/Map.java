package traffic_simulation.environment;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Pure;
import traffic_simulation.environment.Edge;
import traffic_simulation.environment.Graph;
import traffic_simulation.environment.Node;

@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Map {
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private Graph G;
  
  private int l;
  
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private int w;
  
  public Map(final int length, final int width) {
    this.l = length;
    this.w = width;
    double[] coord = { 0.0, (0.4 * this.w), (0.4 * this.l), (0.4 * this.w), 0.0, (0.5 * this.w), (0.4 * this.l), (0.5 * this.w), 0.0, (0.6 * this.w), (0.4 * this.l), (0.6 * this.w), (0.45 * this.l), this.w, (0.45 * this.l), (0.65 * this.w), (0.55 * this.l), this.w, (0.55 * this.l), (0.65 * this.w), this.l, (0.6 * this.w), (0.6 * this.l), (0.6 * this.w), this.l, (0.5 * this.w), (0.6 * this.l), (0.5 * this.w), this.l, (0.4 * this.w), (0.6 * this.l), (0.4 * this.w), (0.55 * this.l), 0.0, (0.55 * this.l), (0.35 * this.w), (0.45 * this.l), 0.0, (0.45 * this.l), (0.35 * this.w) };
    ArrayList<Node> LN = new ArrayList<Node>();
    ArrayList<ArrayList<Node>> Lst_A = new ArrayList<ArrayList<Node>>();
    int id = 0;
    for (int i = 0; (i < ((List<Double>)Conversions.doWrapArray(coord)).size()); i = (i + 2)) {
      {
        double _get = coord[i];
        double _get_1 = coord[(i + 1)];
        Node _node = new Node(id, Double.valueOf(_get), Double.valueOf(_get_1));
        LN.add(_node);
        id = (id + 1);
      }
    }
    for (int k = 0; (k < LN.size()); k++) {
      {
        ArrayList<Node> a = new ArrayList<Node>();
        a.add(LN.get(k));
        Lst_A.add(a);
      }
    }
    for (int j = 0; (j < LN.size()); j = (j + 2)) {
      {
        Node N1 = LN.get(j);
        Node N2 = LN.get((j + 1));
        if (((j < 6) || ((j > 9) && (j < 16)))) {
          N1.setStatut("y");
          N2.setStatut("y");
        } else {
          N1.setStatut("x");
          N2.setStatut("x");
        }
        if (((((j == 0) || (j == 10)) || (j == 6)) || (j == 16))) {
          Lst_A.get((j + 1)).add(N1);
        } else {
          Lst_A.get(j).add(N2);
        }
      }
    }
    Lst_A.get(3).add(LN.get(17));
    Lst_A.get(5).add(LN.get(7));
    Lst_A.get(5).add(LN.get(11));
    Lst_A.get(9).add(LN.get(1));
    Lst_A.get(9).add(LN.get(11));
    Lst_A.get(9).add(LN.get(17));
    Lst_A.get(13).add(LN.get(7));
    Lst_A.get(15).add(LN.get(1));
    Lst_A.get(15).add(LN.get(17));
    Lst_A.get(19).add(LN.get(1));
    Lst_A.get(19).add(LN.get(7));
    Lst_A.get(19).add(LN.get(11));
    Graph _graph = new Graph(LN, Lst_A);
    this.G = _graph;
    this.G.LstAdj_To_Graph();
  }
  
  @Pure
  public ArrayList<Edge> poly() {
    return this.G.getListEdge();
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
    Map other = (Map) obj;
    if (other.l != this.l)
      return false;
    if (other.w != this.w)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Integer.hashCode(this.l);
    result = prime * result + Integer.hashCode(this.w);
    return result;
  }
  
  @Pure
  public Graph getG() {
    return this.G;
  }
  
  public void setG(final Graph G) {
    this.G = G;
  }
  
  @Pure
  public int getW() {
    return this.w;
  }
  
  public void setW(final int w) {
    this.w = w;
  }
}
