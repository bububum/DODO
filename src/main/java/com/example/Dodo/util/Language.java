package com.example.Dodo.util;

public enum Language {
    RU,
    EN;

    public static Language getLanguage(Integer ordinal) {
        switch(ordinal) {
            case 1 : return RU;

            case 2 : return EN;

            default: return RU;
        }
    }
}
