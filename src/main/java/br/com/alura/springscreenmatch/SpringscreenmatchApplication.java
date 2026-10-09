package br.com.alura.springscreenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.springscreenmatch.model.DadosSerie;
import br.com.alura.springscreenmatch.services.ConsumoApi;
import br.com.alura.springscreenmatch.services.ConverteDados;

@SpringBootApplication
public class SpringscreenmatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringscreenmatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var consumoApi = new ConsumoApi();
        var json = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
        ConverteDados conversor = new ConverteDados();
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);
    }
}
