package cat.aoc.client_pci.samples.serveis.vo.estat.igae;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.serveis.igae.PeticioConsultaInhabilitacions;

import java.util.Properties;

public class PeticionBuilderIgae extends PeticionBuilderFromProperties<OperacioIgae> {
    public PeticionBuilderIgae(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioIgae operacio) {
        switch (operacio) {
            case IGAE_INHABILITACIONS:
                return new Object[]{
                        buildPeticioConsultaInhabilitacions()
                };
            case IGAE_MINIMIS:
            case IGAE_CONCESSIONS:
            default:
                return new Object[]{};
        }
    }

    private PeticioConsultaInhabilitacions buildPeticioConsultaInhabilitacions() {
        PeticioConsultaInhabilitacions peticio = new PeticioConsultaInhabilitacions();
        peticio.setPais("ES");
        return peticio;
    }

}
