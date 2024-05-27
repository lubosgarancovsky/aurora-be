package io.github.lubosgarancovsky.aurora.business.board.repository;

import io.github.lubosgarancovsky.aurora.business.JooqRepository;
import io.github.lubosgarancovsky.aurora.domain.board.command.UpdateBoardCommand;
import io.github.lubosgarancovsky.aurora.domain.board.query.BoardListingQuery;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.board.BoardEntryResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.board.BoardResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.board.ImmutableBoardEntryResponse;
import io.github.lubosgarancovsky.aurora.rest_api.api_dto.board.ImmutableBoardResponse;
import org.jooq.*;
import org.jooq.Record;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static io.github.lubosgarancovsky.aurora.repository.model.tables.ProjectBoard.PROJECT_BOARD;
import static io.github.lubosgarancovsky.aurora.repository.model.tables.StoryState.STORY_STATE;

@Repository
public class JooqBoardRepository extends JooqRepository {

    protected JooqBoardRepository(DSLContext dslContext) {
        super(dslContext);
    }

    public BoardResponse list(BoardListingQuery query) {
        final var select = dslContext
                .select(
                        STORY_STATE.ID,
                        STORY_STATE.NAME,
                        STORY_STATE.CODE

                )
                .from(PROJECT_BOARD)
                .join(STORY_STATE)
                .on(PROJECT_BOARD.STATE_ID.eq(STORY_STATE.ID))
                .where(PROJECT_BOARD.PROJECT_ID.eq(query.projectId()))
                .fetch();

        return map(select);
    }

    public void update(UpdateBoardCommand command) {
        dslContext.deleteFrom(PROJECT_BOARD).where(PROJECT_BOARD.PROJECT_ID.eq(command.projectId())).execute();

        List<? extends TableRecord<?>> records = command.items().stream().map(item -> {
            TableRecord<?> record = dslContext.newRecord(PROJECT_BOARD);
            record.setValue(PROJECT_BOARD.PROJECT_ID, command.projectId());
            record.setValue(PROJECT_BOARD.STATE_ID, item);

            return record;
        }).toList();

        dslContext.batchInsert(records).execute();
    }

    private BoardEntryResponse map(Record record) {
        return ImmutableBoardEntryResponse.builder()
                .id(record.get(STORY_STATE.ID).toString())
                .name(record.get(STORY_STATE.NAME))
                .code(record.get(STORY_STATE.CODE))
                .build();
    }

    private BoardResponse map(Result result) {
        return ImmutableBoardResponse.builder()
                .items(result.stream().map(record -> {
                    return map((Record) record);
                }).toList())
                .build();
    }
}
