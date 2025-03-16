package org.project.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

public class Json {

    /**
     * Utility class for handling JSON operations using Jackson's ObjectMapper.
     * This class provides a customized ObjectMapper instance with specific configurations.
     */
    private static ObjectMapper myObjectMapper = defaultObjectMapper();

    /**
     * Creates and configures a default ObjectMapper instance.
     *
     * @return a customized ObjectMapper instance
     *
     * <p>Configuration details:</p>
     * <ul>
     *     <li>Disables {@code DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES},
     *         which allows JSON to contain extra fields that do not map to the Java class.</li>
     * </ul>
     *
     * <p>This ensures that unknown properties in the JSON input do not cause deserialization failures.</p>
     */
    private static ObjectMapper defaultObjectMapper() {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }


    /**
     * Parses a JSON string and converts it into a {@link JsonNode} representation.
     *
     * @param jsonSource the JSON string to be parsed
     * @return a {@link JsonNode} representing the JSON structure
     * @throws JsonProcessingException if the input string is not a valid JSON format
     *
     * <p>This method utilizes Jackson's {@code readTree()} method to create a tree-like structure
     * of the JSON data, allowing easy traversal and manipulation.</p>
     *
     * <h3>Example Usage:</h3>
     * <pre>
     * String json = "{ \"name\": \"Alice\", \"age\": 25 }";
     * JsonNode node = Json.parse(json);
     * System.out.println(node.get("name").asText()); // Output: Alice
     * </pre>
     *
     * <h3>Why Use JsonNode?</h3>
     * <ul>
     *     <li>Allows flexible access to JSON properties without needing a predefined Java class.</li>
     *     <li>Prevents deserialization errors caused by unknown fields.</li>
     *     <li>Supports tree traversal and data manipulation.</li>
     * </ul>
     */
    public static JsonNode parse(String jsonSource) throws JsonProcessingException {
        return myObjectMapper.readTree(jsonSource);
    }

    /**
     * Converts a {@link JsonNode} into a Java object of the specified type.
     *
     * @param <A>   the type of the Java object to be returned
     * @param node  the {@link JsonNode} representing the JSON structure
     * @param clazz the target Java class to convert the JSON data into
     * @return an instance of the specified class populated with the data from the JSON node
     * @throws JsonProcessingException if the conversion fails due to incompatible types or malformed JSON
     *
     * <p>This method uses Jackson's {@code treeToValue()} to deserialize the JSON tree into a Java object.</p>
     *
     * <h3>Example Usage:</h3>
     * <pre>
     * String json = "{ \"name\": \"Alice\", \"age\": 25 }";
     * JsonNode node = Json.parse(json);
     * User user = Json.fromJson(node, User.class);
     * System.out.println(user.getName()); // Output: Alice
     * </pre>
     *
     * <h3>Why Use this Method?</h3>
     * <ul>
     *     <li>Allows for JSON data transformation after parsing before conversion.</li>
     *     <li>Provides a safe and flexible way to deserialize JSON without requiring direct string parsing.</li>
     *     <li>Handles unknown properties gracefully based on ObjectMapper configuration.</li>
     * </ul>
     */
    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException {
        return myObjectMapper.treeToValue(node, clazz);
    }

    public static JsonNode toJson(Object obj) {
        return myObjectMapper.valueToTree(obj);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateJson(node, false);
    }

    public static String stringifyPretty(JsonNode node) throws JsonProcessingException {
        return generateJson(node, true);
    }

    private static String generateJson(Object o, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = myObjectMapper.writer();
        if(!pretty){
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(o);
    }
}
