package cat.aoc.client_pci.samples.serveis.vo.estat.cadastre;

import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.serveis.cadastre.certificacio.Ambito;
import generated.serveis.cadastre.certificacio.Cn;
import generated.serveis.cadastre.certificacio.DatosEntrada;
import generated.serveis.cadastre.certificacio.PeticioCertificacioTitular;

import java.util.Properties;

public class PeticionBuilderCadastre extends PeticionBuilderFromProperties<OperacioCadastre> {
    public PeticionBuilderCadastre(Properties properties) {
        super(properties);
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioCadastre operacio) {
        switch (operacio) {
            case CERTIFICACIO_TITULARITAT:
                return new Object[]{
                        buildPeticioCertificacioTitular()
                };
            case DADES_CADASTRALS:
            case DESCRIPTIVA_GRAFICA:
            case DOCUMENT_CSV:
            default:
                return new Object[]{};
        }
    }

    private PeticioCertificacioTitular buildPeticioCertificacioTitular() {
        PeticioCertificacioTitular peticio = new PeticioCertificacioTitular();
        DatosEntrada datos = new DatosEntrada();
        Ambito ambito = new Ambito();
        ambito.setCcaa("09");
        ambito.setCp("08");
        ambito.setCm("155");
        datos.setAmbito(ambito);
        datos.setCn(Cn.UR);
        peticio.setDatosEntrada(datos);
        return peticio;
    }

}
