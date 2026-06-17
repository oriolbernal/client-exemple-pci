package cat.aoc.client_pci.samples.serveis.boe;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;

import java.util.Properties;

import static cat.aoc.client_pci.samples.serveis.boe.PeticionBuilderBoeConsultar.buildPeticioEnviamentAnunci;

public class PeticionBuilderBoe extends PeticionBuilderFromProperties<OperacioBoe> {

    public PeticionBuilderBoe(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioBoe operacio) {
        return new Object[]{ getDatoEspecifico(operacio) };
    }

    private Object getDatoEspecifico(OperacioBoe operacio) {
        switch (operacio) {
            case PUBLICAR:
                return null;
            case CONSULTAR:
                return buildPeticioEnviamentAnunci();
            case ANULAR:
                return null;
            default:
                return null;
        }
    }

}
