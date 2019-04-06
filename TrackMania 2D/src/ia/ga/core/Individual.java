package ia.ga.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Individual<T> {

	protected final List<T> genes;

	private final int genomeLength;

	private Integer fitness;

	protected Individual() {
		this.genomeLength = lengthSupplier();
		this.genes = new ArrayList<>(genomeLength);
	}

	// Create a random individual
	public final void randomizeIndividual() {
		for (int i = 0; i < genomeLength; i++) {
			genes.add(geneSupplier());
		}
	}

	public void mutate(double mutationRate) {
		for (int i = 0; i < size(); i++) {
			if (Math.random() <= mutationRate) {
				genes.set(i, geneSupplier());
			}
		}
	}

	public T getGene(int index) {
		return genes.get(index);
	}

	public void add(T value) {
		genes.add(value);
	}

	public int size() {
		return this.genes.size();
	}

	protected int getFitness(FitnessCalc<T> fitnessCalc) {
		if (fitness == null)
			fitness = fitnessCalc.getFitness(this);

		return fitness;
	}

	public int getFitness() {
		return fitness;
	}

	public List<T> getChromosome() {
		return Collections.unmodifiableList(this.genes);
	}

	/**
	 * Gene supplier that supply a random gene.
	 *
	 * @return the t
	 */
	protected abstract T geneSupplier();

	/**
	 * Length supplier that defines the length of the Chromosome
	 *
	 * @return the int
	 */
	protected abstract int lengthSupplier();

	@Override
	public String toString() {
		return genes.toString();
	}
}