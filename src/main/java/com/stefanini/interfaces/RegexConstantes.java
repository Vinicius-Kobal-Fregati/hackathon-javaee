package com.stefanini.interfaces;

public interface RegexConstantes {
    String SENHA_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[}{,.^?~=+\\-_\\/*\\-+.\\|ç!])" +
            "(?:([0-9a-zA-Z[}{,.^?~=+\\-_\\/*\\-+.\\|ç!]])){4,10}$";
    String DATA_ANIVERSARIO_REGEX = "^[0-9]{4}/[0-9]{2}/[0-9]{2}$";
    String EMAIL_REGEX = "^(?=.*@)(?=.*\\.com)(?:([a-z@._])){4,100}";
}
