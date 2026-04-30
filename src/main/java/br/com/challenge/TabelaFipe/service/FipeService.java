package br.com.challenge.TabelaFipe.service;

import br.com.challenge.TabelaFipe.client.FipeClient;
import br.com.challenge.TabelaFipe.model.Dados;
import br.com.challenge.TabelaFipe.model.Modelos;
import br.com.challenge.TabelaFipe.model.Veiculo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FipeService {

    private final FipeClient client;
    private final ConverteDados conversor;

    public FipeService(FipeClient client, ConverteDados conversor) {
        this.client = client;
        this.conversor = conversor;
    }

    public List<Dados> buscarMarcas(String tipo) {
        String json = client.get("/" + tipo + "/marcas");
        return conversor.obterLista(json, Dados.class);
    }

    public List<Dados> buscarModelos(String tipo, String codigoMarca) {
        String json = client.get("/" + tipo + "/marcas/" + codigoMarca + "/modelos");
        return conversor.obterDados(json, Modelos.class).modelos();
    }

    public List<Dados> buscarAnos(String tipo, String codigoMarca, String codigoModelo) {
        String json = client.get("/" + tipo + "/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos");
        return conversor.obterLista(json, Dados.class);
    }

    public Veiculo buscarVeiculo(String tipo, String codigoMarca, String codigoModelo, String codigoAno) {
        String json = client.get("/" + tipo + "/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos/" + codigoAno);
        return conversor.obterDados(json, Veiculo.class);
    }
}