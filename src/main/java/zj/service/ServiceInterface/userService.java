package zj.service.ServiceInterface;

import zj.entity.user;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by zj on 2017-2-23.
 */
public interface userService {
	user getUser(int id);
	boolean login(user _user);
	void addSession(HttpServletRequest httpServletRequest, user _user);
	void logout();
	boolean insertUser(user _user);
	void update(user _user, int id, String name);
	ArrayList<user> findAll();
}
