package fr.kreatik.kintro.web.rest;

import fr.kreatik.kintro.KintroApp;

import fr.kreatik.kintro.domain.DocumentEmployee;
import fr.kreatik.kintro.repository.DocumentEmployeeRepository;
import fr.kreatik.kintro.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import java.util.List;


import static fr.kreatik.kintro.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DocumentEmployeeResource REST controller.
 *
 * @see DocumentEmployeeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = KintroApp.class)
public class DocumentEmployeeResourceIntTest {

    private static final String DEFAULT_DOC_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DOC_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DOCPATH = "AAAAAAAAAA";
    private static final String UPDATED_DOCPATH = "BBBBBBBBBB";

    @Autowired
    private DocumentEmployeeRepository documentEmployeeRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restDocumentEmployeeMockMvc;

    private DocumentEmployee documentEmployee;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DocumentEmployeeResource documentEmployeeResource = new DocumentEmployeeResource(documentEmployeeRepository);
        this.restDocumentEmployeeMockMvc = MockMvcBuilders.standaloneSetup(documentEmployeeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DocumentEmployee createEntity() {
        DocumentEmployee documentEmployee = new DocumentEmployee()
            .docName(DEFAULT_DOC_NAME)
            .description(DEFAULT_DESCRIPTION)
            .docpath(DEFAULT_DOCPATH);
        return documentEmployee;
    }

    @Before
    public void initTest() {
        documentEmployeeRepository.deleteAll();
        documentEmployee = createEntity();
    }

    @Test
    public void createDocumentEmployee() throws Exception {
        int databaseSizeBeforeCreate = documentEmployeeRepository.findAll().size();

        // Create the DocumentEmployee
        restDocumentEmployeeMockMvc.perform(post("/api/document-employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(documentEmployee)))
            .andExpect(status().isCreated());

        // Validate the DocumentEmployee in the database
        List<DocumentEmployee> documentEmployeeList = documentEmployeeRepository.findAll();
        assertThat(documentEmployeeList).hasSize(databaseSizeBeforeCreate + 1);
        DocumentEmployee testDocumentEmployee = documentEmployeeList.get(documentEmployeeList.size() - 1);
        assertThat(testDocumentEmployee.getDocName()).isEqualTo(DEFAULT_DOC_NAME);
        assertThat(testDocumentEmployee.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDocumentEmployee.getDocpath()).isEqualTo(DEFAULT_DOCPATH);
    }

    @Test
    public void createDocumentEmployeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = documentEmployeeRepository.findAll().size();

        // Create the DocumentEmployee with an existing ID
        documentEmployee.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restDocumentEmployeeMockMvc.perform(post("/api/document-employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(documentEmployee)))
            .andExpect(status().isBadRequest());

        // Validate the DocumentEmployee in the database
        List<DocumentEmployee> documentEmployeeList = documentEmployeeRepository.findAll();
        assertThat(documentEmployeeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    public void getAllDocumentEmployees() throws Exception {
        // Initialize the database
        documentEmployeeRepository.save(documentEmployee);

        // Get all the documentEmployeeList
        restDocumentEmployeeMockMvc.perform(get("/api/document-employees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(documentEmployee.getId())))
            .andExpect(jsonPath("$.[*].docName").value(hasItem(DEFAULT_DOC_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].docpath").value(hasItem(DEFAULT_DOCPATH.toString())));
    }
    
    @Test
    public void getDocumentEmployee() throws Exception {
        // Initialize the database
        documentEmployeeRepository.save(documentEmployee);

        // Get the documentEmployee
        restDocumentEmployeeMockMvc.perform(get("/api/document-employees/{id}", documentEmployee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(documentEmployee.getId()))
            .andExpect(jsonPath("$.docName").value(DEFAULT_DOC_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.docpath").value(DEFAULT_DOCPATH.toString()));
    }

    @Test
    public void getNonExistingDocumentEmployee() throws Exception {
        // Get the documentEmployee
        restDocumentEmployeeMockMvc.perform(get("/api/document-employees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateDocumentEmployee() throws Exception {
        // Initialize the database
        documentEmployeeRepository.save(documentEmployee);

        int databaseSizeBeforeUpdate = documentEmployeeRepository.findAll().size();

        // Update the documentEmployee
        DocumentEmployee updatedDocumentEmployee = documentEmployeeRepository.findById(documentEmployee.getId()).get();
        updatedDocumentEmployee
            .docName(UPDATED_DOC_NAME)
            .description(UPDATED_DESCRIPTION)
            .docpath(UPDATED_DOCPATH);

        restDocumentEmployeeMockMvc.perform(put("/api/document-employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDocumentEmployee)))
            .andExpect(status().isOk());

        // Validate the DocumentEmployee in the database
        List<DocumentEmployee> documentEmployeeList = documentEmployeeRepository.findAll();
        assertThat(documentEmployeeList).hasSize(databaseSizeBeforeUpdate);
        DocumentEmployee testDocumentEmployee = documentEmployeeList.get(documentEmployeeList.size() - 1);
        assertThat(testDocumentEmployee.getDocName()).isEqualTo(UPDATED_DOC_NAME);
        assertThat(testDocumentEmployee.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDocumentEmployee.getDocpath()).isEqualTo(UPDATED_DOCPATH);
    }

    @Test
    public void updateNonExistingDocumentEmployee() throws Exception {
        int databaseSizeBeforeUpdate = documentEmployeeRepository.findAll().size();

        // Create the DocumentEmployee

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDocumentEmployeeMockMvc.perform(put("/api/document-employees")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(documentEmployee)))
            .andExpect(status().isBadRequest());

        // Validate the DocumentEmployee in the database
        List<DocumentEmployee> documentEmployeeList = documentEmployeeRepository.findAll();
        assertThat(documentEmployeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteDocumentEmployee() throws Exception {
        // Initialize the database
        documentEmployeeRepository.save(documentEmployee);

        int databaseSizeBeforeDelete = documentEmployeeRepository.findAll().size();

        // Delete the documentEmployee
        restDocumentEmployeeMockMvc.perform(delete("/api/document-employees/{id}", documentEmployee.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DocumentEmployee> documentEmployeeList = documentEmployeeRepository.findAll();
        assertThat(documentEmployeeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocumentEmployee.class);
        DocumentEmployee documentEmployee1 = new DocumentEmployee();
        documentEmployee1.setId("id1");
        DocumentEmployee documentEmployee2 = new DocumentEmployee();
        documentEmployee2.setId(documentEmployee1.getId());
        assertThat(documentEmployee1).isEqualTo(documentEmployee2);
        documentEmployee2.setId("id2");
        assertThat(documentEmployee1).isNotEqualTo(documentEmployee2);
        documentEmployee1.setId(null);
        assertThat(documentEmployee1).isNotEqualTo(documentEmployee2);
    }
}
