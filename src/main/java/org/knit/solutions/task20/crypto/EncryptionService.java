package org.knit.solutions.task20.crypto;

public interface EncryptionService {
    String encrypt(String data, char[] masterPassword);
    String decrypt(String encryptedData, char[] masterPassword);
} 