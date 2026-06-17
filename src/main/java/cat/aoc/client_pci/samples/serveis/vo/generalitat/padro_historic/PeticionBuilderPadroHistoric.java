package cat.aoc.client_pci.samples.serveis.vo.generalitat.padro_historic;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.serveis.padro_historic.DocumentacionTitular;
import generated.serveis.padro_historic.PeticionDatosConvivientesHistorico;
import generated.serveis.padro_historic.PeticionDatosTitularHistorico;

import java.util.Properties;

public class PeticionBuilderPadroHistoric extends PeticionBuilderFromProperties<OperacioPadroHistoric> {
    public PeticionBuilderPadroHistoric(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioPadroHistoric operacio) {
        switch (operacio) {
            case TITULAR_HISTORIC:
                return new Object[]{
                        buildPeticionDatosTitularHistorico()
                };
            case CONVIVENTS_HISTORIC:
                return new Object[]{
                        buildPeticionDatosConvivientesHistorico()
                };
            default:
                return null;
        }
    }

    private PeticionDatosTitularHistorico buildPeticionDatosTitularHistorico() {
        PeticionDatosTitularHistorico peticion = new PeticionDatosTitularHistorico();
        peticion.setNumExpediente("test");
        DocumentacionTitular titular = new DocumentacionTitular();
        titular.setTipoDocumentacion(1); // DNI
        titular.setDocumentacion("10101010P");
        peticion.setDocumentacionTitular(titular);
        return peticion;
    }

    private PeticionDatosConvivientesHistorico buildPeticionDatosConvivientesHistorico() {
        PeticionDatosConvivientesHistorico peticion = new PeticionDatosConvivientesHistorico();
        peticion.setNumExpediente("test");
        DocumentacionTitular titular = new DocumentacionTitular();
        titular.setTipoDocumentacion(1); // DNI
        titular.setDocumentacion("10101010P");
        peticion.setDocumentacionTitular(titular);
        return peticion;
    }

}
