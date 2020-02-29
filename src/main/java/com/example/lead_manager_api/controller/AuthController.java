package com.example.lead_manager_api.controller;

import com.example.lead_manager_api.dto.UserDto;
import com.example.lead_manager_api.error.ServiceException;
import com.example.lead_manager_api.model.User;
import com.example.lead_manager_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "register", consumes = "application/json")
    public Map register(@RequestBody UserDto userDto, HttpServletRequest request) {
        Map result = new HashMap();

        User user = User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
        User savedUser = userRepository.save(user);
        savedUser.setPassword(null);

        // 生成TOKEN
        String token = UUID.randomUUID().toString().replaceAll("-","");

        result.put("user", savedUser);
        result.put("token", token);

        request.getSession().setAttribute("user", result);

        return result;
    }

    @PostMapping(path = "login", consumes = "application/json")
    public Map login(@RequestBody UserDto userDto, HttpServletRequest request) {
        Map result = new HashMap();

        User user = userRepository.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
        if (user == null) {
            throw new ServiceException("用户名或密码错误");
        }

        user.setPassword(null);
        // 生成TOKEN
        String token = UUID.randomUUID().toString().replaceAll("-","");
        result.put("user", user);
        result.put("token", token);
        request.getSession().setAttribute("user", result);
        return result;
    }

    @GetMapping("user")
    public Map getUser(@RequestHeader("Authorization") String token, HttpServletRequest request) {
        Map result = new HashMap();
        Map UserSession = (Map) request.getSession().getAttribute("user");
        if (UserSession != null) {
            String serverSideToken = (String) UserSession.get("token");
            if (serverSideToken != null && serverSideToken.equals(token)) {
                User user = (User) UserSession.get("user");
                result.put("id", user.getId());
                result.put("username", user.getUsername());
                result.put("email", user.getEmail());
                return result;
            }
        }
        throw new ServiceException("认证失败");
    }

    @PostMapping(path = "logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(@RequestHeader("Authorization") String token, HttpServletRequest request) {
        Map UserSession = (Map) request.getSession().getAttribute("user");
        if (UserSession != null) {
            String serverSideToken = (String) UserSession.get("token");
            if (serverSideToken != null && serverSideToken.equals(token)) {
                request.getSession().invalidate();
            }
        }
    }
}
