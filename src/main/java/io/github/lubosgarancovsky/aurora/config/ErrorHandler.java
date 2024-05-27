package io.github.lubosgarancovsky.aurora.config;

import io.github.lubosgarancovsky.restapi.mapper.ErrorDetailResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import io.github.lubosgarancovsky.aurora.business.AbstractService;
import io.github.lubosgarancovsky.domain.error.BusinessError;
import io.github.lubosgarancovsky.restapi.dto.ErrorDetailResponse;
import io.github.lubosgarancovsky.restapi.error.AbstractServiceAdviceController;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class ErrorHandler extends AbstractServiceAdviceController {

    public ErrorHandler() {
       super();
    }

    @Override
    public ResponseEntity<ErrorDetailResponse> mapErrorToResponse(final BusinessError businessError) {
        return ErrorDetailResponseMapper.mapErrorToResponse(
                businessError, AbstractService.SERVICE_NAME, UUID.randomUUID().toString());
    }
    
}
