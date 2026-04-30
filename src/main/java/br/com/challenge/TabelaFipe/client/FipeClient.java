package br.com.challenge.TabelaFipe.client;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class FipeClient {

    private final HttpClient client = HttpClient.newHttpClient();
    private final String BASE_URL = "https://parallelum.com.br/fipe/api/v1";

    public String get(String endpoint) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + endpoint))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new RuntimeException("Erro na API: " + response.statusCode());
            }

            return response.body();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao consumir API", e);
        }
    }

//    public String obterDados(String endereco) {
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(endereco))
//                .build();
//        HttpResponse<String> response = null;
//        try {
//            response = client
//                    .send(request, HttpResponse.BodyHandlers.ofString());
//        } catch (IOException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        String json = response.body();
//        return json;
//    }
}
