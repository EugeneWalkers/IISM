package labs.lab3.utilities.concreteDistributions;

import labs.utilities.*;

import java.util.Random;

public class XSquareDistribution implements ContinuousDistribution {

    /**
     * Степени свободы
     */
    private double k;

    private int i = 0;

    public XSquareDistribution(final double k) {
        this.k = k;
    }

    @Override
    public double getDistributionFunctionValue(double i) {
        double sum = 0;

        for (int f = getMin(); f<i; f++){

            sum += f * getP(f);
        }

        return sum;

//        return 0.5 + LaplaceFunction.getValue(Math.sqrt(2 * i) - Math.sqrt(2 * k - 1));
    }

    @Override
    public double getExpectedValue() {
        return k;
    }

    @Override
    public double getVariance() {
        return 2 * k;
    }

//    @Override
    private double getP(final double i) {

        if (k%2 == 0){
            return calculateKEven(i);
        }
        else{
            return calculateKOdd(i);
        }

        //return 0.5 - LaplaceFunction.getValue(Math.sqrt(2 * i) - Math.sqrt(2 * k - 1));

        //return Math.pow(i, k/2 - 1)*Math.exp(-i/2)/(Math.pow(2, k/2)* GammaFunction.getEilerValue(k/2));
    }

    private double calculateKEven(final double x){
        double sum = 0;

        for (int i=1; i<=(k-2)/2; i++){
            sum += Math.pow(x, i)/getDoubleFact(2*i);
        }

        return 1 + sum;
    }

    private double calculateKOdd(final double x){
        double sum = 0;

        for (int i=1; i<=(k-1)/2; i++){
            sum += Math.pow(x, i-1)/getDoubleFact(2*i - 1);
        }

        sum*=getFiLittle(Math.sqrt(x));

        sum += LaplaceFunction.getValue(Math.sqrt(x));
        sum = -sum;
        sum++;

        return 2*sum;
    }

    private double getDoubleFact(final double i){
        double result = 1;

        if (i%2 == 0){
            for (int k=2; k<=i; k+=2){
                result*=k;
            }
        }
        else{
            for (int k=1; k<=i; k+=2){
                result*=k;
            }
        }

        return result;
    }

    private double getFiLittle(final double x){
        return Math.sqrt(2.0*Math.PI)*Math.exp(-x*x/2.0);
    }

    @Override
    public Pair<Double, Double> generateValue() {

        double value = 0;



        //for (int t = 0; t < k; t++) {
            double temp = StandartDistributionGenerator.getArray()[i] * StandartDistributionGenerator.getArray()[i]
                    + StandartDistributionGenerator.getArray()[1000 + i] * StandartDistributionGenerator.getArray()[1000 + i]
                    + StandartDistributionGenerator.getArray()[2000 + i] * StandartDistributionGenerator.getArray()[2000 + i]
                    + StandartDistributionGenerator.getArray()[3000 + i] * StandartDistributionGenerator.getArray()[3000 + i];
            //double temp = StandartDistributionGenerator.getArray()[random.nextInt(6000)];
            //value += temp * temp;
        //}
        i++;
        return new Pair<>(temp, getDistributionFunctionValue(temp));

    }

    @Override
    public String getDescription() {
        return "X2-распределение.";
    }
}
