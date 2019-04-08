package pathfinding;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

import tm2DGame.Circuit;
import tm2DGame.terrain.Terrain;

public class Astar implements Callable<List<Terrain>>{

  private Circuit circuit;
  
  private Terrain carTerrain;

  private ToDoubleFunction<Terrain> gSupplier;

  private ToDoubleFunction<Terrain> hSupplier;
  
  public Astar(Circuit circuit) {
    this.circuit = circuit;
  }

  /**
   * Run run the Astar algorithm that returns the best path according to G and H.
   *
   * @return the list
   */
  public synchronized List<Terrain> call() {
    
    // the closed set that contains Node that already has been processed
    Set<Terrain> closed = new HashSet<>();

    // get one of the endpoint of the circuit
    final Terrain endTerrain = circuit.getEndTerrains().get(0);

    // function to calculate G(cost) for a node. it depends of the deceleration of
    // the terrain
    gSupplier = terrain -> 1 / terrain.getSpeedDecreaseCoef();

    // function to calculate H(heuristic) of a node. In this case, it is the
    // distance.
    hSupplier = terrain -> terrain.getDistance(endTerrain)/10;

    // open List that contains Node that will be processed. We processed the one
    // with the lowest F(G + H) value first
    PriorityQueue<Node> openList = new PriorityQueue<>((node0, node1) -> Double.compare(node0.getF(), node1.getF()));

    // add adjacents nodes and calculate their F value. if we find an end node, we
    // return the path to this node.
    if(carTerrain == null) {
      carTerrain = circuit.getStart();
    }
    openList.add(new Node(carTerrain, null, gSupplier, hSupplier, false));
    while (!isEmpty(openList)) {
      Node currentNode = openList.poll();
      closed.add(currentNode.getTerrain());
      if (currentNode.getTerrain().isEnd()) {
        return currentNode.getPath().stream().map(node -> node.getTerrain()).collect(Collectors.toList());
      } else {
        addValidAdjacentNodesToOpen(currentNode, openList, closed);
      }
    }

    return Collections.emptyList();
  }

  /**
   * Adds the valid adjacent nodes to the open queue. valid here means not a block
   * terrain and not in already processed(closed set). If the node already exists
   * in the open queue, compare G(cost) values and see which one is cheaper. they
   * will be sorted when added according to their f value
   * 
   * @param node     the node
   * @param openList the open list
   * @param closed   the closed
   */
  private void addValidAdjacentNodesToOpen(Node node, PriorityQueue<Node> openList, Set<Terrain> closed) {
    List<Terrain> adjacents = circuit.getAdjacentTiles(node.getTerrain());
    boolean hasBlock = adjacents.stream().anyMatch(Terrain::isBlock);
    for (Terrain terrain : adjacents) {
      if (terrain != null && !terrain.isBlock() && !closed.contains(terrain)) {
        Node newNode = new Node(terrain, node, gSupplier, hSupplier, hasBlock);
        Optional<Node> existingTerrain = openList.stream().filter(newNode::equals).findFirst();
        if (existingTerrain.isPresent() && existingTerrain.get().getG() > newNode.getG()) {
          openList.remove(newNode);
          openList.add(newNode);
        } else if (!existingTerrain.isPresent()) {
          openList.add(newNode);
        }
      }
    }
  }

  private boolean isEmpty(PriorityQueue<Node> openList) {
    return openList == null || openList.isEmpty();
  }
  
  public void setCarTerrain(Terrain carTerrain) {
    this.carTerrain = carTerrain;
  }

}
