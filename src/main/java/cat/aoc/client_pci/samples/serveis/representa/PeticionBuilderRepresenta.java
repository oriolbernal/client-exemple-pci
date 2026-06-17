package cat.aoc.client_pci.samples.serveis.representa;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;

import java.util.Properties;

import static cat.aoc.client_pci.samples.serveis.representa.PeticionBuilderRepresentaConsultarRepresentacio.buildConsultarRepresentacio;

public class PeticionBuilderRepresenta extends PeticionBuilderFromProperties<OperacioRepresenta> {

    public PeticionBuilderRepresenta(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioRepresenta operacio) {
        return new Object[]{getDatoEspecifico(operacio)};
    }

    private Object getDatoEspecifico(OperacioRepresenta operacio) {
        switch (operacio) {
            case CONSULTA_REPRESENTACIO:
                return buildConsultarRepresentacio();
            case CONSULTA_REPRESENTACIONS:
            case VALIDACIO:
            case ALTA:
            case MODIFICACIO:
            case CONSULTA_CATALEG:
            case CONSULTA_FAMILIES:
            case CONSULTA_FAMILIA:
            case CONSULTA_TRAMITS:
            case CONSULTA_ADMINISTRACIO:
            case CONSULTA_ADMINISTRACIONS:
            case DESCARREGA:
                return null;
            default:
                return null;
        }
    }

}
