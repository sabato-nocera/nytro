package nytro.model.bean;

import lombok.*;

import java.io.Serializable;

@Data
public class RecensioneBean implements Serializable {
    private int numRecensione;
    private int codVideogioco;
    private String username;
    private String commento;
    private double voto;
}
