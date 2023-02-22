import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Phone.class)
public abstract class Phone_ {

	public static volatile SingularAttribute<Phone, String> country;
	public static volatile SingularAttribute<Phone, Manufacture> manufacture;
	public static volatile SingularAttribute<Phone, Integer> quantity;
	public static volatile SingularAttribute<Phone, String> color;
	public static volatile SingularAttribute<Phone, Integer> price;
	public static volatile SingularAttribute<Phone, String> name;
	public static volatile SingularAttribute<Phone, Long> id;

	public static final String COUNTRY = "country";
	public static final String MANUFACTURE = "manufacture";
	public static final String QUANTITY = "quantity";
	public static final String COLOR = "color";
	public static final String PRICE = "price";
	public static final String NAME = "name";
	public static final String ID = "id";

}

