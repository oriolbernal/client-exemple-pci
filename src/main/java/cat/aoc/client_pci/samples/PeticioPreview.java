package cat.aoc.client_pci.samples;

import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.samples.serveis.enotum.OperacioEnotum;
import cat.aoc.client_pci.samples.serveis.enotum.PeticionBuilderEnotum;
import cat.aoc.client_pci.utils.PropertiesReader;
import generated.pci.peticion.Peticion;
import generated.pci.peticion.Procesa;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * Genera la representació XML d'una {@link Peticion} <strong>sense enviar-la</strong>.
 *
 * <p>És una eina didàctica pensada per als integradors: permet veure exactament
 * quin missatge es construeix abans d'estar donat d'alta als entorns de l'AOC,
 * sense necessitat de certificat ni de connectivitat. La signatura WS-Security
 * s'aplica de manera automàtica durant l'enviament real, però l'estructura de
 * negoci (la part que heu de construir) és la que es mostra aquí.</p>
 */
public final class PeticioPreview {

    private static final String PCI_PACKAGE = "generated.pci.peticion";

    private PeticioPreview() {
        // Classe utilitària: no instanciable.
    }

    /**
     * Genera l'XML de la petició per a un servei concret, afegint automàticament
     * els paquets JAXB específics del servei.
     *
     * @param servei   servei de la PCI al qual va dirigida la petició
     * @param peticion petició construïda amb un {@code PeticionBuilder}
     * @return l'XML formatat de la petició
     */
    public static String toXml(Serveis servei, Peticion peticion) {
        String[] packages = Stream.concat(Stream.of(PCI_PACKAGE), Arrays.stream(servei.getPackages()))
                .toArray(String[]::new);
        return toXml(peticion, packages);
    }

    /**
     * Genera l'XML de la petició a partir dels paquets JAXB indicats.
     *
     * @param peticion        petició construïda amb un {@code PeticionBuilder}
     * @param contextPackages paquets JAXB necessaris per serialitzar la petició
     * @return l'XML formatat de la petició
     */
    public static String toXml(Peticion peticion, String... contextPackages) {
        Procesa procesa = new Procesa();
        procesa.setPeticion(peticion);

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPaths(contextPackages);
        Map<String, Object> marshallerProperties = new HashMap<>();
        marshallerProperties.put(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setMarshallerProperties(marshallerProperties);

        StringWriter writer = new StringWriter();
        marshaller.marshal(procesa, new StreamResult(writer));
        return writer.toString();
    }

    /**
     * Exemple executable: imprimeix l'XML d'una petició de CERCA del servei e-NOTUM
     * fent servir les dades de {@code client.properties.example}.
     */
    public static void main(String[] args) throws IOException {
        Properties properties = PropertiesReader.loadFromClasspath("client.properties.example");
        Peticion peticion = new PeticionBuilderEnotum(properties).build(OperacioEnotum.CERCA, Finalitat.PROVES);
        System.out.println(toXml(Serveis.ENOTUM, peticion));
    }

}
