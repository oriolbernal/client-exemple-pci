package cat.aoc.client_pci.samples.serveis.vo.estat.dgp;

import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.pci.peticion.Peticion;
import generated.pci.peticion.Titular;
import generated.serveis.dgp.PeticioConsultaDadesIdentitat;
import generated.serveis.dgp.PeticioVerificacioDadesIdentitat;

import java.util.Properties;

public class PeticionBuilderDgp extends PeticionBuilderFromProperties<OperacioDgp> {
    public PeticionBuilderDgp(Properties properties) {
        super(properties);
    }

    @Override
    public Peticion build(OperacioDgp operacio, Finalitat finalitat) {
        Peticion peticion = super.build(operacio, finalitat);
        peticion.getSolicitudes().getSolicitudTransmision().get(0).getDatosGenericos().setTitular(getTitular());
        return peticion;
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioDgp operacio) {
        switch (operacio) {
            case IDENTITAT_DADES:
                return new Object[]{
                        buildPeticioConsultaDadesIdentitat()
                };
            case IDENTITAT_VERIFICACIO:
                return new Object[]{
                        buildPeticioVerificacioDadesIdentitat()
                };
            default:
                return null;
        }
    }

    private static Titular getTitular() {
        Titular titular = new Titular();
        titular.setTipoDocumentacion("DNI");
        titular.setDocumentacion("10000949C");
        titular.setNombre("OLGA");
        titular.setApellido1("MIGUEL");
        titular.setApellido2("CHAO");
        titular.setNombreCompleto("OLGA MIGUEL CHAO");
        return titular;
    }

    private PeticioConsultaDadesIdentitat buildPeticioConsultaDadesIdentitat() {
        return new PeticioConsultaDadesIdentitat();
    }

    private PeticioVerificacioDadesIdentitat buildPeticioVerificacioDadesIdentitat() {
        return new PeticioVerificacioDadesIdentitat();
    }
}
