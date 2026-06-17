package cat.aoc.client_pci.api.clients.proxy;

import cat.aoc.client_pci.api.ClientPCI;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.api.model.Cluster;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Frontal;
import generated.pci.peticion.Peticion;
import generated.pci.respuesta.Respuesta;

import java.util.Properties;

public class ProxyClientCadastre extends ClientPCI {
    private static final String[] CERTIFICACIO_PACKAGES = {"generated.serveis.cadastre.certificacio",};
    private static final String[] DADES_PACKAGES = {"generated.serveis.cadastre.dades",};
    private static final String[] GRAFICA_PACKAGES = {"generated.serveis.cadastre.grafica",};
    private static final String[] CSV_PACKAGES = {"generated.serveis.cadastre.csv",};

    private final ClientPCI clientCertificacio;
    private final ClientPCI clientDades;
    private final ClientPCI clientGrafica;
    private final ClientPCI clientCsv;

    public ProxyClientCadastre(Entorn entorn, Frontal frontal, Properties properties) {
        super(entorn, Cluster.IOP, frontal, new String[]{}, properties);
        this.clientCertificacio = new ClientPCI(entorn, Cluster.IOP, frontal, CERTIFICACIO_PACKAGES, properties);
        this.clientDades = new ClientPCI(entorn, Cluster.IOP, frontal, DADES_PACKAGES, properties);
        this.clientGrafica = new ClientPCI(entorn, Cluster.IOP, frontal, GRAFICA_PACKAGES, properties);
        this.clientCsv = new ClientPCI(entorn, Cluster.IOP, frontal, CSV_PACKAGES, properties);
    }

    @Override
    public Respuesta send(Peticion peticion) throws ClientException {
        String modalitat = peticion.getAtributos().getCodigoCertificado();
        switch (modalitat) {
            case "CERTIFICACIO_TITULARITAT":
                return clientCertificacio.send(peticion);
            case "DADES_CADASTRALS":
                return clientDades.send(peticion);
            case "DESCRIPTIVA_GRAFICA":
                return clientGrafica.send(peticion);
            case "DOCUMENT_CSV":
                return clientCsv.send(peticion);
            default:
                throw new ClientException("Modalitat no definida: " + modalitat);
        }
    }
}
