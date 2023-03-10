package com.example.BanRyeohaedyuo.domain.enumtype;

public enum Grade{
    UNRANKED("초보",0),
    AMATEUR("아마추어",10),
    SEMIPRO("세미프로",20);

    private final String name;
    private final int value;

    private Grade(String name, int value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
}
