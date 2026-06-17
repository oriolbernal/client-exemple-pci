package cat.aoc.client_pci.samples.serveis.vo.estat.dgt;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.serveis.dgt.PeticioConsultaVehicle;

import java.util.Properties;

public class PeticionBuilderDgt extends PeticionBuilderFromProperties<OperacioDgt> {
    public PeticionBuilderDgt(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioDgt operacio) {
        switch (operacio) {
            case DGT_DADES_VEHICLE:
                return new Object[]{
                        buildPeticioConsultaVehicle()
                };
            case DGT_DADES_VEHICLE_SANCIONS:
            case DGT_PERMISOS_CONDUCTOR:
            case DGT_TITULAR_VIA:
            case DGT_SANCIONS_CONDUCTOR:
            case DGT_VEHICLES_CONDUCTOR:
            case DGT_DISTINTIU_MEDIAMBIENTAL:
            default:
                return new Object[]{};
        }
    }

    private PeticioConsultaVehicle buildPeticioConsultaVehicle() {
        PeticioConsultaVehicle peticioConsultaVehicle = new PeticioConsultaVehicle();
        peticioConsultaVehicle.setMatricula("AB1234D");
        return peticioConsultaVehicle;
    }

}
