package cat.aoc.client_pci.samples.serveis.vo.local;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.serveis.padro.PeticionDatosConvivientes;
import generated.serveis.padro.PeticionDatosTitular;

import java.util.Properties;

public class PeticionBuilderPadro extends PeticionBuilderFromProperties<OperacioPadro> {

    public PeticionBuilderPadro(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioPadro operacio) {
        switch (operacio) {
            case CONVIVENTS:
                return new Object[]{
                        buildPeticionDatosConvivientes()
                };
            case TITULAR:
                return new Object[]{
                        buildPeticionDatosTitular()
                };
            case RESIDENT:
            case MUNICIPI_RESIDENCIA:
            case RESIDENT_MUNICIPI:
            case NUMERO_CONVIVENTS:
            case COMPROVACIO_CONVIVENTS:
            case TITULAR_PROPI:
            case CONVIVENTS_PROPI:
            case TITULAR_PDF:
            case CONVIVENTS_PDF:
            case TITULAR_IDESCAT:
            case CERCA_TITULAR:
            default:
                return new Object[]{};
        }
    }

    private PeticionDatosConvivientes buildPeticionDatosConvivientes() {
        PeticionDatosConvivientes peticion = new PeticionDatosConvivientes();
        peticion.setNumExpediente("prova");
        peticion.setTipoDocumentacion(1);
        peticion.setDocumentacion("12345678Z");
        peticion.setCodigoProvincia("08");
        peticion.setCodigoMunicipio("001");
        peticion.setIdescat(0);
        return peticion;
    }

    private PeticionDatosTitular buildPeticionDatosTitular() {
        PeticionDatosTitular peticion = new PeticionDatosTitular();
        peticion.setNumExpediente("prova");
        peticion.setTipoDocumentacion(1);
        peticion.setDocumentacion("12345678Z");
        peticion.setCodigoProvincia("08");
        peticion.setCodigoMunicipio("001");
        peticion.setIdescat(0);
        return peticion;
    }

}
