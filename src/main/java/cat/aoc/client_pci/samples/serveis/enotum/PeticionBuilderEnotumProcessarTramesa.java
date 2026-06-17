package cat.aoc.client_pci.samples.serveis.enotum;

import generated.serveis.enotum.*;

final class PeticionBuilderEnotumProcessarTramesa {

    private PeticionBuilderEnotumProcessarTramesa() {
    }

    public static PeticioProcessarTramesa buildPeticioProcessarTramesa(UsuariType usuari) {
        PeticioProcessarTramesa peticio = new PeticioProcessarTramesa();
        peticio.setTramesa(getTramesa());
        peticio.setUsuari(usuari);
        return peticio;
    }

    private static PeticioProcessarTramesa.Tramesa getTramesa() {
        PeticioProcessarTramesa.Tramesa tramesa = new PeticioProcessarTramesa.Tramesa();
        tramesa.setDadesOfici(getDadesOfici());
        tramesa.getNotificacio().add(getNotificacio());
        tramesa.setDocuments(getDocument());
        return tramesa;
    }


    private static DadesOficiType getDadesOfici() {
        DadesOficiType dadesOfici = new DadesOficiType();
        dadesOfici.setCosNotificacio("Cos");
        dadesOfici.setPeuRecurs("Peu");
        return dadesOfici;
    }

    private static NotificacioAmbDestinatarisType getNotificacio() {
        NotificacioAmbDestinatarisType notificacio = new NotificacioAmbDestinatarisType();
        notificacio.setTitol("Titol");
        notificacio.setReferencia("Ref");
        notificacio.setTipusObjecte(TipusObjecteType.NOTIFICACIO);
        notificacio.setTipusAcces(TipusAccesType.BAIX);
        notificacio.setDiesExpiracio(10);
        DestinatarisType destinataris = new DestinatarisType();
        DestinatarisType.Destinatari destinatari = new DestinatarisType.Destinatari();
        PersonaFisicaType personaFisica = new PersonaFisicaType();
        BustiesCorreuType bustia = new BustiesCorreuType();
        bustia.getBustiaCorreu().add("XXXX@XXXXXX.com");
        personaFisica.setBustiesCorreu(bustia);
        DocumentPersonaFisicaType documentPersonaFisica = new DocumentPersonaFisicaType();
        documentPersonaFisica.setNIF("12345678Z");
        personaFisica.setDocumentIdentificatiu(documentPersonaFisica);
        personaFisica.setNom("Friedriche");
        personaFisica.setPrimerCognom("Wilhelm");
        personaFisica.setSegonCognom("Nietzsche");
        destinatari.setPersonaFisica(personaFisica);
        destinatari.setIdioma(Idioma.CA);
        destinataris.getDestinatari().add(destinatari);
        notificacio.setDestinataris(destinataris);
        return notificacio;
    }

    private static DocumentsType getDocument() {
        DocumentsType documentsType = new DocumentsType();
        DocumentType document = new DocumentType();
        document.setNom("sample.pdf");
        document.setIdFicheroPCI("1234");
        documentsType.setResolucio(document);
        return documentsType;
    }

}
