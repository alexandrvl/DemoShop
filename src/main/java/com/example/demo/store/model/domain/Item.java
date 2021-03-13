package com.example.demo.store.model.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import com.example.demo.common.model.domain.BaseVersionedEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, exclude = "itemGroup")
@ToString(callSuper = true, exclude = "itemGroup")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item")
public class Item extends BaseVersionedEntity<Long> {

    @Id
    @Column(name = "id", columnDefinition = "bigserial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 40)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @PositiveOrZero
    @Column(name = "count", nullable = false)
    private Integer count;

    @NotNull
    @PositiveOrZero
    @Digits(integer = 10, fraction = 2)
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "group_id", nullable = false)
    private ItemGroup itemGroup;

}
