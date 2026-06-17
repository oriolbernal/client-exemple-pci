package cat.aoc.client_pci.samples.serveis.vo.estat.registre_civil;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import cat.aoc.client_pci.utils.XmlDates;
import generated.serveis.registre_civil.AltresDades;
import generated.serveis.registre_civil.PeticioConsultaRegistreCivil;

import java.util.Properties;

public class PeticionBuilderRegistreCivil extends PeticionBuilderFromProperties<OperacioRegistreCivil> {
    public PeticionBuilderRegistreCivil(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioRegistreCivil operacio) {
        switch (operacio) {
            case NAIXEMENT:
                return new Object[]{
                        buildPeticioConsultaRegistreCivil()
                };
            case MATRIMONI:
            case DEFUNCIO:
            default:
                return new Object[]{};
        }
    }

    private PeticioConsultaRegistreCivil buildPeticioConsultaRegistreCivil() {
        PeticioConsultaRegistreCivil peticio = new PeticioConsultaRegistreCivil();
        peticio.setDataFetRegistral(XmlDates.of("1980-03-02"));
        peticio.setAbsenciaSegonCognom(false);
        AltresDades altres = new AltresDades();
        altres.setPoblacioFetRegistral("01009");
        altres.setDataNaixement(XmlDates.of("1980-03-02"));
        peticio.setAltresDades(altres);
        return peticio;
    }

}
