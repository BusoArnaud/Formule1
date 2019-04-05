package ia.ga.impl.car;

import ia.ga.core.Individual;

public class BehaviorIndividual extends Individual<KeyEventGame> {

	@Override
	protected KeyEventGame geneSupplier() {
		return KeyEventGame.getRandom();
	}

	@Override
	protected int lengthSupplier() {
		return 5;
	}

}
