package cat.aoc.client_pci.samples.serveis.vo.estat.inss;

import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import cat.aoc.client_pci.utils.XmlDates;
import generated.pci.peticion.Peticion;
import generated.pci.peticion.Titular;
import generated.serveis.inss_historic.PeticioConsultaPrestacionsHistoric;

import java.util.Properties;

public class PeticionBuilderInss extends PeticionBuilderFromProperties<OperacioInss> {
    public PeticionBuilderInss(Properties properties) {
        super(properties);
    }

    @Override
    public Peticion build(OperacioInss operacio, Finalitat finalitat) {
        Peticion peticion = super.build(operacio, finalitat);
        peticion.getSolicitudes().getSolicitudTransmision().get(0).getDatosGenericos().setTitular(getTitular());
        return peticion;
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioInss operacio) {
        switch (operacio) {
            case PRESTACIONS:
                return new Object[]{};
            case PRESTACIONS_HISTORIC:
                return new Object[]{
                        buildPeticioConsultaPrestacionsHistoric()
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

    private PeticioConsultaPrestacionsHistoric buildPeticioConsultaPrestacionsHistoric() {
        PeticioConsultaPrestacionsHistoric peticio = new PeticioConsultaPrestacionsHistoric();
        peticio.setDataInici(XmlDates.of("2021-01-01"));
        peticio.setDataFi(XmlDates.of("2021-12-01"));
        return peticio;
    }

}
