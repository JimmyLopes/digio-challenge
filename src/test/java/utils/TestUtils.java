package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;

public final class TestUtils {

    /**
     * Objeto que mapeia os valores de um input stream para uma classe
     */
    private final static ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    /**
     * Construtor padrão privado para a classe não ser instanciada indevidamente.
     */
    private TestUtils() {
    }

    /**
     * Método que lé os valores de um arquivo e mapeia para uma classe destino
     *
     * @param mockFolder  o endereço da pasta
     * @param fileName    o nome do arquivo
     * @param targetClazz a classe de destino
     * @param <T>         a classe a ser mapeada
     * @return a classe destino mapeada
     */
    public static <T> T getMock(String mockFolder, String fileName, Class<T> targetClazz) {
        String filePath = mockFolder + "/" + fileName;
        try (InputStream inputStream = TestUtils.class.getResourceAsStream(filePath)) {
            return mapper.readValue(inputStream, targetClazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Um erro ocorreu ao carregar o JSON de teste: " + filePath);
        }
    }
}
