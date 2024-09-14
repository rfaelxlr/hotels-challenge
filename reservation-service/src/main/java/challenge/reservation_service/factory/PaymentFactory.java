package challenge.reservation_service.factory;

import challenge.reservation_service.domain.Payment;
import challenge.reservation_service.model.RequestPaymentDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PaymentFactory {
    public Set<Payment> create(List<RequestPaymentDTO> payments) {
        return payments.stream().map(payment -> Payment.builder()
                        .value(payment.getValue())
                        .type(payment.getType())
                        .installmentNumber(payment.getInstallmentNumber())
                        .build())
                .collect(Collectors.toSet());
    }
}
