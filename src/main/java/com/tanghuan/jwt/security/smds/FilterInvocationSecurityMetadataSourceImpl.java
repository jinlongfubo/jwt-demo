package com.tanghuan.jwt.security.smds;

import com.tanghuan.jwt.entity.domain.Permission;
import com.tanghuan.jwt.entity.domain.Resource;
import com.tanghuan.jwt.entity.domain.Role;
import com.tanghuan.jwt.repository.ResourceRepository;
import com.tanghuan.jwt.security.entity.GrantedAuthorityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Arthur on 2017/3/14.
 */
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private ResourceRepository resourceRepository;


    // URL 和角色对应
    private final Map<String, Collection<ConfigAttribute>> requestMap;

    public FilterInvocationSecurityMetadataSourceImpl() {
        this.requestMap = new LinkedHashMap<>();
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

        List<Resource> resources = resourceRepository.findByEnabledTrue();
        Set<String> roleNames = new HashSet<>();
        for (Resource resource : resources) {
            for (Permission permission : resource.getPermissions()) {
                if (permission.isEnabled()) {
                    for (Role role : permission.getRoles()) {
                        if (role.isEnabled() && roleNames.add(role.getName())) {
                            allAttributes.add(new GrantedAuthorityImpl(role.getName(), role.isEnabled()));
                        }
                    }
                }
            }
        }

        return allAttributes;
    }

    public Collection<ConfigAttribute> getAttributes(Object object) {
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
            if (entry.getKey().matches(request.getRequestURI())) {
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

}
