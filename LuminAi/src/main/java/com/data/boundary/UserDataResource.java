package com.data.boundary;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("api/user")
public class UserDataResource {

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    @RolesAllowed("user")
    @Path("username")
    public String getUsername() {
        return securityIdentity.getPrincipal().getName();
    }

    @GET
    @RolesAllowed("user")
    @Path("roles")
    public List<String> getUserInfo() {
        return securityIdentity.getRoles().stream().toList();
    }
}
