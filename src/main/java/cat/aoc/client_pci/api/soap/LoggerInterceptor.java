package cat.aoc.client_pci.api.soap;

import cat.aoc.client_pci.api.exceptions.ClientException;
import generated.pci.peticion.Procesa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.oxm.Unmarshaller;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.transport.context.TransportContextHolder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class LoggerInterceptor implements ClientInterceptor {

    private final Unmarshaller unmarshaller;
    public LoggerInterceptor(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    @Override
    public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
        logSoapMessage("SENT INFO", () -> {
            try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
                logRequest(messageContext);
                messageContext.getRequest().writeTo(buffer);
                String payload = buffer.toString(StandardCharsets.UTF_8.name());
                log.info("Request:");
                log.info(payload);
            } catch (Exception e) {
                throw new ClientException("Error handling request", e);
            }
        });
        return true;
    }

    private void logRequest(MessageContext messageContext) throws IOException, URISyntaxException {
        String endpointAddress = TransportContextHolder.getTransportContext().getConnection().getUri().toString();
        log.info("Endpoint: " + endpointAddress);
        Procesa procesa = (Procesa) unmarshaller.unmarshal(messageContext.getRequest().getPayloadSource());
        log.info("ID: " + procesa.getPeticion().getAtributos().getIdPeticion());
        log.info("Timestamp: " + procesa.getPeticion().getAtributos().getTimeStamp());
        log.info("Producte: " + procesa.getPeticion().getAtributos().getCodigoProducto());
        log.info("Modalitat: " + procesa.getPeticion().getAtributos().getCodigoCertificado());
        log.info("Finalitat: " + procesa.getPeticion().getAtributos().getDatosAutorizacion().getFinalidad());
    }

    @Override
    public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
        logSoapMessage("RECEIVED INFO", () -> {
            try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
                messageContext.getResponse().writeTo(buffer);
                String payload = buffer.toString(StandardCharsets.UTF_8.name());
                log.info("Response:");
                log.info(payload);
            } catch (IOException e) {
                throw new ClientException("Error handling response", e);
            }
        });
        return true;
    }

    @Override
    public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
        logSoapMessage("RECEIVED FAULT", () -> {
            try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
                messageContext.getResponse().writeTo(buffer);
                String payload = buffer.toString(StandardCharsets.UTF_8.name());
                log.info("Fault:");
                log.info(payload);
            } catch (IOException e) {
                throw new ClientException("Error handling fault", e);
            }
        });
        return true;
    }

    @Override
    public void afterCompletion(MessageContext messageContext, Exception ex) throws WebServiceClientException {
        log.debug("Communication completed!");
    }

    private void logSoapMessage(String message, Runnable runnable) {
        log.info("#################################### " + message + " ####################################");
        try {
            runnable.run();
        } catch (WebServiceClientException e) {
            log.warn("No s'ha pogut registrar el missatge SOAP", e);
        }
        log.info("#######################################################################################");
    }

}
