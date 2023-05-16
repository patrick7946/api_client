package net.ibebu.user.common

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Health Checks", description = "AWS target groups 에 설정한 health check를 하기 위한 API이다.")
class HealthCheckController() {
    @Operation(summary = "AWS 의 health checks 를 하기 위한 기능")
    @GetMapping("aws/health/check")
    fun postV1EmailSend(): ResponseEntity<String> {
        return ResponseEntity.ok().body("OK")
    }
}