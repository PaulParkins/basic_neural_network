package basicneuralnetwork.utilities;

import basicneuralnetwork.NeuralNetwork;
import basicneuralnetwork.activationfunctions.ActivationFunction;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import org.ejml.data.Matrix;
import org.ejml.simple.SimpleOperations;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by KimFeichtinger on 26.04.18.
 */
public class FileReaderAndWriter {

    public static void writeToFile(NeuralNetwork nn, File file) {
        try {
            FileWriter writer = new FileWriter(file);
            Gson gson = getGsonBuilder().create();
            String nnData = gson.toJson(nn);

            writer.write(nnData);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static NeuralNetwork readFromFile(File file) {
        try {
            Gson gson = getGsonBuilder().create();
            JsonReader jsonReader = new JsonReader(new FileReader(file));
            return gson.fromJson(jsonReader, NeuralNetwork.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Get a GsonBuilder-object with all the needed TypeAdapters added
    private static GsonBuilder getGsonBuilder(){
        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(ActivationFunction.class, new InterfaceAdapter<ActivationFunction>());
        gsonBuilder.registerTypeAdapter(Matrix.class, new InterfaceAdapter<Matrix>());
        gsonBuilder.registerTypeAdapter(SimpleOperations.class, new InterfaceAdapter<SimpleOperations>());
        gsonBuilder.setPrettyPrinting();

        return gsonBuilder;
    }

}
