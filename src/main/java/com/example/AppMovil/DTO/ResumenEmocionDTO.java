package com.example.AppMovil.DTO;

public class ResumenEmocionDTO {

    public String emoji;
    public String code;
    public Long total;

    public ResumenEmocionDTO(String emoji, String code, Long total) {
        this.emoji = emoji;
        this.code = code;
        this.total = total;
    }
}
