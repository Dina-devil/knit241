package org.knit.solutions.task20.service;

import org.knit.solutions.task20.clipboard.ClipboardService;
import org.knit.solutions.task20.crypto.EncryptionService;
import org.knit.solutions.task20.model.PasswordEntry;
import org.knit.solutions.task20.repository.PasswordRepository;
import org.knit.solutions.task20.security.MasterPasswordHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordService {
    private static final Logger logger = LoggerFactory.getLogger(PasswordService.class);
    
    private final PasswordRepository repository;
    private final EncryptionService encryptionService;
    private final ClipboardService clipboardService;
    private final MasterPasswordHolder masterPasswordHolder;

    public PasswordService(PasswordRepository repository,
                          EncryptionService encryptionService,
                          ClipboardService clipboardService,
                          MasterPasswordHolder masterPasswordHolder) {
        this.repository = repository;
        this.encryptionService = encryptionService;
        this.clipboardService = clipboardService;
        this.masterPasswordHolder = masterPasswordHolder;
    }

    public void addEntry(String site, String login, String password) {
        String encryptedPassword = encryptionService.encrypt(password, masterPasswordHolder.getMasterPassword());
        PasswordEntry entry = new PasswordEntry(site, login, encryptedPassword);
        repository.save(entry);
        logger.info("Added new entry for site: {}", site);
    }

    public List<PasswordEntry> getAllEntries() {
        return repository.findAll();
    }

    public void copyPasswordToClipboard(String site) {
        repository.findBySite(site).ifPresent(entry -> {
            String decryptedPassword = encryptionService.decrypt(entry.getEncryptedPassword(), 
                                                               masterPasswordHolder.getMasterPassword());
            clipboardService.copyToClipboard(decryptedPassword);
            logger.info("Password for site {} copied to clipboard", site);
        });
    }

    public void deleteEntry(String site) {
        repository.delete(site);
        logger.info("Deleted entry for site: {}", site);
    }
} 