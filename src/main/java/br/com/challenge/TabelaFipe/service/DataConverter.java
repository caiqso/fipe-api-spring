package br.com.challenge.TabelaFipe.service;

import java.util.List;

public interface DataConverter {
    <T> T obterDados(String json, Class<T> classe);

    <T> List<T> obterLista(String json, Class<T> classe);
}
