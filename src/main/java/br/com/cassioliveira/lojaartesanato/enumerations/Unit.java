package br.com.cassioliveira.lojaartesanato.enumerations;

/**
 *
 * @author Cássio Oliveira <cassio@cassioliveira.com.br>
 */
public enum Unit {

    CAIXA("Caixa"),
    PACOTE("Pacote"),
    GRAMA("Grama"),
    KILOGRAMA("Quilo"),
    LITRO("Litro"),
    MILILITRO("Milímetro"),
    UNIDADE("Unidade"),
    DUZIA("Dúzia"),
    CENTIMETRO("Centímetro"),
    METRO("Metro"),
    OUTRO("Outro");

    private final String description;

    Unit(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
