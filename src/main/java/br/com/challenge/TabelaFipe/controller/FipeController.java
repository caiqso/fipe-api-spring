package br.com.challenge.TabelaFipe.controller;

import br.com.challenge.TabelaFipe.exception.ApiException;
import br.com.challenge.TabelaFipe.model.Dados;
import br.com.challenge.TabelaFipe.model.Veiculo;
import br.com.challenge.TabelaFipe.service.FipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fipe")
public class FipeController {

    private final FipeService service;

    public FipeController(FipeService service) {
        this.service = service;
    }

    @GetMapping("/marcas")
    public List<Dados> buscarMarcas(@RequestParam String tipo) {
        if (!tipo.matches("carros|motos|caminhoes")) {
            throw new ApiException("Tipo inválido. Use: carros, motos ou caminhoes");
        }
        return service.buscarMarcas(tipo);
    }

    @GetMapping("/modelos")
    public List<Dados> buscarModelos(
            @RequestParam String tipo,
            @RequestParam String marca) {

        if (!tipo.matches("carros|motos|caminhoes")) {
            throw new ApiException("Tipo inválido. Use: carros, motos ou caminhoes");
        }

        List<Dados> modelos = service.buscarModelos(tipo, marca);

        if (modelos.isEmpty()) {
            throw new ApiException("Nenhum modelo encontrado para a marca informada");
        }

        return modelos;
    }

    @GetMapping("/veiculos")
    public List<Veiculo> buscarVeiculos(
            @RequestParam String tipo,
            @RequestParam String marca,
            @RequestParam String modelo) {

        if (!tipo.matches("carros|motos|caminhoes")) {
            throw new ApiException("Tipo inválido");
        }

        List<Dados> anos = service.buscarAnos(tipo, marca, modelo);

        if (anos.isEmpty()) {
            throw new ApiException("Nenhum ano encontrado para o modelo selecionado");
        }

        return anos.stream()
                .map(a -> service.buscarVeiculo(tipo, marca, modelo, a.codigo()))
                .toList();
    }
}