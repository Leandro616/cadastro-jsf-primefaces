package io.github.leandro616.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceTest {

    @Test
    void conexaoTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("cursoJSF");
        assertNotNull(emf);

        EntityManager em = emf.createEntityManager();
        assertNotNull(em);
    }
}
