package io.github.jhipster.application.repository.search;

import io.github.jhipster.application.domain.Pack;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Pack entity.
 */
public interface PackSearchRepository extends ElasticsearchRepository<Pack, Long> {
}
