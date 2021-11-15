package nytro.model.bean;

import lombok.*;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

@Data
public class VideogiocoBean implements Serializable {
    private int codice;
    private String ISIN;
    private String dataRilascio;
    private String dataRimozione;
    private String titolo;
    private float votoMedio;
    private int PEGI;
    private InputStream img;
    private String trailer;
    private float prezzo;
    private int copieVendute;
    private List<String> console;
    private List<String> generi;
}
