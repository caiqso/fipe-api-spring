package br.com.challenge.TabelaFipe.principal;

import br.com.challenge.TabelaFipe.model.Dados;
import br.com.challenge.TabelaFipe.model.Veiculo;
import br.com.challenge.TabelaFipe.service.FipeService;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    private final FipeService service;

    public Principal(FipeService service) {
        this.service = service;
    }

    private Scanner leitura = new Scanner(System.in);

    public void exibeMenu() {

        System.out.println("""
                ====================================
                        CONSULTA TABELA FIPE
                ====================================
                
                Escolha o tipo de veículo:
                
                1 - Carros
                2 - Motos
                3 - Caminhões
                """);

        String tipo = switch (leitura.nextLine()) {
            case "1" -> "carros";
            case "2" -> "motos";
            case "3" -> "caminhoes";
            default -> throw new RuntimeException("Opção inválida");
        };

        List<Dados> marcas = service.buscarMarcas(tipo);

        System.out.println("\n🔎 Buscando marcas...");

        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Digite o código da marca:");
        String codigoMarca = leitura.nextLine();

        List<Dados> modelos = service.buscarModelos(tipo, codigoMarca);

        System.out.println("\nModelos:");
        modelos.forEach(System.out::println);

        System.out.println("\nDigite parte do nome do modelo:");
        String filtro = leitura.nextLine();

        List<Dados> filtrados = modelos.stream()
                .filter(m -> m.nome().toLowerCase().contains(filtro.toLowerCase()))
                .toList();

        System.out.println("\n📋 Modelos encontrados:");
        filtrados.forEach(System.out::println);

        System.out.println("\nDigite o código do modelo:");
        String codigoModelo = leitura.nextLine();

        List<Dados> anos = service.buscarAnos(tipo, codigoMarca, codigoModelo);

        List<Veiculo> veiculos = anos.stream()
                .map(a -> service.buscarVeiculo(tipo, codigoMarca, codigoModelo, a.codigo()))
                .toList();

        System.out.println("\n📊 Carregando dados por ano...");

        System.out.println("\nVeículos por ano:");
        veiculos.forEach(System.out::println);
    }

}
