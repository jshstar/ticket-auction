package com.sparta.ticketauction.domain.user.entity;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sparta.ticketauction.domain.user.entity.constant.Role;
import com.sparta.ticketauction.domain.user.request.UserCreateRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("회원 이메일")
	@Column(name = "email", length = 50)
	private String email;

	@Comment("회원 비밀번호")
	@Column(name = "password", length = 15)
	private String password;

	@Comment("회원 이름")
	@Column(name = "name", length = 10)
	private String name;

	@Comment("회원 닉네임")
	@Column(name = "nickname", length = 10)
	private String nickname;

	@Comment("회원 전화번호")
	@Column(name = "phone_number", length = 30)
	private String phoneNumber;

	@Comment("회원 생년월일")
	@Column(name = "birth")
	private LocalDate birth;

	@Comment("회원 역할(관리자 or 일반 유저)")
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role = Role.USER;

	@Comment("회원 보유 포인트")
	@Column(name = "point")
	@ColumnDefault("0")
	private long point;

	private User(String email, String password, String name, String nickname, String phoneNumber, LocalDate birth) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
		this.phoneNumber = phoneNumber;
		this.birth = birth;
	}

	public static User of(UserCreateRequest request, PasswordEncoder encoder) {
		return new User(
			request.getEmail(),
			encoder.encode(request.getPassword()),
			request.getName(),
			request.getNickname(),
			request.getPhoneNumber(),
			request.getBirth()
		);
	}

	public void chargePoint(long point) {
		this.point += point;
	}

	public void usePoint(long point) {
		this.point -= point;
	}
}
