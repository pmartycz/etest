/*
 * Copyright (C) 2017 Piotr Martycz
 */
package etest;

/**
 *
 * @author pmart
 */
public class Examples {
    public static final String MULTIPLE_CHOICE_TEST_JSON = "{\n" +
"        \"id\": \"c10ae7c7-3b1d-48d7-b949-e0165b3f3822\",\n" +
"        \"name\": \"Przykładowy egzamin (test wielokrotnego wyboru)\",\n" +
"        \"type\": \"multi\",\n" +
"        \"owner\": \"anna\",\n" +
"        \"groups\": [ \"bd\", \"sk\" ],\n" +
"        \"timeLimit\": 10,\n" +
"        \"startTime\": 1460583976,\n" +
"        \"endTime\": 1483225200,\n" +
"        \"resultsAvailable\": false,\n" +
"        \"questions\": [\n" +
"            {\n" +
"                \"text\": \"Które z typów danych występują w formacie JSON?\",\n" +
"                \"options\": [\n" +
"                    \"Liczbowy\", \"Napisowy\", \"Boolowski\", \"Wskaźnikowy\", \"Tablicowy\"\n" +
"                ]\n" +
"            },\n" +
"            {\n" +
"                \"text\": \"Na jakim kontynencie leży Polska?\",\n" +
"                \"options\": [\n" +
"                    \"Europa\",\n" +
"                    \"Azja\",\n" +
"                    \"Afryka\",\n" +
"                    \"Ameryka Południowa\",\n" +
"                    \"Ameryka Północna\"\n" +
"                ]\n" +
"            },\n" +
"            {\n" +
"                \"text\": \"Jak ma na nazwisko twórca Linuksa?\",\n" +
"                \"options\": [\n" +
"                    \"Gates\",\n" +
"                    \"Torvalds\",\n" +
"                    \"Jobs\",\n" +
"                    \"Kernighan\",\n" +
"                    \"Pike\"\n" +
"                ]\n" +
"            }\n" +
"        ],\n" +
"        \"correctAnswers\": [\n" +
"            [ true, true, true, false, true ],\n" +
"            [ true, false, false, false, false ],\n" +
"            [ false, true, false, false, false ]\n" +
"        ]\n" +
"    }";
    
    public static final String SINGLE_CHOICE_TEST_JSON = "{\n" +
"        \"id\": \"6fc51d84-b28b-4390-9c09-74ec4107ed00\",\n" +
"        \"name\": \"Przykładowy egzamin 2 (test jednokrotnego wyboru)\",\n" +
"        \"type\": \"single\",\n" +
"        \"owner\": \"anna\",\n" +
"        \"groups\": [ \"io\", \"sk\" ],\n" +
"        \"timeLimit\": 10,\n" +
"        \"startTime\": 1460583976,\n" +
"        \"endTime\": 1460603976,\n" +
"        \"resultsAvailable\": true,\n" +
"        \"questions\": [\n" +
"            {\n" +
"                \"text\": \"Który z typów danych _nie_ występuje w formacie JSON?\",\n" +
"                \"options\": [\n" +
"                    \"Liczbowy\", \"Napisowy\", \"Boolowski\", \"Wskaźnikowy\", \"Tablicowy\"\n" +
"                ]\n" +
"            },\n" +
"            {\n" +
"                \"text\": \"Na jakim kontynencie leży Polska?\",\n" +
"                \"options\": [\n" +
"                    \"Europa\",\n" +
"                    \"Azja\",\n" +
"                    \"Afryka\",\n" +
"                    \"Ameryka Południowa\",\n" +
"                    \"Ameryka Północna\"\n" +
"                ]\n" +
"            },\n" +
"            {\n" +
"                \"text\": \"Jak ma na nazwisko twórca Linuksa?\",\n" +
"                \"options\": [\n" +
"                    \"Gates\",\n" +
"                    \"Torvalds\",\n" +
"                    \"Jobs\",\n" +
"                    \"Kernighan\",\n" +
"                    \"Pike\"\n" +
"                ]\n" +
"            }\n" +
"        ],\n" +
"        \"correctAnswers\": [ 3, 0, 1 ]\n" +
"    }";
    public static final String TEST_HEADERS_JSON = "[{\"id\":\"c10ae7c7-3b1d-48d7-b949-e0165b3f3822\",\"name\":\"Przykładowy egzamin (test wielokrotnego wyboru)\",\"type\":\"multi\",\"owner\":\"anna\",\"groups\":[\"bd\",\"sk\"],\"timeLimit\":10,\"startTime\":1460583976,\"endTime\":1483225200,\"resultsAvailable\":false},{\"id\":\"6fc51d84-b28b-4390-9c09-74ec4107ed00\",\"name\":\"Przykładowy egzamin 2 (test jednokrotnego wyboru)\",\"type\":\"single\",\"owner\":\"anna\",\"groups\":[\"io\",\"sk\"],\"timeLimit\":10,\"startTime\":1460583976,\"endTime\":1460603976,\"resultsAvailable\":true}]";
}
