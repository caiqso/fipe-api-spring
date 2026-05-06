package br.com.challenge.TabelaFipe.controller;

import br.com.challenge.TabelaFipe.exception.ApiException;
import br.com.challenge.TabelaFipe.model.DataItem;
import br.com.challenge.TabelaFipe.model.Vehicle;
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

    @GetMapping("/{tipo}/marcas")
    public List<DataItem> buscarMarcas(@PathVariable String tipo) {
        validarTipo(tipo);
        return service.buscarMarcas(tipo);
    }

    @GetMapping("/{tipo}/marcas/{marca}/modelos")
    public List<DataItem> buscarModelos(
            @PathVariable String tipo,
            @PathVariable String marca) {

        validarTipo(tipo);

        List<DataItem> modelos = service.buscarModelos(tipo, marca);

        if (modelos.isEmpty()) {
            throw new ApiException("Nenhum modelo encontrado para a marca informada");
        }

        return modelos;
    }

    @GetMapping("/{tipo}/marcas/{marca}/modelos/{modelo}/veiculos")
    public List<Vehicle> buscarVeiculos(
            @PathVariable String tipo,
            @PathVariable String marca,
            @PathVariable String modelo) {

        validarTipo(tipo);

        List<DataItem> anos = service.buscarAnos(tipo, marca, modelo);

        if (anos.isEmpty()) {
            throw new ApiException("Nenhum ano encontrado para o modelo selecionado");
        }

        return anos.stream()
                .map(a -> service.buscarVeiculo(tipo, marca, modelo, a.codigo()))
                .toList();
    }

    private void validarTipo(String tipo) {
        if (!tipo.matches("carros|motos|caminhoes")) {
            throw new ApiException("Tipo inválido. Use: carros, motos ou caminhoes");
        }
    }
}