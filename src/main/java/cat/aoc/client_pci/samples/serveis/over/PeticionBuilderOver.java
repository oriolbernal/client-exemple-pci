package cat.aoc.client_pci.samples.serveis.over;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;

import java.util.Properties;

import static cat.aoc.client_pci.samples.serveis.over.PeticionBuilderOverDocumentacio.buildPeticioDocumentacioTramit;

public class PeticionBuilderOver extends PeticionBuilderFromProperties<OperacioOver> {

    public PeticionBuilderOver(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioOver operacio) {
        return new Object[]{ getDatoEspecifico(operacio) };
    }

    private Object getDatoEspecifico(OperacioOver operacio) {
        switch (operacio) {
            case OVER_DOCUMENTACIO:
                return buildPeticioDocumentacioTramit();
            case OVER_FORMULARI:
            case OVER_CONTEXT:
            case OVER_TRAMITACIO:
            case OVER_ACTUALITZACIO:
            case OVER_CONSULTA:
            case OVER_LLISTA_EXPEDIENTS:
            case OVER_CONSULTA_EXPEDIENT:
            case OVER_LLISTA_SERVEIS:
            case OVER_LLISTA_TRAMITS:
            case OVER_INTEGRACIO:
                return null;
            default:
                return null;
        }
    }

}
