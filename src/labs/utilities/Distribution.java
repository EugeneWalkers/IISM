package labs.utilities;

public interface Distribution {

    default int getMin(){
        return 0;
    }

    default int getMax(){
        return 1000;
    }

    double getDistributionFunctionValue(final double i);

    double getExpectedValue();

    double getVariance();

    Pair<Double, Double> generateValue();

    String getDescription();

}
