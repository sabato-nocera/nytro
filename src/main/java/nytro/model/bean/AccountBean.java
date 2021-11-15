package nytro.model.bean;

import lombok.*;

import java.io.InputStream;
import java.io.Serializable;

@Data
public class AccountBean implements Serializable {
    private String username;
    private String password;
    private String email;
    private String emailRecupero;
    private String cellulare;
    private String data;
    private String ora;
    private String ip;
    private int ruolo;
    private InputStream imgProfilo;
}
