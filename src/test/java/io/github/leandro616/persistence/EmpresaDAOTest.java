package io.github.leandro616.persistence;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.leandro616.enums.TipoEmpresa;
import io.github.leandro616.model.Empresa;
import io.github.leandro616.model.RamoAtividade;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EmpresaDAOTest {

    static EmpresaDAO dao;
    static EntityManagerFactory emf;
    static EntityManager em;

    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("cursoJSF");
        em = emf.createEntityManager();
        dao = new EmpresaDAO(em);
    }

    @AfterAll
    static void afterEach() {
        em.close();
        emf.close();
    }

    @Test
    void buscarPorNome() {
        List<Empresa> empresas = assertDoesNotThrow(() -> dao.buscarPorNome("Fale"));
        assertFalse(empresas.isEmpty());
    }

    @Test
    void salvarEmpresa() {
        RamoAtividade ramo = new RamoAtividade();
        ramo.setId(6);

        Empresa empresa = new Empresa();
        empresa.setCnpj("120.202.001/0002-32");
        empresa.setDataFundacao(LocalDate.now());
        empresa.setNomeFantasia("Empresa teste");
        empresa.setRamoAtividade(ramo);
        empresa.setRazaoSocial("1231dsdff");
        empresa.setTipo(TipoEmpresa.EIRELI);

        em.getTransaction().begin();
        assertDoesNotThrow(() -> dao.salvar(empresa));
        em.getTransaction().commit();
    }
}
