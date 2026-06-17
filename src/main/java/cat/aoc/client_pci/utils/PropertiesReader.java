package cat.aoc.client_pci.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public interface PropertiesReader {

    /**
     * Carrega un fitxer de propietats des del sistema de fitxers.
     *
     * @param propertiesPath ruta al fitxer
     * @return les propietats carregades
     * @throws IOException si el fitxer no existeix o no es pot llegir
     */
    static Properties load(String propertiesPath) throws IOException {
        try (InputStream input = new FileInputStream(propertiesPath)) {
            Properties properties = new Properties();
            properties.load(input);
            return properties;
        }
    }

    /**
     * Carrega un fitxer de propietats des del classpath (per exemple, un recurs
     * empaquetat dins de {@code src/main/resources}).
     *
     * @param resourceName nom del recurs al classpath
     * @return les propietats carregades
     * @throws IOException si el recurs no es troba o no es pot llegir
     */
    static Properties loadFromClasspath(String resourceName) throws IOException {
        try (InputStream input = PropertiesReader.class.getClassLoader().getResourceAsStream(resourceName)) {
            if (input == null) {
                throw new FileNotFoundException("Recurs no trobat al classpath: " + resourceName);
            }
            Properties properties = new Properties();
            properties.load(input);
            return properties;
        }
    }

}
