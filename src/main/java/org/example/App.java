package org.example;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.reader.StreamReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

/**
 * Kafka Demo
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        InputStream input = null;
        input = StreamReader.class.getClassLoader().getResourceAsStream("application.yml");
        Yaml yaml = new Yaml();
        Map<String, Object> object = yaml.load(input);
        object.get("kafka");
    }
}
