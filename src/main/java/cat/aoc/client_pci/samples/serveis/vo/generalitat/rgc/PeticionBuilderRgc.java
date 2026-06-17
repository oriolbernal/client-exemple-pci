package cat.aoc.client_pci.samples.serveis.vo.generalitat.rgc;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.serveis.rgc.PeticioConsultaPrestacions;

import java.util.Properties;

public class PeticionBuilderRgc extends PeticionBuilderFromProperties<OperacioRgc> {
    public PeticionBuilderRgc(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioRgc operacio) {
        switch (operacio) {
            case RGC_CONSULTA:
                return new Object[]{
                        buildPeticioConsultaPrestacions()
                };
            case RGC_CONSULTA_HISTORIC:
            default:
                return new Object[]{};
        }
    }

    private PeticioConsultaPrestacions buildPeticioConsultaPrestacions() {
        PeticioConsultaPrestacions peticio = new PeticioConsultaPrestacions();
        peticio.setDocumentIdentificador("37657624T");
        peticio.setTipusDocument("00001");
        return peticio;
    }

}
