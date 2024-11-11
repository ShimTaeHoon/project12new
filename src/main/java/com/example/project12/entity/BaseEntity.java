package com.example.project12.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

// 이벤트 발생을 감지하는 리스너 설정
@EntityListeners(value = { AuditingEntityListener.class })
@MappedSuperclass // 테이블과 직접 매핑되는 테이블이 아님을 명시
@Getter
@Setter
public abstract class BaseEntity {

    // insert 발생시 현재시간이 자동으로 저장됨 (한번)
    @CreatedDate
    LocalDateTime regDate; // 날짜 + 시간 + 타임존

    // update 발생시 현재시간이 자동으로 저장됨 (여러번)
    @LastModifiedDate
    LocalDateTime modDate;

}
