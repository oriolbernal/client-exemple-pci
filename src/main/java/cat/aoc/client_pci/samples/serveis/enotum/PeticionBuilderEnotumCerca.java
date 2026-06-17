package cat.aoc.client_pci.samples.serveis.enotum;

import generated.serveis.enotum.IntervalValors;
import generated.serveis.enotum.PeticioCerca;
import generated.serveis.enotum.SentitOrdenacioType;
import generated.serveis.enotum.UsuariType;

final class PeticionBuilderEnotumCerca {

    private PeticionBuilderEnotumCerca() {
    }

    public static PeticioCerca buildPeticioCerca(UsuariType usuari) {
        PeticioCerca peticio = new PeticioCerca();
        peticio.setDadesCerca(getDadesCerca());
        peticio.setUsuari(usuari);
        return peticio;
    }

    private static PeticioCerca.DadesCerca getDadesCerca() {
        PeticioCerca.DadesCerca dadesCerca = new PeticioCerca.DadesCerca();
        dadesCerca.setPaginacio(getPaginacio());
        dadesCerca.setCriterisNotificacio(getCriterisNotificacio());
        return dadesCerca;
    }

    private static PeticioCerca.DadesCerca.Paginacio getPaginacio() {
        PeticioCerca.DadesCerca.Paginacio paginacio = new PeticioCerca.DadesCerca.Paginacio();
        paginacio.setNumeroPagina(1);
        paginacio.setResultatsPerPagina(25);
        paginacio.setSentitOrdenacio(SentitOrdenacioType.DESCENDENT);
        return paginacio;
    }

    private static PeticioCerca.DadesCerca.CriterisNotificacio getCriterisNotificacio() {
        IntervalValors valors = new IntervalValors();
        valors.setValorAbsolut("REF");
        PeticioCerca.DadesCerca.CriterisNotificacio criteris = new PeticioCerca.DadesCerca.CriterisNotificacio();
        criteris.setReferencia(valors);
        return criteris;
    }

}
