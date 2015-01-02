package it.spring.web.model;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;
 

@Entity
public class Person extends AbstractPersistable<Integer> {

    private static final long serialVersionUID = -8712872385957386182L;

    private String firstName = null;
    private String lastName = null;

    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="PERSON_ID", nullable=false)
    private Set<Address> addresses = null;
    
    private Date created = null;

    /**
     * Gets first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets list of <code>Address</code>es.
     */
    public Set<Address> getAddresses() {
        return addresses;
    }

    /**
     * Sets list of <code>Address</code>es.
     */
    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    /**
     * Gets date created.
     */
    public Date getCreated() {
        return created;
    }

    /**
     * Sets date created.
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    public Address findAddressById(Integer id) {
        Address result = null;

        if (addresses != null) {
            for (Address address : addresses) {
                if (address.getId().equals(id)) {
                    result = address;

                    break;
                }
            }
        }

        return result;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(this.getClass().getName() + "-");
        sb.append("  id=" + getId());
        sb.append("  firstName=" + firstName);
        sb.append("  lastName=" + lastName);

        sb.append("  addresses=[");

        if (addresses != null) {
            for (Address address : addresses) {
                sb.append(address.toString());
            }
        }

        sb.append("]");

        sb.append("  created=" + created);

        return sb.toString();
    }

}

