package fr.kreatik.kintro.web.rest;
import fr.kreatik.kintro.domain.DocumentEmployee;
import fr.kreatik.kintro.repository.DocumentEmployeeRepository;
import fr.kreatik.kintro.web.rest.errors.BadRequestAlertException;
import fr.kreatik.kintro.web.rest.util.HeaderUtil;
import fr.kreatik.kintro.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DocumentEmployee.
 */
@RestController
@RequestMapping("/api")
public class DocumentEmployeeResource {

    private final Logger log = LoggerFactory.getLogger(DocumentEmployeeResource.class);

    private static final String ENTITY_NAME = "documentEmployee";

    private final DocumentEmployeeRepository documentEmployeeRepository;

    public DocumentEmployeeResource(DocumentEmployeeRepository documentEmployeeRepository) {
        this.documentEmployeeRepository = documentEmployeeRepository;
    }

    /**
     * POST  /document-employees : Create a new documentEmployee.
     *
     * @param documentEmployee the documentEmployee to create
     * @return the ResponseEntity with status 201 (Created) and with body the new documentEmployee, or with status 400 (Bad Request) if the documentEmployee has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/document-employees")
    public ResponseEntity<DocumentEmployee> createDocumentEmployee(@RequestBody DocumentEmployee documentEmployee) throws URISyntaxException {
        log.debug("REST request to save DocumentEmployee : {}", documentEmployee);
        if (documentEmployee.getId() != null) {
            throw new BadRequestAlertException("A new documentEmployee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DocumentEmployee result = documentEmployeeRepository.save(documentEmployee);
        return ResponseEntity.created(new URI("/api/document-employees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /document-employees : Updates an existing documentEmployee.
     *
     * @param documentEmployee the documentEmployee to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated documentEmployee,
     * or with status 400 (Bad Request) if the documentEmployee is not valid,
     * or with status 500 (Internal Server Error) if the documentEmployee couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/document-employees")
    public ResponseEntity<DocumentEmployee> updateDocumentEmployee(@RequestBody DocumentEmployee documentEmployee) throws URISyntaxException {
        log.debug("REST request to update DocumentEmployee : {}", documentEmployee);
        if (documentEmployee.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DocumentEmployee result = documentEmployeeRepository.save(documentEmployee);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, documentEmployee.getId().toString()))
            .body(result);
    }

    /**
     * GET  /document-employees : get all the documentEmployees.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of documentEmployees in body
     */
    @GetMapping("/document-employees")
    public ResponseEntity<List<DocumentEmployee>> getAllDocumentEmployees(Pageable pageable) {
        log.debug("REST request to get a page of DocumentEmployees");
        Page<DocumentEmployee> page = documentEmployeeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/document-employees");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /document-employees/:id : get the "id" documentEmployee.
     *
     * @param id the id of the documentEmployee to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the documentEmployee, or with status 404 (Not Found)
     */
    @GetMapping("/document-employees/{id}")
    public ResponseEntity<DocumentEmployee> getDocumentEmployee(@PathVariable String id) {
        log.debug("REST request to get DocumentEmployee : {}", id);
        Optional<DocumentEmployee> documentEmployee = documentEmployeeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(documentEmployee);
    }

    /**
     * DELETE  /document-employees/:id : delete the "id" documentEmployee.
     *
     * @param id the id of the documentEmployee to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/document-employees/{id}")
    public ResponseEntity<Void> deleteDocumentEmployee(@PathVariable String id) {
        log.debug("REST request to delete DocumentEmployee : {}", id);
        documentEmployeeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
