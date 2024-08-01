package hhplus.ecommoerce.api.controller;

import hhplus.ecommoerce.api.controller.dto.OrderDTO;
import hhplus.ecommoerce.biz.application.domain.service.OrderHistoryService;
import hhplus.ecommoerce.biz.application.facade.OrderFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order", description = "Order API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderFacade orderFacade;
    private final OrderHistoryService orderHistoryService;


    //주문결제 요청
    @Operation(summary = "주문 결제", description = "주문 결제를 처리합니다.")
    @ApiResponse(responseCode = "200", description = "성공적으로 결제됨",
        content = @Content(schema = @Schema(implementation = OrderDTO.class)))
    @PostMapping("/{userId}/payment")
    public ResponseEntity<OrderDTO> ordersPayment(@PathVariable Long userId, @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(OrderDTO.from(orderFacade.orderPayment(orderDTO.toEntity())));
    }



    @GetMapping("/bulk-insert")
    public String bulkInsert() {
        long startTime = System.currentTimeMillis();
        orderHistoryService.bulkInsert();
        long endTime = System.currentTimeMillis();
        return "Bulk insert completed in " + (endTime - startTime) + " ms";
    }

}
