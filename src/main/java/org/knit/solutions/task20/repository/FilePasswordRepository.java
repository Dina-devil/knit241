package org.knit.solutions.task20.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.knit.solutions.task20.model.PasswordEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.Primary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Primary
public class FilePasswordRepository implements PasswordRepository {
    private static final Logger logger = LoggerFactory.getLogger(FilePasswordRepository.class);
    private static final String STORAGE_FILE = "passwords.json";
    private final Map<String, PasswordEntry> entries = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FilePasswordRepository() {
        loadFromFile();
    }

    @Override
    public void save(PasswordEntry entry) {
        entries.put(entry.getSite(), entry);
        saveToFile();
        logger.info("Сохранена запись для сайта: {}", entry.getSite());
    }

    @Override
    public void saveAll(List<PasswordEntry> entries) {
        entries.forEach(this::save);
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
        saveToFile();
        logger.info("Удалена запись для сайта: {}", site);
    }

    private void loadFromFile() {
        File file = new File(STORAGE_FILE);
        if (file.exists()) {
            try {
                CollectionType type = objectMapper.getTypeFactory().constructCollectionType(List.class, PasswordEntry.class);
                List<PasswordEntry> loadedEntries = objectMapper.readValue(file, type);
                loadedEntries.forEach(entry -> entries.put(entry.getSite(), entry));
                logger.info("Загружено {} записей из файла", loadedEntries.size());
            } catch (IOException e) {
                logger.error("Ошибка при загрузке данных из файла: {}", e.getMessage());
            }
        }
    }

    private void saveToFile() {
        try {
            objectMapper.writeValue(new File(STORAGE_FILE), new ArrayList<>(entries.values()));
            logger.info("Данные сохранены в файл");
        } catch (IOException e) {
            logger.error("Ошибка при сохранении данных в файл: {}", e.getMessage());
        }
    }
} 