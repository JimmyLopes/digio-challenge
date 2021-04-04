package br.com.digio.compra.backend.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class StringUtils {

    /**
     * Construtor padrão privado para a classe não ser instanciada indevidamente.
     */
    private StringUtils() {
    }

    /**
     * Método que procura a key especificada nas mensagens de validação e a retorna.
     * Caso não encontre, retorna a própria key.
     *
     * @param key corresponde a key procurada
     * @param params parâmetros da key
     * @return o valor da key com os parâmetros injetados
     */
    public static String buscarMensagemDeValidacao(String key, Object... params){
        try {
            return MessageFormat.format(ResourceBundle.getBundle("ValidationMessages", Locale.getDefault())
                    .getString(key), params);
        } catch (MissingResourceException e) {
            return key;
        }
    }

    /**
     * Método que converte o texto de booleano para sim ou não
     *
     * @param boleano texto de booleano
     * @return o texto convertido
     */
    public static String converterBooleanParaString(String boleano) {
        return boleano.equals("true") ? "Sim" : "Não";
    }

    /**
     * Método que converte um double para duas casas decimais e depois o transforma numa string no formato de moeda brasileiro
     *
     * @param valorCompra que será convertido
     * @return a string formatada no padrão de moeda brasileiro
     */
    public static String formataDoubleParaMoeda(Double valorCompra) {
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
                .format(BigDecimal.valueOf(valorCompra).setScale(2, RoundingMode.UNNECESSARY).doubleValue());
    }
}
