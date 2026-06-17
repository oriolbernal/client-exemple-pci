package cat.aoc.client_pci.api;

import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.api.model.Cluster;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.api.soap.KeystoreProperties;
import cat.aoc.client_pci.api.soap.LoggerInterceptor;
import cat.aoc.client_pci.api.soap.SignatureInterceptor;
import cat.aoc.client_pci.api.soap.SoapMtomClient;
import generated.pci.peticion.Peticion;
import generated.pci.peticion.Procesa;
import generated.pci.respuesta.ProcesaResponse;
import generated.pci.respuesta.Respuesta;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * Client SOAP genèric per consumir els serveis de la PCI (Plataforma de
 * Col·laboració Interadministrativa) de l'AOC.
 *
 * <p>Encapsula la construcció del client de Spring WS, la signatura de les
 * peticions (WS-Security / XMLDSIG) i, opcionalment, l'enviament amb MTOM.
 * Normalment s'obté a través de {@link cat.aoc.client_pci.api.clients.Serveis}.</p>
 */
@Slf4j
public class ClientPCI {
    private static final String[] PACKAGES = {
            "generated.pci.peticion",
            "generated.pci.respuesta",
    };

    private static String[] processPackages(String... externalPackages) {
        return Stream.concat(Arrays.stream(PACKAGES), Arrays.stream(externalPackages))
                .toArray(String[]::new);
    }

    private final Entorn entorn;
    private final Cluster cluster;
    private final Frontal frontal;
    private final String[] externalPackages;
    private final Properties keystoreProperties;
    private final boolean useMtom;
    private final SoapMtomClient<Procesa, ProcesaResponse> client;

    public ClientPCI(Entorn entorn, Cluster cluster, Frontal frontal, String[] externalPackages, Properties keystoreProperties) {
        this(entorn, cluster, frontal, externalPackages, keystoreProperties, false);
    }

    public ClientPCI(Entorn entorn, Cluster cluster, Frontal frontal, String[] externalPackages, Properties keystoreProperties, boolean useMtom) {
        this.entorn = entorn;
        this.cluster = cluster;
        this.frontal = frontal;
        this.externalPackages = externalPackages;
        this.keystoreProperties = keystoreProperties;
        this.useMtom = useMtom;
        client = new SoapMtomClient<>(useMtom, processPackages(externalPackages));
        configureInterceptors(keystoreProperties); // auth and log
        if(useMtom) {
            configureMtom(keystoreProperties);
        }
    }

    public void configureMtom(Properties keystore) {
        try {
            String p12FilePath = keystore.getProperty(KeystoreProperties.FILE);
            String p12Password = keystore.getProperty(KeystoreProperties.PASSWORD);
            CloseableHttpClient httpClient = createHttpClient(p12FilePath, p12Password);
            HttpComponentsMessageSender messageSender = new HttpComponentsMessageSender();
            messageSender.setHttpClient(httpClient);
            client.getWebServiceTemplate().setMessageSender(messageSender);
        } catch (Exception e) {
            log.error("No s'ha pogut configurar MTOM", e);
        }
    }

    public void configureInterceptors(Properties keystore) {
        // Si es fa servir mtom, no fa falta signar la capçalera: SignatureInterceptor
        // Forcem la signatura sempre per garantir la integritat
        // Si volguéssim millorar el rendiment podríem tunejar aquests interceptors per fer-los servir només quan faci falta
        try {
            client.setInterceptors(new ClientInterceptor[]{
                    new SignatureInterceptor(keystore),
                    new LoggerInterceptor(client.getUnmarshaller())
            });
        } catch (ClientException e) {
            log.error("No s'han pogut configurar els interceptors", e);
        }
    }

    private CloseableHttpClient createHttpClient(String p12FilePath, String p12Password) throws Exception {
        SSLContext sslContext = SSLContextBuilder.create()
                .loadKeyMaterial(new File(p12FilePath), p12Password.toCharArray(), p12Password.toCharArray())
                .build();
        return HttpClients.custom()
                .setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext))
                .build();
    }

    /**
     * Envia una petició signada al servei i en retorna la resposta.
     *
     * @param peticion petició construïda amb un {@code PeticionBuilder}
     * @return la resposta del servei
     * @throws ClientException si es produeix un error en construir l'endpoint o en l'enviament
     */
    public Respuesta send(Peticion peticion) throws ClientException {
        Procesa procesa = new Procesa();
        procesa.setPeticion(peticion);
        String endpoint = getEndpoint();
        ProcesaResponse response = client.send(endpoint, procesa);
        return response.getRespuesta();
    }

    public String getEndpoint() throws ClientException {
        return entorn.getEndpoint(cluster) + "/siri-proxy/services/" + frontal.getName();
    }

}
