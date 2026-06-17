package cat.aoc.client_pci.samples.serveis.etauler;

import generated.serveis.etauler.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

final class PeticionBuilderEtaulerPublicar {

    private PeticionBuilderEtaulerPublicar() {
    }

    public static PeticioPublicarEdicte buildPeticioPublicarEdicte() {
        PeticioPublicarEdicte peticio = new PeticioPublicarEdicte();
        TEdicte edicte = new TEdicte();
        edicte.setIdEdicte("1713710007_502");
        edicte.setNumExpedient("X2018000003");
        Informacio info = new Informacio();
        info.setTitol("Publicació PRE Etauler - PROVA 1");
        info.setDescripcio("Publicació PRE Etauler - PROVA 1");
        info.setIdioma(TIdioma.CA);
        edicte.getInformacio().add(info);
        try {
            edicte.setDataIniciPublicacio(DatatypeFactory.newInstance().newXMLGregorianCalendar("2023-03-31"));
            edicte.setDataFiPublicacio(DatatypeFactory.newInstance().newXMLGregorianCalendar("2023-05-31"));
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        edicte.setDestacat(false);
        Diligencia diligencia = new Diligencia();
        diligencia.setIdioma(TIdioma.CA);
        diligencia.setFormat("electronica");
        edicte.setDiligencia(diligencia);
        edicte.getDocument().add(getDocument());
        ClassificacioSeq classificacioSeq = new ClassificacioSeq();
        classificacioSeq.setTipus("procedencia");
        ClassificacioSeq.Classificacio classificacio = new ClassificacioSeq.Classificacio();
        classificacio.setConcepte("Procedència");
        classificacio.setCategoria("Interna");
        classificacio.setIdioma(TIdioma.CA);
        classificacioSeq.getClassificacio().add(classificacio);
        edicte.getClassificacio().add(classificacioSeq);
        peticio.setEdicte(edicte);
        return peticio;
    }

    private static Document getDocument() {
        Document document = new Document();
        document.setIdioma(TIdioma.CA);
        document.setNom("sample.pdf");
        document.setId("1234");
        return document;
    }

}
