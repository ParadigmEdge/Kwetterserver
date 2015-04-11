/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kwetter.batch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import javax.batch.api.chunk.AbstractItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author Yongyi Jin
 */
@Dependent
@Named("kwetterItemReader")
public class kwetterItemReader extends AbstractItemReader {

    private BufferedReader reader;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        System.out.println("--- OPENING BUFFERED READER---");

        reader = new BufferedReader(
                new InputStreamReader(
                        this
                        .getClass()
                        .getClassLoader()
                        .getResourceAsStream("/META-INF/kwetter-input.json")));
    }

    @Override
    public String readItem() {
        System.out.println("--- READING ITEM ---");
        try {
            System.out.println(reader.readLine());
            return reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
