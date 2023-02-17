package com.stefanini.interfaces;

public interface RegexConstants {
    String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[}{,.^?~=+\\-_\\/*\\-+.\\|ç])" +
            "(?:([0-9a-zA-Z[}{,.^?~=+\\-_\\/*\\-+.\\|ç]])){4,10}$";
    String DAY_OF_BIRTH_REGEX = "^[0-9]{4}/[0-9]{2}/[0-9]{2}$";
    String LOCAL_DATE_TIME_REGEX = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{9}$";
    String EMAIL_REGEX = "^(?=.*@)(?=.*.com)(?:([a-z@._])){4,100}";
}
