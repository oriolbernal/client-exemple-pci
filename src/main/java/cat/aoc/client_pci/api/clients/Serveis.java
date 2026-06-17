package cat.aoc.client_pci.api.clients;

import cat.aoc.client_pci.api.ClientPCI;
import cat.aoc.client_pci.api.clients.proxy.ProxyClientCadastre;
import cat.aoc.client_pci.api.clients.proxy.ProxyClientPadro;
import cat.aoc.client_pci.api.model.Cluster;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.utils.PropertiesReader;
import lombok.Getter;

import java.io.IOException;
import java.util.Properties;

/**
 * Catàleg dels serveis de la PCI disponibles i punt d'entrada per obtenir-ne el client.
 *
 * <p>Cada constant associa el servei amb el seu clúster i amb els paquets JAXB
 * generats a partir dels XSD del servei. Useu {@link #getClient} per obtenir un
 * {@link ClientPCI} configurat per a l'entorn i el frontal desitjats.</p>
 */
@Getter
public enum Serveis {
    BOE(Cluster.APP, "generated.serveis.boe"),
    EACAT(Cluster.APP, "generated.serveis.eacat"),
    ENOTUM(Cluster.NT, "generated.serveis.enotum"),
    ETAULER(Cluster.APP, "generated.serveis.etauler"),
    OVER(Cluster.APP, "generated.serveis.over"),
    REPRESENTA(Cluster.APP, "generated.serveis.representa"),
    SCT_DEV(Cluster.APP, "generated.serveis.sct_dev"),
    SIR2(Cluster.APP, "generated.serveis.sir2"),

    // VO - ESTAT
    CADASTRE(Cluster.IOP, "generated.serveis.cadastre.certificacio", "generated.serveis.cadastre.dades", "generated.serveis.cadastre.grafica", "generated.serveis.cadastre.csv"),
    DEPENDENCIA(Cluster.IOP, "generated.serveis.dependencia"),
    DGP(Cluster.IOP, "generated.serveis.dgp"),
    DGT(Cluster.IOP, "generated.serveis.dgt"),
    ESTRANGERIA(Cluster.IOP, "generated.serveis.estrangeria"),
    IGAE(Cluster.IOP, "generated.serveis.igae"),
    INSS(Cluster.IOP, "generated.serveis.inss", "generated.serveis.inss_historic"),
    MEPSYD(Cluster.IOP, "generated.serveis.mepsyd"),
    NOTARIS(Cluster.IOP, "generated.serveis.notaris"),
    REGISTRE_CIVIL(Cluster.IOP, "generated.serveis.registre_civil"),
    SEPE(Cluster.IOP, "generated.serveis.sepe"),

    // VO - GENERALITAT
    TFN(Cluster.IOP, "generated.serveis.tfn"),
    TFM(Cluster.IOP, "generated.serveis.tfm"),
    SOC(Cluster.IOP, "generated.serveis.soc"),
    RCA(Cluster.IOP, "generated.serveis.rca"),
    GRAU_DISCAPACITAT(Cluster.IOP, "generated.serveis.grau_discapacitat"),
    ATC(Cluster.IOP, "generated.serveis.atc"),
    REGISTRE_ENTITATS(Cluster.IOP, "generated.serveis.registre_entitats"),
    RGC(Cluster.IOP, "generated.serveis.rgc"),
    RPE(Cluster.IOP, "generated.serveis.rpe"),
    PADRO_HISTORIC(Cluster.IOP, "generated.serveis.padro_historic"),

    // VO - LOCAL
    PADRO(Cluster.IOP, "generated.serveis.padro", "generated.serveis.padro.empadronamiento");

    private final Cluster cluster;
    private final String[] packages;

    Serveis(Cluster cluster, String... packages) {
        this.cluster = cluster;
        this.packages = packages;
    }

    /**
     * Construeix un client configurat per a aquest servei.
     *
     * @param entorn       entorn de destinació (DEV, PRE o PRO)
     * @param frontal      frontal del servei (síncron o asíncron)
     * @param keystorePath ruta al fitxer {@code keystore.properties} amb la configuració del certificat
     * @return el client llest per enviar peticions
     * @throws IOException si no es pot llegir la configuració del magatzem de claus
     */
    public ClientPCI getClient(Entorn entorn, Frontal frontal, String keystorePath) throws IOException {
        Properties properties = PropertiesReader.load(keystorePath);
        switch (this) {
            case PADRO:
                return new ProxyClientPadro(entorn, frontal, properties);
            case CADASTRE:
                return new ProxyClientCadastre(entorn, frontal, properties);
            case ENOTUM:
                return new ClientPCI(entorn, this.cluster, frontal, this.packages, properties, true);
            default:
                return new ClientPCI(entorn, this.cluster, frontal, this.packages, properties);
        }
    }

}
