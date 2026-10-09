package br.com.alura.springscreenmatch.services;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
