package crypto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String password;
    private String bigNumber;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getBigNumber() {
        return bigNumber;
    }

    public User setBigNumber(String bigNumber) {
        this.bigNumber = bigNumber;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getPassword().equals(user.getPassword())) return false;
        return getBigNumber().equals(user.getBigNumber());
    }

    @Override
    public int hashCode() {
        int result = getPassword().hashCode();
        result = 31 * result + getBigNumber().hashCode();
        return result;
    }
}
