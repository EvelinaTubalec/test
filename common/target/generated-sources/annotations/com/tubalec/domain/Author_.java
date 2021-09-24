package com.tubalec.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Author.class)
public abstract class Author_ {

	public static volatile SingularAttribute<Author, String> surname;
	public static volatile SingularAttribute<Author, String> name;
	public static volatile SingularAttribute<Author, String> middleName;
	public static volatile SingularAttribute<Author, Long> id;
	public static volatile SingularAttribute<Author, LocalDate> birthDate;

	public static final String SURNAME = "surname";
	public static final String NAME = "name";
	public static final String MIDDLE_NAME = "middleName";
	public static final String ID = "id";
	public static final String BIRTH_DATE = "birthDate";

}

