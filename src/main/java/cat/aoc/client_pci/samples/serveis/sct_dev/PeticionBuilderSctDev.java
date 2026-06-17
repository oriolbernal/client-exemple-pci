package cat.aoc.client_pci.samples.serveis.sct_dev;

import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.pci.peticion.Peticion;
import generated.pci.peticion.Titular;

import java.util.Properties;

public class PeticionBuilderSctDev extends PeticionBuilderFromProperties<OperacioSctDev> {

    public PeticionBuilderSctDev(Properties properties) {
        super(properties);
    }

    @Override
    public Peticion build(OperacioSctDev operacio, Finalitat finalitat) {
        Peticion peticion = super.build(operacio, finalitat);
        peticion.getSolicitudes().getSolicitudTransmision().get(0).getDatosGenericos().setTitular(getTitular());
        return peticion;
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioSctDev operacio) {
        return new Object[]{getDatoEspecifico(operacio)};
    }

    private Object getDatoEspecifico(OperacioSctDev operacio) {
        switch (operacio) {
            case CONSULTA_CREDENCIALS:
            case CONSULTA_CREDENCIALS_LOT:
                return null;
            default:
                return null;
        }
    }

    private static Titular getTitular() {
        Titular titular = new Titular();
        titular.setTipoDocumentacion("NIF");
        titular.setDocumentacion("00000001R");
        return titular;
    }

}
