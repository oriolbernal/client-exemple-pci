package cat.aoc.client_pci.samples.serveis.sir2;

import generated.serveis.sir2.FicheroIntercambioSICRES3;
import generated.serveis.sir2.PeticioEnviamentAssentament;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

final class PeticionBuilderSir2Enviar {

    private PeticionBuilderSir2Enviar() {
    }

    public static PeticioEnviamentAssentament buildPeticioEnviamentAssentament() {
        PeticioEnviamentAssentament peticio = new PeticioEnviamentAssentament();
        peticio.setIdEnviament("ID_SIR - " + System.currentTimeMillis());
        peticio.setEnviament(buildFicheroIntercambioSICRES3Xml());
        return peticio;
    }

    public static String buildFicheroIntercambioSICRES3Xml() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPaths("generated.serveis.sir2");
        FicheroIntercambioSICRES3 sicres3 = buildFicheroIntercambioSICRES3();
        StringWriter sw = new StringWriter();
        Result result = new StreamResult(sw);
        marshaller.marshal(sicres3, result);
        return sw.toString();
    }

    public static FicheroIntercambioSICRES3 buildFicheroIntercambioSICRES3() {
        FicheroIntercambioSICRES3 sicres3 = new FicheroIntercambioSICRES3();
        sicres3.setDeOrigenORemitente(getDeOrigenORemitente());
        sicres3.setDeDestino(getDeDestino());
        sicres3.getDeInteresado().add(getDeInteresado());
        sicres3.setDeAsunto(getDeAsunto());
        sicres3.getDeAnexo().add(getDeAnexo());
        sicres3.setDeInternosControl(getDeInternosControl());
        sicres3.setDeFormularioGenerico(getDeFormularioGenerico());
        return sicres3;
    }

    private static FicheroIntercambioSICRES3.DeOrigenORemitente getDeOrigenORemitente() {
        FicheroIntercambioSICRES3.DeOrigenORemitente o = new FicheroIntercambioSICRES3.DeOrigenORemitente();
        o.setCodigoEntidadRegistralOrigen("O00015791");
        o.setDecodificacionEntidadRegistralOrigen("Registro del Departamento de Agricultura, Ganadería, Pesca, Alimentación y Medi");
        o.setNumeroRegistroEntrada("REGAGE21e00000013486");
        o.setFechaHoraEntrada("20210211192653");
        o.setCodigoUnidadTramitacionOrigen("A09018933");
        o.setDecodificacionUnidadTramitacionOrigen("Ayuntamiento de Catarroja");
        return o;
    }

    private static FicheroIntercambioSICRES3.DeDestino getDeDestino() {
        FicheroIntercambioSICRES3.DeDestino destino = new FicheroIntercambioSICRES3.DeDestino();
        destino.setCodigoEntidadRegistralDestino("O00002721");
        destino.setDecodificacionEntidadRegistralDestino("Consorcio Administración Abierta de Cataluña (CAOC)");
        destino.setCodigoUnidadTramitacionDestino("LA1000323");
        destino.setDecodificacionUnidadTramitacionDestino("Consorcio Administración Abierta de Cataluña");
        return destino;
    }

    private static FicheroIntercambioSICRES3.DeInteresado getDeInteresado() {
        FicheroIntercambioSICRES3.DeInteresado interesado = new FicheroIntercambioSICRES3.DeInteresado();
        interesado.setTipoDocumentoIdentificacionInteresado("C");
        interesado.setDocumentoIdentificacionInteresado("S0811001G");
        interesado.setRazonSocialInteresado("CATARROJA");
        return interesado;
    }

    private static FicheroIntercambioSICRES3.DeAsunto getDeAsunto() {
        FicheroIntercambioSICRES3.DeAsunto asunto = new FicheroIntercambioSICRES3.DeAsunto();
        asunto.setResumen("PRUEBA");
        asunto.setReferenciaExterna("");
        asunto.setNumeroExpediente("");
        return asunto;
    }

    private static FicheroIntercambioSICRES3.DeAnexo getDeAnexo() {
        FicheroIntercambioSICRES3.DeAnexo anexo = new FicheroIntercambioSICRES3.DeAnexo();
        anexo.setNombreFicheroAnexado("SIR.pdf");
        anexo.setIdentificadorFichero("1234");
        anexo.setValidezDocumento("01");
        anexo.setTipoDocumento("02");
        anexo.setHash(new byte[]{});
        anexo.setTipoMIME("application/pdf");
        anexo.setObservaciones("");
        return anexo;
    }

    private static FicheroIntercambioSICRES3.DeInternosControl getDeInternosControl() {
        FicheroIntercambioSICRES3.DeInternosControl control = new FicheroIntercambioSICRES3.DeInternosControl();
        control.setTipoTransporteEntrada("");
        control.setNumeroTransporteEntrada("");
        control.setIdentificadorIntercambio("");
        control.setTipoAnotacion("02");
        control.setTipoRegistro("0");
        control.setDocumentacionFisica("3");
        control.setObservacionesApunte("");
        control.setIndicadorPrueba("0");
        control.setCodigoEntidadRegistralInicio("O00015791");
        control.setDecodificacionEntidadRegistralInicio("Registro del Departamento de Agricultura, Ganader�a, Pesca, Alimentaci�n y Medi");
        return control;
    }

    private static FicheroIntercambioSICRES3.DeFormularioGenerico getDeFormularioGenerico() {
        FicheroIntercambioSICRES3.DeFormularioGenerico formularioGenerico = new FicheroIntercambioSICRES3.DeFormularioGenerico();
        formularioGenerico.setExpone("PRUEBA");
        formularioGenerico.setSolicita("PRUEBA2");
        return formularioGenerico;
    }

}
