package labs.utilities;

public class Pair<K, V> extends javafx.util.Pair<K, V> {

    /**
     * Creates a new pair
     *
     * @param key   The key for this pair
     * @param value The value to use for this pair
     */
    public Pair(K key, V value) {
        super(key, value);
    }

    @Override
    public String toString() {
        return getValue().toString();
    }

    public static final class Converter {

        private Converter() {

        }

        public static double[] getKeysFromPair(Pair<Double, Double>[] pairs) {
            double[] result = new double[pairs.length];

            for (int i = 0; i < pairs.length; i++) {
                result[i] = pairs[i].getKey();
            }

            return result;
        }

        public static double[] getValuesFromPair(Pair<Double, Double>[] pairs) {
            double[] result = new double[pairs.length];

            for (int i = 0; i < pairs.length; i++) {
                result[i] = pairs[i].getValue();
            }

            return result;
        }
    }
}
