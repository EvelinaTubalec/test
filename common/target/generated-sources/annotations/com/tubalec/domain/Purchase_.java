package com.tubalec.domain;

import java.sql.Timestamp;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Purchase.class)
public abstract class Purchase_ {

	public static volatile SingularAttribute<Purchase, LocalDate> dateOfBuying;
	public static volatile SingularAttribute<Purchase, Boolean> isDeleted;
	public static volatile SingularAttribute<Purchase, Timestamp> created;
	public static volatile SingularAttribute<Purchase, Long> id;
	public static volatile SingularAttribute<Purchase, Long> userId;
	public static volatile SingularAttribute<Purchase, Long> bookId;
	public static volatile SingularAttribute<Purchase, Timestamp> changed;

	public static final String DATE_OF_BUYING = "dateOfBuying";
	public static final String IS_DELETED = "isDeleted";
	public static final String CREATED = "created";
	public static final String ID = "id";
	public static final String USER_ID = "userId";
	public static final String BOOK_ID = "bookId";
	public static final String CHANGED = "changed";

}

