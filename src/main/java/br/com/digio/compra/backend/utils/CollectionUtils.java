package br.com.digio.compra.backend.utils;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {

    /**
     * Construtor padrão privado para a classe não ser instanciada indevidamente.
     */
    private CollectionUtils() {
    }

    /**
     * Método para converter um Iterable de T em uma lista de T
     *
     * @param itr o iterable de T
     * @param <T> a classe alvo
     * @return a lista da classe alvo
     */
    public static <T> List<T> getListFromIterable(Iterable<T> itr) {
        List<T> cltn = new ArrayList<>();
        itr.forEach(cltn::add);

        return cltn;
    }
}
