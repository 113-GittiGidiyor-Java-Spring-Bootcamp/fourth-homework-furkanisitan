package dev.patika.schoolmanagementsystem.entities.abtracts;

public abstract class EntityUtility<T extends Entity<?>> {

    protected final T clazz;

    protected EntityUtility(T clazz) {
        this.clazz = clazz;
    }
}
