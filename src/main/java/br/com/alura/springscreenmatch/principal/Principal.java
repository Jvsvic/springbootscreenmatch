package br.com.alura.springscreenmatch.principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import br.com.alura.springscreenmatch.exercicios.Multiplicacao;
import br.com.alura.springscreenmatch.model.DadosEpisodios;
import br.com.alura.springscreenmatch.model.DadosSerie;
import br.com.alura.springscreenmatch.model.DadosTemporada;
import br.com.alura.springscreenmatch.model.Episodio;
import br.com.alura.springscreenmatch.services.ConsumoApi;
import br.com.alura.springscreenmatch.services.ConverteDados;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";
    

    public void exibeMenu(){
        //buscar series
        System.out.println("Digite o nome da série: ");
        var nomeSerie = leitura.nextLine();
        var json1 = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json1, DadosSerie.class);
        System.out.println(dados);


        //temporadas
        List<DadosTemporada> temporadas = new ArrayList<>();
        
            for(int i = 1; i<=dados.totalTemporadas(); i++) {
                    var jsonTemporada = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
                    DadosTemporada dadosTemporada = conversor.obterDados(jsonTemporada, DadosTemporada.class);
                    temporadas.add(dadosTemporada);

            }
            temporadas.forEach(System.out::println);

            /*for(int i = 0; i < dados.totalTemporadas(); i++){
                List<DadosEpisodios> episodiosTemporada = temporadas.get(i).episodios();
                for (int j = 0; j < episodiosTemporada.size(); j++) {
                    System.out.println(episodiosTemporada.get(j));
                }
            }*/

            temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
            
            List<DadosEpisodios> dadosEp = temporadas.stream()
            .flatMap(t -> t.episodios().stream())
            .collect(Collectors.toList());
            
            /*System.out.println("\nTop 10 episódios: ");
            dadosEp.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Primeiro filtro N.A"))
                .sorted(Comparator.comparing(
                (DadosEpisodios e) -> Double.parseDouble(e.avaliacao())).reversed())
                .peek(e -> System.out.println("Ordenação" + e))
                .limit(10)
                .peek(e -> System.out.println("Limite" + e))
                .map(e -> e.titulo().toUpperCase())
                .peek(e -> System.out.println("Maisculas" + e))
                .forEach(System.out::println);*/
                

            List<Episodio> episodios = temporadas.stream()
            .flatMap(t -> t.episodios().stream()
            .map(d -> new Episodio(t.numero(), d)))
            .collect(Collectors.toList());
            
            episodios.forEach(System.out::println);

            System.out.println("Digite o titulo que deseja buscar: ");
            var trechoTitulo = leitura.nextLine();
            Optional<Episodio> episodioBuscado = episodios.stream()
            .filter(e -> e.getTitulo().toUpperCase().contains(trechoTitulo.toUpperCase()))
            .findFirst();
            if (episodioBuscado.isPresent()) {
                System.out.println("Episódio encontrado!");
                System.out.println("Temporada: " + episodioBuscado.get().getTemporada());
            } else {
                System.out.println("Não encontrei... :( ");
            }
             
            /*System.out.println("A partir de que ano você deseja ver os episódios? ");
            var ano = leitura.nextInt();
            leitura.nextLine();
            

            LocalDate dataBusca = LocalDate.of(ano, 1, 1);
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            episodios.stream()
            .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
            .forEach(e -> System.out.println(
                "Temporada: " + e.getTemporada() + " Episódio: " + e.getTitulo() + " Data: " + e.getDataLancamento().format(formatador)
            ));*/
            
            


    }

    
    
}
