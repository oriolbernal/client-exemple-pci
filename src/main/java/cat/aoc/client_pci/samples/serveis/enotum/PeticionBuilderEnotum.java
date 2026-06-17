package cat.aoc.client_pci.samples.serveis.enotum;

import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.pci.peticion.Fichero;
import generated.pci.peticion.Ficheros;
import generated.pci.peticion.Peticion;
import generated.serveis.enotum.UsuariType;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import java.util.Properties;

import static cat.aoc.client_pci.samples.PeticionBuilderFromPropertiesConstants.CODI_ENS;
import static cat.aoc.client_pci.samples.serveis.enotum.PeticionBuilderEnotumCerca.buildPeticioCerca;
import static cat.aoc.client_pci.samples.serveis.enotum.PeticionBuilderEnotumConsulta.buildPeticioConsulta;
import static cat.aoc.client_pci.samples.serveis.enotum.PeticionBuilderEnotumParaulaPas.buildPeticioParaulaPas;
import static cat.aoc.client_pci.samples.serveis.enotum.PeticionBuilderEnotumProcessarTramesa.buildPeticioProcessarTramesa;
import static cat.aoc.client_pci.samples.serveis.enotum.PeticionBuilderEnotumResum.buildPeticioResum;

public class PeticionBuilderEnotum extends PeticionBuilderFromProperties<OperacioEnotum> {

    public PeticionBuilderEnotum(Properties properties) {
        super(properties);
    }

    @Override
    public Peticion build(OperacioEnotum operacio, Finalitat finalitat) {
        Peticion peticion = super.build(operacio, finalitat);
        if (operacio == OperacioEnotum.PROCESSAR_TRAMESA) {
            peticion.getSolicitudes().getSolicitudTransmision().get(0).getDatosGenericos()
                    .setFicheros(getFicheros());
        }
        return peticion;
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioEnotum operacio) {
        return new Object[]{getDatoEspecifico(operacio)};
    }

    private static Ficheros getFicheros() {
        Fichero fichero = new Fichero();
        fichero.setNombreFichero("sample.pdf");
        fichero.setId("1234");
        fichero.setVia("Salida");
        FileDataSource ds = new FileDataSource("src/main/resources/examples/example.pdf");
        fichero.setContenido(new DataHandler(ds)); // XOP reference
        Ficheros ficheros = new Ficheros();
        ficheros.getFichero().add(fichero);
        return ficheros;
    }

    private Object getDatoEspecifico(OperacioEnotum operacio) {
        switch (operacio) {
            case CERCA:
                return buildPeticioCerca(buildUsuari());
            case PROCESSAR_TRAMESA:
                return buildPeticioProcessarTramesa(buildUsuari());
            case CONSULTA:
                return buildPeticioConsulta(buildUsuari());
            case RESUM:
                return buildPeticioResum(buildUsuari());
            case PARAULA_PAS:
                return buildPeticioParaulaPas(buildUsuari());
            case ANULLACIO:
            case EVIDENCIA:
            case PRACTICAR:
            case RECUPERAR_REPORT:
            default:
                return null;
        }
    }

    private UsuariType buildUsuari() {
        UsuariType usuari = new UsuariType();
        UsuariType.Empleat empleat = new UsuariType.Empleat();
        empleat.setCodiOrganisme(properties.getProperty(CODI_ENS));
        empleat.setCodiDepartament(properties.getProperty(CODI_ENS));
        usuari.setEmpleat(empleat);
        return usuari;
    }

}
