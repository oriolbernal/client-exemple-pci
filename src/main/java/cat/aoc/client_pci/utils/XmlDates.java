package cat.aoc.client_pci.utils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Utilitat per crear dates XML ({@code xs:date} / {@code xs:dateTime}) a partir
 * de cadenes en format lexical XSD (per exemple {@code "1980-03-02"}).
 *
 * <p>Centralitza la creació de {@link XMLGregorianCalendar} per evitar repetir el
 * bloc {@code try/catch} de {@link DatatypeConfigurationException} a cada petició.</p>
 */
public final class XmlDates {

    private XmlDates() {
        // Classe utilitària: no instanciable.
    }

    /**
     * Converteix una data en format lexical XSD a {@link XMLGregorianCalendar}.
     *
     * @param isoDate data en format lexical XSD (p. ex. {@code "2023-03-31"})
     * @return el calendari XML corresponent
     * @throws IllegalStateException si la implementació JAXP no es pot inicialitzar
     */
    public static XMLGregorianCalendar of(String isoDate) {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(isoDate);
        } catch (DatatypeConfigurationException e) {
            throw new IllegalStateException("No s'ha pogut crear la data XML: " + isoDate, e);
        }
    }

}
