package com.data.boundary;

import io.quarkus.oidc.OidcSession;
import io.quarkus.security.Authenticated;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("api/user")
public class UserDataResource {

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    OidcSession oidcSession;

    @GET
    @Authenticated
    @Path("username")
    public String getUsername() {
        return securityIdentity.getPrincipal().getName();
    }

    @POST
    @Authenticated
    @Path("logout")
    public void logout() {
        oidcSession.logout();
    }
}
