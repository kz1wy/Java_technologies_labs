import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Manufacture")

// POJO class
public class Manufacture {

    @Id @Column(name = "id") private int id;

    @Column(name = "name") private String name;

    @Column(name = "location") private String location;

    @Column(name = "employee") private int employee;

    public String getName() {
        return name;
    }

    public int getEmployee() {
        return employee;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}