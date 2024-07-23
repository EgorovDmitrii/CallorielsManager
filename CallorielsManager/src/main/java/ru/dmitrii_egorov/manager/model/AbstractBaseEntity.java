package ru.dmitrii_egorov.manager.model;

public class AbstractBaseEntity {
    protected Integer id;

    public AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return id == null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [id=" + id + "]";
    }


}
