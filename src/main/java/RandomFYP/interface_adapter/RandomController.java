package RandomFYP.interface_adapter;

import RandomFYP.use_case.RandomInputBoundary;

public class RandomController {
    private final RandomInputBoundary randomInputBoundary;

    public RandomController(RandomInputBoundary randomInputBoundary) {
        this.randomInputBoundary = randomInputBoundary;
    }

    public void handleRandom() {
        randomInputBoundary.generateRandom();
    }
}