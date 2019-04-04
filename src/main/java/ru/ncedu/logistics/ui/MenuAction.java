package ru.ncedu.logistics.ui;

public enum MenuAction {
    ADD_TOWN("Add town"),
    ADD_OFFICE("Add office"),
    ADD_OFFERING("Add offering"),
    ADD_PRODUCT("Add product"),
    ADD_ROAD("Add road between towns"),
    SHOW_INFO("Show info about office"),
    EXIT("Exit from programm");

    private String description;

    MenuAction(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}