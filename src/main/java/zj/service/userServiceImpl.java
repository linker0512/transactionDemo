package zj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import zj.entity.user;
import zj.mapping.UserMapping;
import zj.service.ServiceInterface.userService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by zj on 2017-2-23.
 */
@Service
public class userServiceImpl implements userService {

    public static String currentUserName;

	@Autowired
    UserMapping userMapping;

	@Override
	public user getUser(int id) {
		return userMapping.findById(id);
	}

	@Override
	public boolean login(user _user) {
	    currentUserName = _user.getName();
		return userMapping.findbyUser(_user.getName() ,_user.getPassword()) > 0 ? true : false;
	}

    @Override
    public void addSession(HttpServletRequest httpServletRequest, user _user) {
        HttpSession httpSession = httpServletRequest.getSession(true);
        httpSession.setAttribute("current_user" , _user);
        httpSession.setMaxInactiveInterval(1000);
    }

    @Override
	public void logout() {

	}

	@Override
	public boolean insertUser(user _user) {
	    boolean flg =  userMapping.findbyUser(_user.getName() ,_user.getPassword()) > 0 ? true : false;
	    if(flg) {
            return flg;
        }else {
            userMapping.insert(_user.getName() , _user.getPassword());
            System.out.println("register");
            return flg;
        }
	}

	@Override
	public void update(user _user, int id , String name) {

	}

    @Override
//    @Cacheable(value = "test")
    public ArrayList<user> findAll() {
        return userMapping.findAll();
    }

}
