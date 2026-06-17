package cat.aoc.client_pci.samples;

import cat.aoc.client_pci.api.model.Finalitat;
import generated.pci.peticion.Peticion;

/**
 * Constructor de peticions PCI per a un servei concret.
 *
 * <p>Cada servei té la seva implementació, que omple tant els atributs genèrics
 * de la petició com les dades específiques de l'operació sol·licitada.</p>
 *
 * @param <O> tipus d'operació suportada pel servei
 */
public interface PeticionBuilder<O extends Operacio> {

    /**
     * Construeix una petició per a l'operació i la finalitat indicades.
     *
     * @param operacio  operació a executar
     * @param finalitat finalitat de la consulta
     * @return la petició construïda
     */
    Peticion build(O operacio, Finalitat finalitat);

    /**
     * Construeix una petició per a l'operació indicada amb un procediment concret.
     *
     * @param operacio      operació a executar
     * @param procedimiento codi del procediment
     * @return la petició construïda
     */
    Peticion build(O operacio, String procedimiento);

}
