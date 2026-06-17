package cat.aoc.client_pci.samples.serveis.vo.estat.notaris;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.serveis.notaris.IdEscriptura;
import generated.serveis.notaris.PeticioConsultaSubsistenciaAdministradors;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
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
        try {
            id.setDataAutoritzacio(DatatypeFactory.newInstance().newXMLGregorianCalendar("2013-01-11"));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        dades.setIdEscriptura(id);
        peticio.setDadesProtocol(dades);
        return peticio;
    }

}
