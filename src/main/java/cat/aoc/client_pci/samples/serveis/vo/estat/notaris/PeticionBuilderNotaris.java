package cat.aoc.client_pci.samples.serveis.vo.estat.notaris;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import cat.aoc.client_pci.utils.XmlDates;
import generated.serveis.notaris.IdEscriptura;
import generated.serveis.notaris.PeticioConsultaSubsistenciaAdministradors;

import java.math.BigInteger;
import java.util.Properties;

public class PeticionBuilderNotaris extends PeticionBuilderFromProperties<OperacioNotaris> {
    public PeticionBuilderNotaris(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioNotaris operacio) {
        switch (operacio) {
            case SUBSISTENCIA_ADMINISTRADORS:
                return new Object[]{
                        buildPeticioConsultaSubsistenciaAdministradors()
                };
            case SUBSISTENCIA_PODERS:
            case COPIA_SIMPLE:
            case CONSULTA_NOTARIS:
            default:
                return new Object[]{};
        }
    }

    private PeticioConsultaSubsistenciaAdministradors buildPeticioConsultaSubsistenciaAdministradors() {
        PeticioConsultaSubsistenciaAdministradors peticio = new PeticioConsultaSubsistenciaAdministradors();
        PeticioConsultaSubsistenciaAdministradors.DadesProtocol dades = new PeticioConsultaSubsistenciaAdministradors.DadesProtocol();
        IdEscriptura id = new IdEscriptura();
        id.setCodiNotari("0913999");
        id.setCodiNotaria("070012009");
        id.setNumProtocol(BigInteger.valueOf(22));
        id.setDataAutoritzacio(XmlDates.of("2013-01-11"));
        dades.setIdEscriptura(id);
        peticio.setDadesProtocol(dades);
        return peticio;
    }

}
