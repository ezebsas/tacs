package com.utn.tacs.tacsthree.auth;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;

@NameBinding
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Secured {
}
