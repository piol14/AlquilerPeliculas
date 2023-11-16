package net.ausiasmarch.foxforumserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import net.ausiasmarch.foxforumserver.bean.UserBean;
import net.ausiasmarch.foxforumserver.entity.ClienteEntity;
import net.ausiasmarch.foxforumserver.exception.ResourceNotFoundException;
import net.ausiasmarch.foxforumserver.exception.UnauthorizedException;
import net.ausiasmarch.foxforumserver.helper.JWTHelper;
import net.ausiasmarch.foxforumserver.repository.ClienteRepository;



@Service
public class SessionService {

    @Autowired
    ClienteRepository oClienterRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    public String login(UserBean oUserBean) {
        oClienterRepository.findByUsernameAndPassword(oUserBean.getUsername(), oUserBean.getPassword())
                .orElseThrow(() -> new ResourceNotFoundException("Wrong User or password"));
        return JWTHelper.generateJWT(oUserBean.getUsername());
    }

 public String getSessionUsername() {        
    Object usernameAttribute = oHttpServletRequest.getAttribute("username");
    if (usernameAttribute instanceof String) {
        return usernameAttribute.toString();
    } else {
        // Add a log statement for debugging
        System.out.println("Attribute 'username' is not a String or is null");
        return null;
    }
}

    public ClienteEntity getSessionUser() {
        if (this.getSessionUsername() != null) {
            return oClienterRepository.findByUsername(this.getSessionUsername()).orElse(null);    
        } else {
            return null;
        }
    }

    public Boolean isSessionActive() {
        if (this.getSessionUsername() != null) {
            return oClienterRepository.findByUsername(this.getSessionUsername()).isPresent();
        } else {
            return false;
        }
    }

    public Boolean isAdmin() {
        if (this.getSessionUsername() != null) {
            ClienteEntity oUserEntityInSession = oClienterRepository.findByUsername(this.getSessionUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            return Boolean.FALSE.equals(oUserEntityInSession.getRol());
        } else {
            return false;
        }
    }

    public Boolean isUser() {
        if (this.getSessionUsername() != null) {
            ClienteEntity oUserEntityInSession = oClienterRepository.findByUsername(this.getSessionUsername())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            return Boolean.TRUE.equals(oUserEntityInSession.getRol());
        } else {
            return false;
        }
    }

    public void onlyAdmins() {
        if (!this.isAdmin()) {
            throw new UnauthorizedException("Only admins can do this");
        }
    }

    public void onlyUsers() {
        if (!this.isUser()) {
            throw new UnauthorizedException("Only users can do this");
        }
    }

    public void onlyAdminsOrUsers() {
        if (!this.isSessionActive()) {
            throw new UnauthorizedException("Only admins or users can do this");
        }
    }

    public void onlyUsersWithIisOwnData(Long id_user) {
        if (!this.isUser()) {
            throw new UnauthorizedException("Only users can do this");
        }
        if (!this.getSessionUser().getId().equals(id_user)) {
            throw new UnauthorizedException("Only users can do this");
        }
    }

    public void onlyAdminsOrUsersWithIisOwnData(Long id_user) {
        if (this.isSessionActive()) {
            if (!this.isAdmin()) {
                if (!this.isUser()) {
                    throw new UnauthorizedException("Only admins or users can do this");
                } else {
                    if (!this.getSessionUser().getId().equals(id_user)) {
                        throw new UnauthorizedException("Only admins or users with its own data can do this");
                    }
                }
            }
        } else {
            throw new UnauthorizedException("Only admins or users can do this");
        }
    }

}