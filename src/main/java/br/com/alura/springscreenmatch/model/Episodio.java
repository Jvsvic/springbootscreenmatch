package br.com.alura.springscreenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double avaliacao;
    private LocalDate dataLancamento;


    public Episodio(Integer numeroTemporada, DadosEpisodios dadosEpisodio) {
        try {
            this.temporada = numeroTemporada;
        } catch (Exception e) {
            this.avaliacao = 0.0;
        }
        try {
            this.dataLancamento = LocalDate.parse(dadosEpisodio.dataLancamento());
        } catch (DateTimeParseException ex) {
            this.dataLancamento = null;
        }




        this.temporada = numeroTemporada;
        this.titulo = dadosEpisodio.titulo();
        this.numeroEpisodio = dadosEpisodio.numeroEpisodio();
        
        
    
    }

    

    public Double getAvaliacao() {
        return avaliacao;
    }
    public LocalDate getDataLancamento() {
        return dataLancamento;
    }
    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }
    public Integer getTemporada() {
        return temporada;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }
    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }
    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }
    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "\nTemporada: " + temporada + "\nTitulo: " + titulo + "\nEp.Numero: " + numeroEpisodio + "\nAvaliação: " + avaliacao + "\ndataLancamento: " + dataLancamento;
    }
}