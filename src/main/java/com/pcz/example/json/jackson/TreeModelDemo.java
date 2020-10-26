package com.pcz.example.json.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

/**
 * @author picongzhi
 */
public class TreeModelDemo {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) {
        traverseJson();
        traverseJsonArray();
        treeModelCrud();
    }

    private static void treeModelCrud() {
        try {
            JsonNode root = OBJECT_MAPPER.readTree(
                    TreeModelDemo.class.getClassLoader().getResourceAsStream("user.json"));
            System.out.println("before update: " +
                    OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(root));

            ((ObjectNode) root).put("id", 1000L);

            ObjectNode nameNode = (ObjectNode) root.path("name");
            if ("".equals(nameNode.path("middle").asText())) {
                nameNode.put("middle", "M");
            }

            nameNode.put("nickname", "pcz");
            nameNode.remove("last");

            ObjectNode positionNode = OBJECT_MAPPER.createObjectNode();
            positionNode.put("name", "Developer");
            positionNode.put("years", 10);
            ((ObjectNode) root).set("position", positionNode);

            ArrayNode gameNodes = OBJECT_MAPPER.createArrayNode();
            ObjectNode gameNode1 = OBJECT_MAPPER.createObjectNode();
            gameNode1.put("name", "Fall Out 4");
            gameNode1.put("price", 49.9);

            ObjectNode gameNode2 = OBJECT_MAPPER.createObjectNode();
            gameNode2.put("name", "Dark Soul 3");
            gameNode2.put("price", 59.9);

            gameNodes.add(gameNode1);
            gameNodes.add(gameNode2);
            ((ObjectNode) root).set("games", gameNodes);

            ObjectNode emailNode = OBJECT_MAPPER.createObjectNode();
            emailNode.put("type", "email");
            emailNode.put("ref", "picongzhi@gmail.com");

            JsonNode contactNode = root.path("contact");
            ((ArrayNode) contactNode).add(emailNode);

            System.out.println("after update: " +
                    OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void traverseJsonArray() {
        try {
            JsonNode root = OBJECT_MAPPER.readTree(
                    TreeModelDemo.class.getClassLoader().getResourceAsStream("user2.json"));
            for (JsonNode node : root) {
                printUserNode(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printUserNode(JsonNode jsonNode) {
        long id = jsonNode.path("id").asLong();
        System.out.println("id: " + id);

        JsonNode nameNode = jsonNode.path("name");
        if (!nameNode.isMissingNode()) {
            System.out.println("firstName: " + nameNode.path("first").asText());
            System.out.println("middleName: " + nameNode.path("middle").asText());
            System.out.println("lastName: " + nameNode.path("last").asText());
        }

        JsonNode contactNode = jsonNode.path("contact");
        if (!contactNode.isMissingNode()) {
            System.out.println("is array: " + contactNode.isArray());
            for (JsonNode node : contactNode) {
                System.out.println("type: " + node.path("type").asText());
                System.out.println("ref: " + node.path("ref").asText());
            }
        }
    }

    private static void traverseJson() {
        try {
            JsonNode root = OBJECT_MAPPER.readTree(
                    TreeModelDemo.class.getClassLoader().getResourceAsStream("user.json"));
            printUserNode(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
