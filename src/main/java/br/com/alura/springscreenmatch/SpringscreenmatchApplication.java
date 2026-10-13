package br.com.alura.springscreenmatch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.springscreenmatch.model.DadosEpisodios;
import br.com.alura.springscreenmatch.model.DadosSerie;
import br.com.alura.springscreenmatch.model.DadosTemporada;
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
        var json1 = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
        ConverteDados conversor = new ConverteDados();
        //buscar series
        DadosSerie dados = conversor.obterDados(json1, DadosSerie.class);
        System.out.println(dados);
        //buscar episodios
        var json = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=6585022c");
        DadosEpisodios dadosEpisodios = conversor.obterDados(json, DadosEpisodios.class);
        System.out.println(dadosEpisodios);

        //buscar temporadas
        List<DadosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            var jsonTemp = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=6585022c");
            DadosTemporada dadosTemporada = conversor.obterDados(jsonTemp, DadosTemporada.class);
            temporadas.add(dadosTemporada);
            System.out.println(dadosTemporada.numero());
            System.out.println("Temporada " + dadosTemporada.numero() + " tem " + dadosTemporada.episodios().size() + " episódios.");
        }

        

    }
}
