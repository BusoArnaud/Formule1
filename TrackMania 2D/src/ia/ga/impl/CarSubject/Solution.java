package ia.ga.impl.CarSubject;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

import ia.ga.core.EvolutionAlgorithm;
import ia.ga.core.GeneticAlgorithmTemplate;
import ia.ga.impl.SimpleEvolutionAlgorithm;
import ia.ga.impl.car.KeyEventGame;
import ia.subject.Chromosome;
import ia.subject.Subject;
import tm2DGame.CarComponent;
import tm2DGame.terrain.Phatom;
import tm2DGame.terrain.Terrain;

public class Solution {

	private static final double CROSSOVER_RATE = 0.3;

	private static final double MUTATION_RATE = 0.1;

	private static final int SELECTION_SIZE = 10;

  CarComponent car;
  List<Terrain> pathAstar;

  public Solution(CarComponent car, List<Terrain> pathAstar) {
		this.car = car;
		this.pathAstar = pathAstar;
	}

	public List<Subject> call()
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		SubjectFitnessCalc fitnessCalc = new SubjectFitnessCalc(car, pathAstar);

		EvolutionAlgorithm<Subject, BehaviorSubject> evolutionAlgo = new SimpleEvolutionAlgorithm<>(CROSSOVER_RATE,
				MUTATION_RATE, SELECTION_SIZE, fitnessCalc, BehaviorSubject.class);

		GeneticAlgorithmTemplate<Subject, BehaviorSubject> algorithm = new GeneticAlgorithmTemplate<>(fitnessCalc,
				evolutionAlgo, BehaviorSubject.class, 50, 30);

		return algorithm.getSolution();
	}

	List<KeyEventGame> convert(List<Subject> subjects){
		return subjects.stream()
		.map(subject -> getActions(getEval(subject) , subject) )
		.collect(Collectors.toList()); 
	}


	public KeyEventGame getActions(double eval,Subject subject) {

		boolean accelerate = false;
		boolean rotate = false;
		int direction = 0;
		int rotationDirection = 0;
		Chromosome chromosome = subject.evalAll(eval);
		if (car.getSpeed() < chromosome.getTargetSpeed()) {
			direction = 1;
			accelerate = true;
		} else if (car.getSpeed() > chromosome.getTargetSpeed() + 2) {
			direction = -1;
			accelerate = true;
		}
		rotationDirection = chromosome.getTargetRotation();
		rotate = chromosome.getTargetRotation() != 0;

		return KeyEventGame.find(accelerate, rotate, direction, rotationDirection);
	}

	public double getEval(Subject subject) {
		Terrain myTerrain = new Phatom((int) Math.round(car.getpX()), (int) Math.round(car.getpY()));
		Terrain dest = pathAstar.get(0);
		double distance = pathAstar.get(0).getDistance(myTerrain);
		for (int i = 0; i < pathAstar.size(); i++) {
			Terrain terrain = pathAstar.get(i);
			double dist = terrain.getDistance(myTerrain);
			if (dist < distance) {
				distance = dist;
				if (pathAstar.size() > i + subject.getGeneComplex().getDistanceEval()) {
					dest = pathAstar.get(i + subject.getGeneComplex().getDistanceEval());
				} else {
					dest = pathAstar.get(pathAstar.size() - 1);
				}
			}
		}

		double targetX = (dest.getX() - car.getpX());
		double targetY = (dest.getY() - car.getpY());
		float angle = (float) Math.toDegrees(Math.atan2(targetY - 0, targetX - 1));
		float carAngle = (float) Math.toDegrees(car.getCurrentAngle());
		double newradian = Math.toRadians((angle - carAngle) % 360) + Math.PI / 2;

		return newradian;
	}

}
