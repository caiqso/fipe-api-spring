package br.com.challenge.TabelaFipe.service;

import br.com.challenge.TabelaFipe.client.FipeClient;
import br.com.challenge.TabelaFipe.model.DataItem;
import br.com.challenge.TabelaFipe.model.ModelsResponse;
import br.com.challenge.TabelaFipe.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FipeService {

    private final FipeClient client;
    private final DataConverterImpl conversor;

    public FipeService(FipeClient client, DataConverterImpl conversor) {
        this.client = client;
        this.conversor = conversor;
    }

    public List<DataItem> buscarMarcas(String tipo) {
        String json = client.get("/" + tipo + "/marcas");
        return conversor.obterLista(json, DataItem.class);
    }

    public List<DataItem> buscarModelos(String tipo, String codigoMarca) {
        String json = client.get("/" + tipo + "/marcas/" + codigoMarca + "/modelos");
        return conversor.obterDados(json, ModelsResponse.class).modelos();
    }

    public List<DataItem> buscarAnos(String tipo, String codigoMarca, String codigoModelo) {
        String json = client.get("/" + tipo + "/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos");
        return conversor.obterLista(json, DataItem.class);
    }

    public Vehicle buscarVeiculo(String tipo, String codigoMarca, String codigoModelo, String codigoAno) {
        String json = client.get("/" + tipo + "/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos/" + codigoAno);
        return conversor.obterDados(json, Vehicle.class);
    }
}