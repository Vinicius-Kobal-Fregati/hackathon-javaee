package com.stefanini.interfaces;

public interface MensagensConstantes {
    String MAXIMO_50_CARACTERES = "Deve ter no máximo 50 caracteres";
    String NAO_VAZIO = "Não pode ser vazio";
    String NAO_NULO = "Não pode ser nulo";
    String ENTRE_5_A_20_CARACTERES = "Deve ter entre 5 a 20 caracteres";
    String ENTRE_4_A_10_CARACTERES = "Deve ter entre 4 a 10 caracteres";
    String DATA_FORA_PADRAO = "A data de aniversário está fora do padrão: 1999/08/05";
    static String noNull(String text) { return text + " não pode ser nulo"; }
    static String noEmpty(String text) { return text + " não pode ser vazio"; }
}
