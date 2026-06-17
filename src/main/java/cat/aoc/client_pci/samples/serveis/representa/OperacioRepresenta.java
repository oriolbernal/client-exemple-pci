package cat.aoc.client_pci.samples.serveis.representa;

import cat.aoc.client_pci.samples.Operacio;

public enum OperacioRepresenta implements Operacio {
    CONSULTA_REPRESENTACIONS,
    CONSULTA_REPRESENTACIO,
    VALIDACIO,
    ALTA,
    MODIFICACIO,
    CONSULTA_CATALEG,
    CONSULTA_FAMILIES,
    CONSULTA_FAMILIA,
    CONSULTA_TRAMITS,
    CONSULTA_ADMINISTRACIO,
    CONSULTA_ADMINISTRACIONS,
    DESCARREGA;

    @Override
    public String getCodiProducte() {
        return "REPRESENTA";
    }

    @Override
    public String getCodiModalitat() {
        switch (this) {
            case CONSULTA_REPRESENTACIO:
                return "REPRESENTA_CONSULTA";
            default:
                return this.name();
        }
    }

}
