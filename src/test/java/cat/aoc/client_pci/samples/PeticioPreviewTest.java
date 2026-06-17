package cat.aoc.client_pci.samples;

import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.samples.serveis.enotum.OperacioEnotum;
import cat.aoc.client_pci.samples.serveis.enotum.PeticionBuilderEnotum;
import cat.aoc.client_pci.utils.PropertiesReader;
import generated.pci.peticion.Peticion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Exemple executable sense credencials: comprova que es pot construir i serialitzar
 * una petició PCI (e-NOTUM) sense necessitat de certificat ni de connectivitat.
 */
class PeticioPreviewTest {

    @Test
    @DisplayName("Genera l'XML d'una petició e-NOTUM sense enviar-la")
    void generaXmlSenseEnviar() throws IOException {
        Properties properties = PropertiesReader.loadFromClasspath("client.properties.example");
        Peticion peticion = new PeticionBuilderEnotum(properties).build(OperacioEnotum.CERCA, Finalitat.PROVES);

        String xml = PeticioPreview.toXml(Serveis.ENOTUM, peticion);

        assertNotNull(xml);
        assertTrue(xml.contains("Peticion"), "L'XML hauria de contenir l'element Peticion");
        assertTrue(xml.contains("ENOTUM"), "L'XML hauria de contenir el codi de producte ENOTUM");
        assertTrue(xml.contains(Finalitat.PROVES.name()), "L'XML hauria de contenir la finalitat");
    }

}
