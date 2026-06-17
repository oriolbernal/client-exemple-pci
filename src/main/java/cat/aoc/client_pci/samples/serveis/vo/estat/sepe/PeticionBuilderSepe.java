package cat.aoc.client_pci.samples.serveis.vo.estat.sepe;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;

import java.util.Properties;

public class PeticionBuilderSepe extends PeticionBuilderFromProperties<OperacioSepe> {
    public PeticionBuilderSepe(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioSepe operacio) {
        switch (operacio) {
            case VERIF_DADES_ATUR:
            case VERIF_IMPORTS_ACTUALS:
            case VERIF_IMPORTS_PERIODE:
            default:
                return new Object[]{
                };
        }
    }

}
