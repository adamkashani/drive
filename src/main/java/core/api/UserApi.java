package core.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.dao.IUserDao;
import core.entity.UserEntity;
import core.exception.DriveException;
import core.javaBean.User;
import core.javaBean.UserLogin;
import core.logic.TokenService;
import core.logic.UserService;

@RestController
@RequestMapping("")
public class UserApi {

	private static final int HOUR = 60 * 60;

	private UserService userService;

	private IUserDao iUserDao;

	private TokenService tokenService;

	@Autowired
	public UserApi(UserService userService, TokenService tokenService, IUserDao iUserDao) {
		super();
		this.userService = userService;
		this.tokenService = tokenService;
		this.iUserDao = iUserDao;
	}

	@PostMapping
	@RequestMapping("/registration")
	public String create(@RequestBody User user) throws DriveException {
		System.out.println(user);
		userService.create(user);
		return "registration succeeded";
	}

	@GetMapping
	@RequestMapping(path = "/login")
	public String login(@RequestBody UserLogin userLogin, HttpServletResponse httpServletResponse) {
		String token = userService.login(userLogin);
		Cookie cookie = new Cookie("token", token);
		System.out.println(token);
		cookie.setMaxAge(HOUR);
		httpServletResponse.addCookie(cookie);
//		UserEntity entity =  iUserDao.findByName(userLogin.getName());
//		return entity.getFileEntities();
		return "login succeeded";
	}

	@GetMapping
	@RequestMapping(path = "/logout")
	public String logout(@CookieValue(name = "token") String token) {
		System.out.println("from logout the token cookie : " + token);
		tokenService.remove(token);
		return "logout succeeded";
	}

}
