import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Manufacture.class)
public abstract class Manufacture_ {

	public static volatile SingularAttribute<Manufacture, String> name;
	public static volatile ListAttribute<Manufacture, Phone> phones;
	public static volatile SingularAttribute<Manufacture, String> location;
	public static volatile SingularAttribute<Manufacture, Long> id;
	public static volatile SingularAttribute<Manufacture, Integer> employee;

	public static final String NAME = "name";
	public static final String PHONES = "phones";
	public static final String LOCATION = "location";
	public static final String ID = "id";
	public static final String EMPLOYEE = "employee";

}

