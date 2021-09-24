package com.tubalec.domain;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> address;
	public static volatile SingularAttribute<User, String> telephoneNumber;
	public static volatile SingularAttribute<User, Float> money;
	public static volatile SingularAttribute<User, String> surname;
	public static volatile SingularAttribute<User, Timestamp> created;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> login;
	public static volatile SingularAttribute<User, Timestamp> changed;

	public static final String PASSWORD = "password";
	public static final String ADDRESS = "address";
	public static final String TELEPHONE_NUMBER = "telephoneNumber";
	public static final String MONEY = "money";
	public static final String SURNAME = "surname";
	public static final String CREATED = "created";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String LOGIN = "login";
	public static final String CHANGED = "changed";

}

