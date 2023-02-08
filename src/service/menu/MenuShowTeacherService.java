package service.menu;

import bean.Config;
import bean.Teacher;
import service.inter.menu.MenuShowTeacherServiceInter;

public class MenuShowTeacherService implements MenuShowTeacherServiceInter {
    @Override
    public void processLogic() {
        Teacher[] all = Config.instance().getTeachers();
        for (int i = 0; i < all.length; i++) {
            System.out.println(all[i]);
        }
    }
}
