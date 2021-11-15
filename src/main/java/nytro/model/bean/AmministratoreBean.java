package nytro.model.bean;

import lombok.*;

import java.io.Serializable;

@Data
public class AmministratoreBean extends AccountBean implements Serializable {
    private String nome;
    private String cognome;

    public AmministratoreBean(AccountBean bean) {
        this.setUsername(bean.getUsername());
        this.setPassword(bean.getPassword());
        this.setEmail(bean.getEmail());
        this.setEmailRecupero(bean.getEmailRecupero());
        this.setCellulare(bean.getCellulare());
        this.setData(bean.getData());
        this.setOra(bean.getOra());
        this.setIp(bean.getIp());
        this.setRuolo(bean.getRuolo());
        this.setImgProfilo(bean.getImgProfilo());
    }

}
