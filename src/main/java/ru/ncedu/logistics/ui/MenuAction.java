package ru.ncedu.logistics.ui;

public enum MenuAction {
    ADD_PRODUCT("Add product"),
    ADD_TOWN("Add town"),
    ADD_OFFICE("Add office"),
    ADD_OFFERING("Add offering"),
    ADD_ROAD("Add road between towns"),
    SHOW_INFO("Show info about office"),
    FIND_PRODUCT("Show offerings with product"),
    SEARCH("Show the cheapest option"),
    EXPORT("Export current data"),
    IMPORT("Import data from file"),
    EXIT("Exit from programm");

    private String description;

    MenuAction(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
}