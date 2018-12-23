package be.pxl.ja.ticketsystem.mapper;

public interface Mapper<T> {
    T map(String[] lines);
}
