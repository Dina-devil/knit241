package org.knit.solutions.task20.crypto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

@Service
public class FileEncryptionService {
    private static final Logger logger = LoggerFactory.getLogger(FileEncryptionService.class);
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String KEY_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int ITERATION_COUNT = 65536;
    private static final int KEY_LENGTH = 256;
    private static final int SALT_LENGTH = 16;
    private static final int IV_LENGTH = 16;

    public void encryptFile(String inputFile, String outputFile, char[] masterPassword) {
        try {
            byte[] salt = generateSalt();
            byte[] iv = generateIV();
            SecretKey key = generateKey(masterPassword, salt);

            try (FileInputStream fis = new FileInputStream(inputFile);
                 FileOutputStream fos = new FileOutputStream(outputFile)) {
                
                fos.write(salt);
                fos.write(iv);

                Cipher cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));

                try (CipherOutputStream cos = new CipherOutputStream(fos, cipher)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fis.read(buffer)) != -1) {
                        cos.write(buffer, 0, bytesRead);
                    }
                }
            }
            logger.info("Файл успешно зашифрован: {}", outputFile);
        } catch (Exception e) {
            logger.error("Ошибка при шифровании файла: {}", e.getMessage());
            throw new RuntimeException("Ошибка при шифровании файла", e);
        }
    }

    public void decryptFile(String inputFile, String outputFile, char[] masterPassword) {
        try {
            try (FileInputStream fis = new FileInputStream(inputFile)) {
                byte[] salt = new byte[SALT_LENGTH];
                byte[] iv = new byte[IV_LENGTH];
                
                if (fis.read(salt) != SALT_LENGTH || fis.read(iv) != IV_LENGTH) {
                    throw new IOException("Некорректный формат файла");
                }

                SecretKey key = generateKey(masterPassword, salt);
                Cipher cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));

                try (CipherInputStream cis = new CipherInputStream(fis, cipher);
                     FileOutputStream fos = new FileOutputStream(outputFile)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = cis.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                }
            }
            logger.info("Файл успешно расшифрован: {}", outputFile);
        } catch (Exception e) {
            logger.error("Ошибка при расшифровке файла: {}", e.getMessage());
            throw new RuntimeException("Ошибка при расшифровке файла", e);
        }
    }

    private SecretKey generateKey(char[] masterPassword, byte[] salt) throws Exception {
        KeySpec spec = new PBEKeySpec(masterPassword, salt, ITERATION_COUNT, KEY_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        byte[] keyBytes = factory.generateSecret(spec).getEncoded();
        return new SecretKeySpec(keyBytes, "AES");
    }

    private byte[] generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    private byte[] generateIV() {
        byte[] iv = new byte[IV_LENGTH];
        new SecureRandom().nextBytes(iv);
        return iv;
    }
} 