package cat.aoc.client_pci.samples.serveis.vo.generalitat.grau_discapacitat;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.serveis.grau_discapacitat.DadesPersona;
import generated.serveis.grau_discapacitat.PeticioConsultaDiscapacitatSimple;

import java.util.Properties;

public class PeticionBuilderGrauDiscapacitat extends PeticionBuilderFromProperties<OperacioGrauDiscapacitat> {
    public PeticionBuilderGrauDiscapacitat(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioGrauDiscapacitat operacio) {
        switch (operacio) {
            case GRAU_DISCAPACITAT_SIMPLE:
                return new Object[]{
                        buildPeticioConsultaDiscapacitatSimple()
                };
            case GRAU_DISCAPACITAT_TOTAL:
            default:
                return new Object[]{};
        }
    }

    private PeticioConsultaDiscapacitatSimple buildPeticioConsultaDiscapacitatSimple() {
        PeticioConsultaDiscapacitatSimple peticio = new PeticioConsultaDiscapacitatSimple();
        DadesPersona dadesPersona = new DadesPersona();
        dadesPersona.setNom("NOM12");
        dadesPersona.setCognoms("COGNOM121 COGNOM122");
        dadesPersona.setDataNaixement("15111946");
        peticio.setDadesPersona(dadesPersona);
        return peticio;
    }

}
