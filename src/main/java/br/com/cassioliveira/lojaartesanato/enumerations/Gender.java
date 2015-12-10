package br.com.cassioliveira.lojaartesanato.enumerations;

/**
 * Contains a list with Gender, represented by a letter.
 *
 * @author cassio
 */
public enum Gender {

    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private final String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
