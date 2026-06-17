package cat.aoc.client_pci.samples.serveis.vo.estat.estrangeria;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.serveis.estrangeria.PeticioConsultaDadesResidenciaLegal;

import java.util.Properties;

public class PeticionBuilderEstrangeria extends PeticionBuilderFromProperties<OperacioEstrangeria> {
    public PeticionBuilderEstrangeria(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioEstrangeria operacio) {
        switch (operacio) {
            case RESIDENCIA_LEGAL:
                return new Object[]{
                        buildPeticioConsultaDadesResidenciaLegal()
                };
            default:
                return null;
        }
    }

    private PeticioConsultaDadesResidenciaLegal buildPeticioConsultaDadesResidenciaLegal() {
        PeticioConsultaDadesResidenciaLegal peticio = new PeticioConsultaDadesResidenciaLegal();
        peticio.setAnyNaixement("1978");
        return peticio;
    }

}
