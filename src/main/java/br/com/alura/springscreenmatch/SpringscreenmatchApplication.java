package br.com.alura.springscreenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.springscreenmatch.services.ConsumoApi;

@SpringBootApplication
public class SpringscreenmatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringscreenmatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var consumoApi = new ConsumoApi();
        var json = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
        //System.out.println(json);
        //json = consumoApi.obterDados("http://coffee.alexflipnote.dev/random.json");
        System.out.println(json);
    }
}
