package cat.aoc.client_pci.samples.serveis.sir2;

import cat.aoc.client_pci.api.model.Finalitat;
import cat.aoc.client_pci.samples.PeticionBuilderFromProperties;
import generated.pci.peticion.Fichero;
import generated.pci.peticion.Ficheros;
import generated.pci.peticion.Peticion;

import java.util.Properties;

import static cat.aoc.client_pci.samples.serveis.sir2.PeticionBuilderSir2Confirmar.buildPeticioConfirmacioAssentament;
import static cat.aoc.client_pci.samples.serveis.sir2.PeticionBuilderSir2Consultar.buildPeticioConsultaAssentament;
import static cat.aoc.client_pci.samples.serveis.sir2.PeticionBuilderSir2Enviar.buildPeticioEnviamentAssentament;
import static cat.aoc.client_pci.samples.serveis.sir2.PeticionBuilderSir2Rebutjar.buildPeticioRebuigAssentament;
import static cat.aoc.client_pci.samples.serveis.sir2.PeticionBuilderSir2Reenviar.buildPeticioReenviamentAssentament;

public class PeticionBuilderSir2 extends PeticionBuilderFromProperties<OperacioSir2> {

    public PeticionBuilderSir2(Properties properties) {
        super(properties);
    }

    @Override
    public Peticion build(OperacioSir2 operacio, Finalitat finalitat) {
        Peticion peticion = super.build(operacio, finalitat);
        if (operacio == OperacioSir2.ENVIAR) {
            peticion.getSolicitudes().getSolicitudTransmision().get(0).getDatosGenericos()
                    .setFicheros(getFicheros());
        }
        return peticion;
    }

    private static Ficheros getFicheros() {
        Fichero fichero = new Fichero();
        fichero.setNombreFichero("sample.pdf");
        fichero.setId("1234");
        fichero.setVia("Salida");
        javax.activation.DataSource ds = new javax.activation.FileDataSource("src\\main\\resources\\examples\\example.pdf");
        fichero.setContenido(new javax.activation.DataHandler(ds));
        Ficheros ficheros = new Ficheros();
        ficheros.getFichero().add(fichero);
        return ficheros;
    }

    @Override
    protected Object[] getDatosEspecificos(OperacioSir2 operacio) {
        return new Object[]{getDatoEspecifico(operacio)};
    }

    private Object getDatoEspecifico(OperacioSir2 operacio) {
        switch (operacio) {
            case ENVIAR:
                return buildPeticioEnviamentAssentament();
            case CONFIRMAR:
                return buildPeticioConfirmacioAssentament();
            case REBUTJAR:
                return buildPeticioRebuigAssentament();
            case REENVIAR:
                return buildPeticioReenviamentAssentament();
            case CONSULTAR:
                return buildPeticioConsultaAssentament();
            case SINCRONITZAR:
            default:
                return null;
        }
    }

}
