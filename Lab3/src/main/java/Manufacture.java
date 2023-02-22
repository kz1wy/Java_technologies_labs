import javax.persistence.*;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "Manufacture")

// POJO class
public class Manufacture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", length = 128)
    private String name;


    @Column(name = "location") private String location;

    @Column(name = "employee", nullable = false) private int employee;
    @OneToMany(mappedBy = "manufacture", fetch = FetchType.EAGER)
    private List<Phone> phones;

    public Manufacture(String name, String location, int employee) {
        this.name = name;
        this.location = location;
        this.employee = employee;
    }

    public Manufacture() {

    }

    public String getName() {
        return name;
    }

    public int getEmployee() {
        return employee;
    }

    public long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}