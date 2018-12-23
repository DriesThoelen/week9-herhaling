package be.pxl.ja.ticketsystem.mapper;

import be.pxl.ja.ticketsystem.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserMapper implements Mapper<User> {
    @Override
    public User map(String[] lines) {
        return new User(lines[1], lines[2], LocalDate.parse(lines[3], DateTimeFormatter.ofPattern("ddMMuuuu")));
    }
}
