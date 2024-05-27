package io.github.lubosgarancovsky.aurora.business.project.repository;

import io.github.lubosgarancovsky.aurora.domain.listing.ProjectsListingAttribute;
import io.github.lubosgarancovsky.persistance.jooq.metadata.JooqRsqlMetadata;
import io.github.lubosgarancovsky.persistance.jooq.metadata.JooqRsqlMetadataConfigProvider;

import static io.github.lubosgarancovsky.aurora.repository.model.tables.Projects.PROJECTS;

public class ProjectJooqRsqlMetadataConfigProvider implements JooqRsqlMetadataConfigProvider {

    @Override
    public void configure(JooqRsqlMetadata metadata) {
        metadata
                .addColumnInfo(ProjectsListingAttribute.NAME.apiName(), PROJECTS.NAME)
                .addColumnInfo(ProjectsListingAttribute.CREATED_BY.apiName(), PROJECTS.CREATED_BY);
    }
}
