package io.github.lubosgarancovsky.aurora.business.story.repository;

import io.github.lubosgarancovsky.aurora.domain.listing.StoryListingAttribute;
import io.github.lubosgarancovsky.persistance.jooq.metadata.JooqRsqlMetadata;
import io.github.lubosgarancovsky.persistance.jooq.metadata.JooqRsqlMetadataConfigProvider;

import static io.github.lubosgarancovsky.aurora.repository.model.tables.Stories.STORIES;

public class StoryJooqRsqlMetadataConfigProvider implements JooqRsqlMetadataConfigProvider {
    @Override
    public void configure(JooqRsqlMetadata metadata) {
        metadata
                .addColumnInfo(StoryListingAttribute.NAME.apiName(), STORIES.NAME)
                .addColumnInfo(StoryListingAttribute.CODE.apiName(), STORIES.CODE)
                .addColumnInfo(StoryListingAttribute.PROJECT_ID.apiName(), STORIES.PROJECT_ID)
                .addColumnInfo(StoryListingAttribute.CREATED_AT.apiName(), STORIES.CREATED_AT)
                .addColumnInfo(StoryListingAttribute.CREATED_BY.apiName(), STORIES.CREATED_BY)
                .addColumnInfo(StoryListingAttribute.IN_BOARD.apiName(), STORIES.IN_BOARD)
                .addColumnInfo(StoryListingAttribute.STATE_ID.apiName(), STORIES.STATE_ID)
                .addColumnInfo(StoryListingAttribute.TYPE_ID.apiName(), STORIES.TYPE_ID)
                .addColumnInfo(StoryListingAttribute.ASSIGNED_TO.apiName(), STORIES.ASSIGNED_TO);
    }
}
