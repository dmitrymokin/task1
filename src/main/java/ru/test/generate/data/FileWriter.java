package ru.test.generate.data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FileWriter {

    public void write(String filePathInput, String filePathOutput, Generator generator, Integer count) {
        BufferedWriter writer = null;
        FileReader reader = new FileReader(filePathInput);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Variable.FORMAT_DATE);
        ArrayList listOffices = (ArrayList) reader.toList();
        try {
            writer = new BufferedWriter(new java.io.FileWriter(filePathOutput));
            writer.write(Variable.START_FILE);
            for (int i = 0; i < count; i++) {
                writer.write(formatter.format(generator.getDate())  + Variable.SEPARATOR);
                writer.write(generator.getTime().toString() + Variable.SEPARATOR);
                writer.write( listOffices.get(generator.getInt(0, listOffices.size()-1)) + Variable.SEPARATOR);
                writer.write(i + 1 + Variable.SEPARATOR);
                writer.write(generator.getSum() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer!=null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
