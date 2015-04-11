/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kwetter.batch;

import kwetter.domain.Tweet;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;

/**
 *
 * @author Yongyi Jin
 */
@Dependent
@Named("kwetterItemProcessor")
public class kwetterItemProcessor implements ItemProcessor {

    SimpleDateFormat format = new SimpleDateFormat("M/dd/yy");

    @Override
    public Object[] processItem(Object t) {
        System.out.println("--- PROCESSING ITEM ---");
        System.out.println(t);

        String username = "";
        String tweetText = "";
        Date date = new Date();
        String postedFrom = "";

        String json = (String) t;
        while (!json.startsWith("{") & json.length() > 0) {
            json = json.substring(1, json.length());
        }
        while (!json.endsWith("}") & json.length() > 0) {
            json = json.substring(0, json.length() - 1);
        }
        if (json.indexOf("}") <= json.indexOf("{")) {
            return new Object[]{null, null};
        }

        JsonParserFactory factory = Json.createParserFactory(null);
        JsonParser parser = factory.createParser(new StringReader(json));

        String key = "";
        String value = "";
        HashMap<String, String> map = new HashMap<>();
        while (parser.hasNext()) {
            switch (parser.next()) {
                case KEY_NAME:
                    key = parser.getString();
                    break;
                case VALUE_STRING:
                    value = parser.getString();
                    map.put(key, value);
                    break;
            }
        }

        username = map.get("screenName");
        tweetText = map.get("tweet");
        postedFrom = map.get("postedFrom");
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(map.get("postDate"));
        } catch (ParseException ex) {
            Logger.getLogger(kwetterItemProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }

        Object[] result = new Object[]{new Tweet(tweetText, date, postedFrom), username};
        System.out.println(Arrays.deepToString(result));
        return result;
    }
}
