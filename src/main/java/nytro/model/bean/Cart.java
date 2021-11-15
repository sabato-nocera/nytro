package nytro.model.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class Cart {
    private List<VideogiocoBean> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(VideogiocoBean item) {
        items.add(item);                                    //Non verifico se il prodotto inserito esiste gia' all'interno del carrello
    }

    public void deleteItem(VideogiocoBean item) {
        items.remove(item);
    }

    public void deleteAll() {
        items.clear();
    }

    public boolean contains(VideogiocoBean item) {
        for (VideogiocoBean x : items)
            if (x.getCodice() == item.getCodice())
                return true;
        return false;
    }

}
