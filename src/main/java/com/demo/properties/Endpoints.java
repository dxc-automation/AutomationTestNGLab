package com.demo.properties;

import java.net.URI;

public class Endpoints {

    public static final String host = "thinking-tester-contact-list.herokuapp.com";


    // USERS
    public static final String user_add     = "/users";
    public static final String user_profile = "/users/me";
    public static final String user_update  = "/users/me";
    public static final String user_delete  = "/users/me";
    public static final String user_logout  = "/users/logout";
    public static final String user_login   = "/users/login";

    // CONTACTS
    public static final String contact_add = "/contacts";
    public static final String contact_update = "/contacts/";
}

