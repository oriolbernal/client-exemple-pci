package cat.aoc.client_pci.samples.serveis.vo.generalitat.rpe;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;

import java.util.Properties;

public class PeticionBuilderRpe extends PeticionBuilderFromProperties<OperacioRpe> {
    public PeticionBuilderRpe(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioRpe operacio) {
        switch (operacio) {
            case RPE_CONSULTA:
            case RPE_VERIFICACIO:
            default:
                return new Object[]{};
        }
    }

}
