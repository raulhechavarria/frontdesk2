package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Pack;
import io.github.jhipster.application.repository.PackRepository;
import io.github.jhipster.application.repository.search.PackSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Pack.
 */
@Service
@Transactional
public class PackService {

    private final Logger log = LoggerFactory.getLogger(PackService.class);

    private final PackRepository packRepository;

    private final PackSearchRepository packSearchRepository;

    public PackService(PackRepository packRepository, PackSearchRepository packSearchRepository) {
        this.packRepository = packRepository;
        this.packSearchRepository = packSearchRepository;
    }

    /**
     * Save a pack.
     *
     * @param pack the entity to save
     * @return the persisted entity
     */
    public Pack save(Pack pack) {
        log.debug("Request to save Pack : {}", pack);
        Pack result = packRepository.save(pack);
        packSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the packs.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Pack> findAll() {
        log.debug("Request to get all Packs");
        return packRepository.findAll();
    }


    /**
     * Get one pack by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<Pack> findOne(Long id) {
        log.debug("Request to get Pack : {}", id);
        return packRepository.findById(id);
    }

    /**
     * Delete the pack by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Pack : {}", id);
        packRepository.deleteById(id);
        packSearchRepository.deleteById(id);
    }

    /**
     * Search for the pack corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Pack> search(String query) {
        log.debug("Request to search Packs for query {}", query);
        return StreamSupport
            .stream(packSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
