package cat.aoc.client_pci.api.soap;

/**
 * Claus de configuració del magatzem de claus (keystore) usat per signar les
 * peticions amb WS-Security / XMLDSIG.
 *
 * <p>Es corresponen amb les propietats del fitxer {@code keystore.properties} i
 * amb la configuració que espera {@code org.apache.wss4j.common.crypto.Merlin}.</p>
 */
public final class KeystoreProperties {

    /** Proveïdor criptogràfic (implementació de {@code Crypto}). */
    public static final String PROVIDER = "org.apache.ws.security.crypto.provider";
    /** Àlies del certificat dins del magatzem. */
    public static final String ALIAS = "org.apache.ws.security.crypto.merlin.keystore.alias";
    /** Format del magatzem (p. ex. {@code PKCS12} o {@code JKS}). */
    public static final String TYPE = "org.apache.ws.security.crypto.merlin.keystore.type";
    /** Contrasenya del magatzem/certificat. */
    public static final String PASSWORD = "org.apache.ws.security.crypto.merlin.keystore.password";
    /** Ruta al fitxer del certificat. */
    public static final String FILE = "org.apache.ws.security.crypto.merlin.keystore.file";

    private KeystoreProperties() {
        // Classe de constants: no instanciable.
    }

}
