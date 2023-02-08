package util;

import bean.Config;
import service.menu.*;
import service.Process;

public enum Menu {
    LOGIN(1,"LOGIN",new MenuLoginService()),
    REGISTER(2,"REGISTER",new MenuRegisterService()),
    ADD_TEACHER(3,"ADD TEACHER",new MenuAddTeacherService()),
    ADD_STUDENT(4,"ADD STUDENT",new MenuAddStudentService()),
    SHOW_ALL_TEACHER(5,"SHOW ALL TEACHERS",new MenuShowTeacherService()),
    SHOW_ALL_STUDENT(6,"SHOW ALL STUDENTS",new MenuShowStudentService()),
    UNKNOWN;

    private String label;
    private Process service;

    private int number;

    Menu(int number, String label, Process service){
        this.label = label;
        this.service = service;
        this.number = number;
    }

    Menu() {

    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString(){
        return number+"."+label;
    }
    public void process(){
        service.process();
        MenuUtil.showMenu();
    }

    public int getNumber(){
        return this.number;
    }

    public static Menu find(int number){
        Menu[] menus = Menu.values();
        for (int i = 0; i < menus.length; i++){
            if(menus[i].getNumber() == number){
                return menus[i];
            }
        }
        return Menu.UNKNOWN;
    }

    public static void showAllMenu(){
        Menu[] menus = Menu.values();

        for (int i = 0; i < menus.length; i++){
            if (menus[i] != UNKNOWN) {
                if(menus[i] == LOGIN || menus[i] == REGISTER){
                    if(!Config.isLoggedIn()) {
                        System.out.println(menus[i]);
                    }
                }else if(Config.isLoggedIn()) {
                        System.out.println(menus[i]);
                }
            }
        }
    }
}
