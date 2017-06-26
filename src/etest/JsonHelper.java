/*
 * Copyright (C) 2017 Piotr Martycz
 */
package etest;

import etest.entities.Test;
import etest.entities.SingleChoiceTest;
import etest.entities.MultiChoiceTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import etest.JsonHelper.Initializable;
import java.io.IOException;
import java.io.Reader;

import java.lang.reflect.Type;
import java.time.Duration;
import java.time.Instant;

/**
 * Class implementing JSON (de)serialization.
 * 
 * This class abstracts out the actual library used for json manipulation.
 * 
 * @author pmart
 */
public class JsonHelper {
    public interface Initializable {
        void init();
    }
    
    private static final Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(
            RuntimeTypeAdapterFactory.of(Test.class, "type")
                .registerSubtype(SingleChoiceTest.class, "single")
                .registerSubtype(MultiChoiceTest.class, "multi")
        )
        .registerTypeAdapterFactory(new TypeAdapterFactory() {        
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
                final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);

                return new TypeAdapter<T>() {
                    public void write(JsonWriter out, T value) throws IOException {
                        delegate.write(out, value);
                    }

                    public T read(JsonReader in) throws IOException {
                        T obj = delegate.read(in);
                        if (obj instanceof Initializable) {
                            ((Initializable)obj).init();
                        }
                        return obj;
                    }
                };
            }
        })
        .registerTypeAdapter(Instant.class, (JsonSerializer<Instant>) 
                (Instant src, Type srcType, JsonSerializationContext context) 
                        -> new JsonPrimitive(src.getEpochSecond()))
        .registerTypeAdapter(Instant.class, (JsonDeserializer<Instant>) 
                (JsonElement json, Type type, JsonDeserializationContext context) 
                        -> Instant.ofEpochSecond(json.getAsLong()))
        .registerTypeAdapter(Duration.class, (JsonSerializer<Duration>) 
                (Duration src, Type srcType, JsonSerializationContext context) 
                        -> new JsonPrimitive(src.toMinutes()))
        .registerTypeAdapter(Duration.class, (JsonDeserializer<Duration>) 
                (JsonElement json, Type type, JsonDeserializationContext context) 
                        -> Duration.ofMinutes(json.getAsLong()))
        .create();
   
    /**
     * Returns a json serialization of java object.
     * 
     * @param src java object
     * @return json string
     */
    public static String toJson(Object src) {
        return gson.toJson(src);
    }
    
    /**
     * Returns a java object based on json read from stream.
     * 
     * @param <T> type of java object
     * @param reader stream containg json data
     * @param classOfT class of java object
     * @return  java object
     */
    public static <T> T fromJson(Reader reader, Class<T> classOfT) {
        JsonReader jsonReader = new JsonReader(reader);
        jsonReader.setLenient(true);
        return gson.fromJson(jsonReader, classOfT);
    }
    
    /**
     * Returns a java object based on json string.
     * 
     * @param <T> type of java object
     * @param json json string
     * @param classOfT type of java object
     * @return java object
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }
}
