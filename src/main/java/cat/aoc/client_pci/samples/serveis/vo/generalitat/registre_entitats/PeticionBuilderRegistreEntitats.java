package cat.aoc.client_pci.samples.serveis.vo.generalitat.registre_entitats;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.serveis.registre_entitats.PeticioBasicaConsulta;

import java.util.Properties;

public class PeticionBuilderRegistreEntitats extends PeticionBuilderFromProperties<OperacioRegistreEntitats> {
    public PeticionBuilderRegistreEntitats(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioRegistreEntitats operacio) {
        switch (operacio) {
            case ENTITAT_INSCRIPCIO:
                return new Object[]{
                        buildPeticioBasicaConsulta()
                };
            case ENTITAT_DADES:
            case ENTITAT_ESTATUTS:
            case ENTITAT_ESCRIPTURES:
            case ENTITAT_COMPTES:
            default:
                return new Object[]{};
        }
    }

    private PeticioBasicaConsulta buildPeticioBasicaConsulta() {
        PeticioBasicaConsulta peticio = new PeticioBasicaConsulta();
        peticio.setNomEntitat("Xarxes");
        return peticio;
    }

}
