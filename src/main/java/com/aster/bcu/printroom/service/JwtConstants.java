package com.aster.bcu.printroom.service;

public interface JwtConstants {
    String AUTH_HEADER = "Authorization";

    String SECRET = "defaultSecret";

    String AUTH_PATH = "/attackApi/auth";

    Long EXPIRATION = 604800L;

}
