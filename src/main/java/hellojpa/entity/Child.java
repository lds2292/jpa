package hellojpa.entity;

import javax.persistence.*;

@Entity
public class Child {
    @Id
    private String id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PARENT_ID1_1", referencedColumnName = "PARENT_ID1"),
            @JoinColumn(name = "PARENT_ID2_2", referencedColumnName = "PARENT_ID2")
    })
    private Parent parent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}
