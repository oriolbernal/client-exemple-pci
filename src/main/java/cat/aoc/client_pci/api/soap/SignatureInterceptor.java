package cat.aoc.client_pci.api.soap;

import cat.aoc.client_pci.api.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.apache.wss4j.common.WSS4JConstants;
import org.apache.wss4j.common.crypto.Crypto;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean;

import java.util.Properties;

@Slf4j
public class SignatureInterceptor extends Wss4jSecurityInterceptor {
    private static final String SECURITY_ACTIONS = "Signature Timestamp";
    private static final String KEY_IDENTIFIER = "DirectReference";
    private static final String SIGNATURE_ALGORITHM = WSS4JConstants.RSA_SHA1;
    private static final String DIGEST_ALGORITHM = WSS4JConstants.SHA1;


    public SignatureInterceptor(Properties properties) throws ClientException {
        createSecurityInterceptor(properties);
    }

    public void createSecurityInterceptor(Properties properties) throws ClientException {
        setSecurementActions(SECURITY_ACTIONS);
        setSecurementUsername(properties.getProperty(KeystoreProperties.ALIAS));
        setSecurementPassword(properties.getProperty(KeystoreProperties.PASSWORD));
        setSecurementSignatureCrypto(createCrypto(properties));
        setSecurementSignatureKeyIdentifier(KEY_IDENTIFIER);
        setSecurementSignatureAlgorithm(SIGNATURE_ALGORITHM);
        setSecurementSignatureDigestAlgorithm(DIGEST_ALGORITHM);
        setSecurementTimeToLive(60);
        setTimestampPrecisionInMilliseconds(false);
        setTimestampStrict(false);
        setValidateResponse(false);
    }

    private Crypto createCrypto(Properties properties) throws ClientException {
        try {
            CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
            cryptoFactoryBean.setConfiguration(properties);
            cryptoFactoryBean.afterPropertiesSet();
            return cryptoFactoryBean.getObject();
        } catch (Exception e) {
            throw new ClientException("Could not load the keystore", e);
        }
    }

}
