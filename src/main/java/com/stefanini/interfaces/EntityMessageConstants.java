package com.stefanini.interfaces;

public interface EntityMessageConstants {
    String MAX_50_CHARACTERS = "Deve ter no máximo 50 caracteres";
    String NOT_EMPTY = "Não pode ser vazio";
    String NOT_NULL = "Não pode ser nulo";
    String BETWEEN_5_TO_20_CHARACTERS = "Deve ter entre 5 a 20 caracteres";
    String BETWEEN_4_TO_10_CARACTERS = "Deve ter entre 4 a 10 caracteres";
    String PATTERN_BIRTHDAY_DATE = "A data de aniversário está fora do padrão: 1999/08/05";
    String PATTERN_DATE_TIME = "A data e horário deve estar no padrão 0000-00-00T00:00:00.000000000";
    static String noNull(String text) { return text + " não pode ser nulo"; }
    static String noEmpty(String text) { return text + " não pode ser vazio"; }
}
