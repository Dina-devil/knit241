package org.knit.solutions.task20.repository;

import org.knit.solutions.task20.model.PasswordEntry;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryPasswordRepository implements PasswordRepository {
    private final Map<String, PasswordEntry> entries = new ConcurrentHashMap<>();

    @Override
    public void save(PasswordEntry entry) {
        entries.put(entry.getSite(), entry);
    }

    @Override
    public Optional<PasswordEntry> findBySite(String site) {
        return Optional.ofNullable(entries.get(site));
    }

    @Override
    public List<PasswordEntry> findAll() {
        return new ArrayList<>(entries.values());
    }

    @Override
    public void delete(String site) {
        entries.remove(site);
    }

    @Override
    public void saveAll(List<PasswordEntry> entries) {
        entries.forEach(this::save);
    }
} 