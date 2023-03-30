package cat.aoc.client_pci.samples.serveis;

import cat.aoc.client_pci.api.clients.Serveis;
import cat.aoc.client_pci.api.exceptions.ClientException;
import cat.aoc.client_pci.api.model.Entorn;
import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.api.model.Frontal;
import cat.aoc.client_pci.samples.AbstractClientPCITest;
import cat.aoc.client_pci.samples.serveis.sct_dev.OperacioSctDev;
import cat.aoc.client_pci.samples.serveis.sct_dev.PeticionBuilderSctDev;
import generated.sct_dev.RespostaConsultaCredencials;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SctDevTest extends AbstractClientPCITest<OperacioSctDev> {

    public SctDevTest() throws IOException {
        super(Serveis.SCT_DEV.getClient(Entorn.PRE, Frontal.SINCRON),
                new PeticionBuilderSctDev(PROPERTIES));
    }

    @Test
    @DisplayName("CONSULTA_CREDENCIALS")
    void consultaCredencials() throws ClientException {
        RespostaConsultaCredencials resposta = this.send(OperacioSctDev.CONSULTA_CREDENCIALS, Finalitat.PROVES);
        assertNotNull(resposta);
    }

}