package basicneuralnetwork.activationfunctions;

import org.ejml.simple.SimpleMatrix;

import static java.lang.Math.max;

// See https://www.v7labs.com/blog/neural-networks-activation-functions
public class LeakyReLuActivationFunction implements ActivationFunction {

    private static final String NAME = LEAKY_RELU;

    public SimpleMatrix applyActivationFunctionToMatrix(SimpleMatrix input) {
        SimpleMatrix output = new SimpleMatrix(input.numRows(), input.numCols());

        for (int i = 0; i < input.numRows(); i++) {
            // Column is always 0 because input has only one column
            double value = input.get(i, 0);
            double result = max(value, 0.1 * value);

            output.set(i, 0, result);
        }

        return output;
    }

    public SimpleMatrix applyDerivativeOfActivationFunctionToMatrix(SimpleMatrix input) {
        SimpleMatrix output = new SimpleMatrix(input.numRows(), input.numCols());

        for (int i = 0; i < input.numRows(); i++) {
            // Column is always 0 because input has only one column
            double value = input.get(i, 0);
            double result = (value >= 0) ? 1 : 0.01;

            output.set(i, 0, result);
        }

        return output;
    }

    public String getName() {
        return NAME;
    }
}
