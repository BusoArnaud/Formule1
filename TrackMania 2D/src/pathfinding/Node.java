package pathfinding;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleFunction;

import tm2DGame.terrain.Terrain;

public class Node {

  private final Terrain terrain;
  
  private final Node parent;
  
  private final double f;
  
  private final double h;
  
  private final double g;
  
  public Node(Terrain terrain, Node parent, ToDoubleFunction<Terrain> gSupplier, ToDoubleFunction<Terrain> hSupplier, boolean hasBlock) {
    this.terrain = terrain;
    this.parent = parent;
    double newG = gSupplier.applyAsDouble(terrain);
    this.g = (parent != null? parent.getG() : 0) +  (hasBlock ? newG * 2 : newG);
    this.h = hSupplier.applyAsDouble(terrain);
    this.f = this.g + this.h;
  }
  
  public Terrain getTerrain() {
    return terrain;
  }

  public double getH() {
    return h;
  }

  public double getG() {
    return g;
  }
  
  public double getF() {
    return f;
  }

  public List<Node> getPath(){
	  List<Node> path = parent == null ? new ArrayList<>() : parent.getPath();
	  path.add(this);
	  return path;
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((terrain == null) ? 0 : terrain.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Node other = (Node) obj;
    if (terrain == null) {
      if (other.terrain != null)
        return false;
    } else if (!terrain.equals(other.terrain))
      return false;
    return true;
  }
  
  @Override 
  public String toString() {
    return terrain.toString();
  }
  
}
