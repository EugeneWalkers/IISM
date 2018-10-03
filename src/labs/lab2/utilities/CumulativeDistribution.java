package labs.lab2.utilities;

public interface CumulativeDistribution {

    double getDistributionFunctionValue(double i);

    double getExpectedValue();

    double getVariance();

    double getP(int i);

    String getDescription();

}
