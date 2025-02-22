package app.shopapi.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Payment{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long paymentId;

        @OneToOne(mappedBy = "payment", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
        private Order order;

        @NotBlank
        @Size(min = 4, message = "Payment method must contain atleast 4 characters")
        private String paymentMethod;
}
