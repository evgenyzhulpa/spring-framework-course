package com.example.SpringWebApp.repository;

import com.example.SpringWebApp.dto.Contact;
import com.example.SpringWebApp.repository.mapper.ContactMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Primary
public class DatabaseRepository implements ContactRepository {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Contact> findAll() {
        String sql = "SELECT * FROM contact";
        return jdbcTemplate.query(sql, new ContactMapper());
    }

    @Override
    public Optional<Contact> findById(UUID id) {
        String sql = "SELECT * FROM contact WHERE id = ?";
        Contact contact = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        sql,
                        new ArgumentPreparedStatementSetter(new Object[]{id}),
                        new RowMapperResultSetExtractor<>(new ContactMapper(), 1)
                )
        );

        return Optional.ofNullable(contact);
    }

    @Override
    public Contact save(Contact contact) {
        String sql = "INSERT INTO contact (firstName, lastName, email, phone, id) VALUES (?, ?, ?, ?, ?)";
        if (findById(contact.getId()).isPresent()) {
            sql = "UPDATE contact SET firstName = ?, lastName = ?, email = ?, phone = ? WHERE id = ?";
        }
        jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone(), contact.getId());

        return contact;
    }

    @Override
    public void delete(UUID id) {
        String sql = "DELETE FROM contact WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
