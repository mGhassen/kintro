package fr.kreatik.kintro.web.rest;
import fr.kreatik.kintro.domain.Document;
import fr.kreatik.kintro.repository.DocumentRepository;
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
 * REST controller for managing Document.
 */
@RestController
@RequestMapping("/api")
public class DocumentResource {

    private final Logger log = LoggerFactory.getLogger(DocumentResource.class);

    private static final String ENTITY_NAME = "document";

    private final DocumentRepository documentRepository;

    public DocumentResource(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    /**
     * POST  /documents : Create a new document.
     *
     * @param document the document to create
     * @return the ResponseEntity with status 201 (Created) and with body the new document, or with status 400 (Bad Request) if the document has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/documents")
    public ResponseEntity<Document> createDocument(@RequestBody Document document) throws URISyntaxException {
        log.debug("REST request to save Document : {}", document);
        if (document.getId() != null) {
            throw new BadRequestAlertException("A new document cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Document result = documentRepository.save(document);
        return ResponseEntity.created(new URI("/api/documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /documents : Updates an existing document.
     *
     * @param document the document to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated document,
     * or with status 400 (Bad Request) if the document is not valid,
     * or with status 500 (Internal Server Error) if the document couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/documents")
    public ResponseEntity<Document> updateDocument(@RequestBody Document document) throws URISyntaxException {
        log.debug("REST request to update Document : {}", document);
        if (document.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Document result = documentRepository.save(document);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, document.getId().toString()))
            .body(result);
    }

    /**
     * GET  /documents : get all the documents.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of documents in body
     */
    @GetMapping("/documents")
    public ResponseEntity<List<Document>> getAllDocuments(Pageable pageable) {
        log.debug("REST request to get a page of Documents");
        Page<Document> page = documentRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/documents");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /documents/:id : get the "id" document.
     *
     * @param id the id of the document to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the document, or with status 404 (Not Found)
     */
    @GetMapping("/documents/{id}")
    public ResponseEntity<Document> getDocument(@PathVariable String id) {
        log.debug("REST request to get Document : {}", id);
        Optional<Document> document = documentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(document);
    }

    /**
     * DELETE  /documents/:id : delete the "id" document.
     *
     * @param id the id of the document to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/documents/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable String id) {
        log.debug("REST request to delete Document : {}", id);
        documentRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
