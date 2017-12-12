package com.ericliu.dubbo.provider.auth.service;

import com.ericliu.dubbo.api.auth.dto.AuthInfoDTO;
import com.ericliu.dubbo.api.auth.service.AuthService;
import com.ericliu.dubbo.api.auth.dto.AuthDTO;
import com.ericliu.dubbo.provider.auth.domain.Auth;
import com.ericliu.dubbo.provider.auth.domain.AuthRole;
import com.ericliu.dubbo.provider.auth.domain.Permission;
import com.ericliu.dubbo.provider.auth.mapper.AuthMapper;
import com.ericliu.dubbo.provider.auth.repo.AuthRepository;
import com.ericliu.dubbo.provider.auth.repo.AuthRoleRepository;
import com.ericliu.dubbo.provider.auth.repo.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/11/30
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final AuthRoleRepository authRoleRepository;
    private final PermissionRepository permissionRepository;

    public AuthServiceImpl(AuthRepository authRepository, AuthRoleRepository authRoleRepository, PermissionRepository permissionRepository) {
        this.authRepository = authRepository;
        this.authRoleRepository = authRoleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public AuthDTO saveAuth(AuthDTO authDTO) {
        Auth auth = AuthMapper.INSTANCE.toAuthEntity(authDTO);
        auth = authRepository.save(auth);
        return AuthMapper.INSTANCE.toAuthDTO(auth);
    }

    @Override
    public AuthDTO findAuthDTO(String loginName) {
        Auth auth = authRepository.findByLoginName(loginName);

        if (auth != null) {
            return AuthMapper.INSTANCE.toAuthDTO(auth);
        }

        return null;
    }


    @Override
    public AuthInfoDTO findAuthInfoByLoginName(String loginName) {
        AuthDTO authDTO = findAuthDTO(loginName);

        if (authDTO == null) {
            return null;
        }

        List<AuthRole> authRoles = authRoleRepository.findAllByUid(authDTO.getId());
        Set<String> roles = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        authRoles.forEach(role -> {
            roles.add(role.getRole().name());

            List<Permission> authPermissions = permissionRepository.findAllByRole(role.getRole());
            authPermissions.forEach(permission -> {
                permissionSet.add(permission.getPermission());
            });
        });

        AuthInfoDTO authInfoDTO = new AuthInfoDTO();
        authInfoDTO.setAuthDTO(authDTO);
        authInfoDTO.setPermissions(permissionSet);
        authInfoDTO.setRoles(roles);

        return authInfoDTO;
    }
}
